package ec.edu.ups.cotrolador;
import ec.edu.ups.ejb.CiudadFacade;
import ec.edu.ups.entidad.Ciudad;
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
public class CiudadBean implements Serializable {
    private  static final  long serialVersionUID = 1L;

    @EJB
    private CiudadFacade ejbCiudadFacade;
    private Ciudad ciudad;
    private List<Ciudad> ciudadList;

    public CiudadBean(){ }

    @PostConstruct
    public void init() {
        this.ciudad=new Ciudad();
        this.ciudadList=ejbCiudadFacade.findAll();

    }

    public void crear(){
        this.ciudad.setNombre(ciudad.getNombre().toUpperCase());
        ejbCiudadFacade.create(ciudad);
        ciudadList=ejbCiudadFacade.findAll();
        this.ciudad=new Ciudad();
        Messages.create("Info").detail("Ciudad Creada").add();
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

}
