package ec.edu.ups.cotrolador;
import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.entidad.Bodega;
import org.omnifaces.util.Messages;

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
public class BodegaBean implements Serializable {

    private  static final  long serialVersionUID = 1L;

    @EJB
    private BodegaFacade ejbBodegaFacade;
    private Bodega bodega;
    private List<Bodega> bodegaList;

    public BodegaBean(){ }

    @PostConstruct
    public void init() {
        this.bodegaList = ejbBodegaFacade.findAll();
        this.bodega=new Bodega();

    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Bodega> getBodegaList() {
        return bodegaList;
    }

    public void setBodegaList(List<Bodega> bodegaList) {
        this.bodegaList = bodegaList;
    }

    public void crear() {
        this.bodega.setEstado('A');
        this.bodega.setNombre(bodega.getNombre().toUpperCase());
        ejbBodegaFacade.create(bodega);
        this.bodega=new Bodega();
        bodegaList = ejbBodegaFacade.findAll();
        Messages.create("Info").detail("Bodega creada correcatamente").add();
    }

    public String delete(Bodega b) {
        ejbBodegaFacade.remove(b);
        bodegaList = ejbBodegaFacade.findAll();
        return null;
    }



}
