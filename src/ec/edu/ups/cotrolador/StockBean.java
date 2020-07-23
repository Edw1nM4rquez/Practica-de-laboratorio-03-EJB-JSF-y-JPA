package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.StockFacade;
import ec.edu.ups.entidad.Bodega;
import ec.edu.ups.entidad.Cliente;
import ec.edu.ups.entidad.Producto;
import ec.edu.ups.entidad.Stock;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class StockBean implements Serializable {
    private  static final  long serialVersionUID = 1L;

    @EJB
    private StockFacade ejbStockFacade;
    private Stock stock;
    private List<Stock> stockList;
    private List<Stock> stockFiltro;
    private int cantidad;

    @PostConstruct
    public void init() {
        this.stockList = ejbStockFacade.findAll();
        this.stock=new Stock();
        this.cantidad=0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarProducto(Producto producto){
        this.stock.setProducto(producto);
        Messages.create("Info").detail("El porducto " +producto.getNombre()+" se ha añadido" ).add();
    }
    public void agregarBodega(Bodega bodega){
        this.stock.setBodega(bodega);
        Messages.create("Info").detail("La bodega " +bodega.getNombre()+" se ha añadido" ).add();
    }

    public List<Stock> getStockFiltro() {
        return stockFiltro;
    }

    public void setStockFiltro(List<Stock> stockFiltro) {
        this.stockFiltro = stockFiltro;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void crear(){
        if (stock.getBodega()!=null){
            if (stock.getProducto()!=null){
                if (stock.getCatidad()!=0){
                    ejbStockFacade.create(stock);
                    stock=new Stock();
                    stockList=ejbStockFacade.findAll();
                    Messages.create("Info").detail("Se ha añadido el producto a la bodega" ).add();
                }else {
                    Messages.create("Fatal!").fatal().detail("Stock minimo 1").add();
                }
            }else {
                Messages.create("Fatal!").fatal().detail("Agrege un producto a la bodega").add();
            }
        }else {
            Messages.create("Fatal!").fatal().detail("Agrege una bodega").add();
        }

    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void actualizarStock(){
        if (stock.getCodigo()!=0){
            if (cantidad!=0){
                stock.setCatidad(stock.getCatidad()+cantidad);
                ejbStockFacade.edit(stock);
                ejbStockFacade.findAll();
                cantidad=0;
                stock=new Stock();
                Messages.create("Info").detail("Se aumento el stock correctamente" ).add();
            }else {
                Messages.create("Fatal!").fatal().detail("La cantidad minima debe ser 1").add();
            }
        }else {
            Messages.create("Fatal!").fatal().detail("Agrege el Stock a aumentar").add();
        }
    }
    public void agregarStock(Stock stockAct){
        this.stock=stockAct;
        Messages.create("Info").detail("Se agrego stock a aumentar" ).add();
    }
}
