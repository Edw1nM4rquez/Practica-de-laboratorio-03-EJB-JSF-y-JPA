package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.entidad.FacturaDetalle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaDetalleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private FacturaDetalleFacade ejbFacturaDetalleFacade;
    private FacturaDetalle facturaDetalle;
    private List<FacturaDetalle> listaFacturaDetalle;

    public FacturaDetalleBean() {}

    @PostConstruct
    public void init(){
        listaFacturaDetalle = ejbFacturaDetalleFacade.findAll();
        facturaDetalle = new FacturaDetalle();
    }


    public List<FacturaDetalle> getListaFacturaDetalle() {
        return listaFacturaDetalle;
    }

    public void setListaFacturaDetalle(List<FacturaDetalle> listaFacturaDetalle) {
        this.listaFacturaDetalle = listaFacturaDetalle;
    }

    public FacturaDetalle getFacturaDetalle() {
        return facturaDetalle;
    }

    public void setFacturaDetalle(FacturaDetalle facturaDetalle) {
        this.facturaDetalle = facturaDetalle;
    }

    public String add(){
        ejbFacturaDetalleFacade.create(new FacturaDetalle());
        listaFacturaDetalle = ejbFacturaDetalleFacade.findAll();
        return null;

    }

    public void obtenerCliente(String cedula){
        System.out.println( cedula );

    }

}
