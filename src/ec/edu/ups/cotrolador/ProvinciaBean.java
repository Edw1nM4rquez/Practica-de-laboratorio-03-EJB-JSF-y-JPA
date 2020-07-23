package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.ProvinciaFacade;
import ec.edu.ups.entidad.Ciudad;
import ec.edu.ups.entidad.Pais;
import ec.edu.ups.entidad.Provincia;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProvinciaBean implements Serializable {
    private  static final  long serialVersionUID = 1L;
    @EJB
    private ProvinciaFacade ejbProvinciaFacade;
    private Provincia provincia;
    private Pais pais;
    private List<Provincia> provinciaList;
    private List<Ciudad> ciudadList;



    public ProvinciaBean (){

    }

    @PostConstruct
    public void init(){
        this.provincia=new Provincia();
        this.provinciaList=ejbProvinciaFacade.findAll();
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public void cargarCiudades(AjaxBehaviorEvent event){
        ciudadList=ejbProvinciaFacade.find(provincia.getCodigo()).getCiudades();
    }
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }


    public void crear(){
        this.provincia.setNombre(provincia.getNombre().toUpperCase());
        ejbProvinciaFacade.create(provincia);
        ejbProvinciaFacade.refreshJPACache();
        provinciaList=ejbProvinciaFacade.findAll();
        this.provincia=new Provincia();
        Messages.create("Info").detail("Provincia Creada").add();
    }
}
