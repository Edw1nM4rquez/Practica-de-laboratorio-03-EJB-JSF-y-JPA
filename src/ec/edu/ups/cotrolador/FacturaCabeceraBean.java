package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.FacturaCebeceraFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.StockFacade;
import ec.edu.ups.entidad.*;
import org.omnifaces.util.Messages;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaCabeceraBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private FacturaCebeceraFacade ejbFacturaCabeceraFacade;
    @EJB
    private StockFacade ejbStock;
    @EJB FacturaDetalleFacade ejbFacturadetalle;
    private FacturaCabecera facturaCabecera;
    private FacturaDetalle facturaDetalle;
    private List<FacturaDetalle> facturaDetalles;
    private List<FacturaCabecera> listaFacturaCabecera;
    private List<Stock> stockList;
    private FacturaCabecera facturaVisualizar;

    public FacturaCabeceraBean() {
    }
    @PostConstruct
    public void init(){
        facturaCabecera = new FacturaCabecera();
        facturaCabecera.setSubtotal(0);
        facturaCabecera.setIva(0);
        facturaCabecera.setTotal(0);
        facturaCabecera.setFecha(new Date());
        facturaDetalle=new FacturaDetalle();
        listaFacturaCabecera=ejbFacturaCabeceraFacade.findAll();
        facturaDetalles=new ArrayList<FacturaDetalle>();
        stockList=new ArrayList<Stock>();

    }

    public FacturaDetalle getFacturaDetalle() {
        return facturaDetalle;
    }

    public void setFacturaDetalle(FacturaDetalle facturaDetalle) {
        this.facturaDetalle = facturaDetalle;
    }

    public List<FacturaDetalle> getFacturaDetalles() {
        return facturaDetalles;
    }

    public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
        this.facturaDetalles = facturaDetalles;
    }

    public FacturaCebeceraFacade getEjbFacturaCabeceraFacade() {
        return ejbFacturaCabeceraFacade;
    }

    public void setEjbFacturaCabeceraFacade(FacturaCebeceraFacade ejbFacturaCabeceraFacade) {
        this.ejbFacturaCabeceraFacade = ejbFacturaCabeceraFacade;
    }

    public List<FacturaCabecera> getListaFacturaCabecera() {
        return listaFacturaCabecera;
    }

    public void setListaFacturaCabecera(List<FacturaCabecera> listaFacturaCabecera) {
        this.listaFacturaCabecera = listaFacturaCabecera;
    }

    public FacturaCabecera getFacturaCabecera() {
        return facturaCabecera;
    }

    public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
        this.facturaCabecera = facturaCabecera;
    }

    public void agregarCliente(Cliente cliente){
        FacesContext context= FacesContext.getCurrentInstance();
        Empleado empleado= (Empleado) context.getExternalContext().getSessionMap().get("Empleado");
        this.facturaCabecera.setCliente(cliente);
        this.facturaCabecera.setEmpleado(empleado);
        Messages.create("Info").detail(facturaCabecera.getCliente().getCedula()).add();
    }

    public void agregarDetalle(Stock stock){
        if (stock!=null){
            if (validarDetalle(stock)){
                Messages.create("Error!").error().detail("El producto de esa bodega ya esta añadido").add();
            }else {
                this.facturaDetalle.setCantida(1);
                this.facturaDetalle.setProducto(stock.getProducto());
                this.facturaDetalles.add(facturaDetalle);
                this.stockList.add(stock);
                calcularValores();
                this.facturaDetalle=new FacturaDetalle();
            }

        }
    }

    public boolean validarDetalle(Stock stock){
        for (Stock stock1: stockList){
            if (stock1.getProducto().getCodigo().equals(stock.getProducto().getCodigo())){
                return true;
            }
        }
        return false;
    }

    public void calcularValores(){
        double total=0;
        for (FacturaDetalle detalle: facturaDetalles
             ) {
            total+=detalle.getTotal();

        }
        facturaCabecera.setSubtotal(round(total,2));
        facturaCabecera.setIva(round(total*0.12,2));
        facturaCabecera.setTotal(facturaCabecera.getSubtotal()+facturaCabecera.getIva());
    }

    public void crear(){
        if (facturaDetalles.isEmpty()){
            Messages.create("Error!").error().detail("Agrege productos a la factura").add();
        }else{
            if (facturaCabecera.getCliente()!=null){
                calcularValores();
                facturaCabecera.setEstado("Registrada");
                ejbFacturaCabeceraFacade.create(facturaCabecera);
                FacturaCabecera cabecera=ejbFacturaCabeceraFacade.obtenerUltimo();
                int i=0;
                for (FacturaDetalle detalle: facturaDetalles
                ) {
                    detalle.setFacturaCabecera(cabecera);
                    detalle.setTotal(detalle.getTotal());
                    ejbFacturadetalle.create(detalle);
                    Stock st=stockList.get(i);
                    st.setCatidad(st.getCatidad()-detalle.getCantida());
                    ejbStock.edit(st);
                    i++;
                }

                stockList=new ArrayList<Stock>();
                facturaDetalles=new ArrayList<FacturaDetalle>();
                facturaCabecera=new FacturaCabecera();
                listaFacturaCabecera=ejbFacturaCabeceraFacade.findAll();

                Messages.create("Info").detail("Factura Creada").add();
            }else {
                Messages.create("Error!").error().detail("Agrege un cliente a la factura").add();
            }

        }

    }

    public void quitarProducto(String codigo, int fila){
        try {
            int i=0;
            for (FacturaDetalle producto: this.facturaDetalles
            ) {
                if (producto.getProducto().getCodigo().equals(codigo) && fila==i){
                    this.facturaDetalles.remove(i);
                    this.stockList.remove(i);
                    break;
                }
                i++;
            }
            calcularValores();

        }catch (Exception e){

        }

    }

    public void anularFactura(FacturaCabecera facturaAnular){
        facturaAnular.setEstado("Anulada");
        ejbFacturaCabeceraFacade.edit(facturaAnular);
        Messages.create("Info").detail("Factura Anulada").add();

    }


    public  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void onRowEdit(RowEditEvent event) {
        calcularValores();

    }

    public void onRowCancel(RowEditEvent event) {

    }

    public FacturaCabecera getFacturaVisualizar() {
        return facturaVisualizar;
    }

    public void setFacturaVisualizar(FacturaCabecera facturaVisualizar) {
        this.facturaVisualizar = facturaVisualizar;
    }

    public String visualizarFactura(FacturaCabecera facVisualizar){
        this.facturaVisualizar=facVisualizar;
        return "dasboardFacturaVisualizar?faces-redirect=true";
    }
}
