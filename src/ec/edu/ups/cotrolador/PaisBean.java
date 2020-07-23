package ec.edu.ups.cotrolador;
import ec.edu.ups.ejb.PaisFacade;
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
public class PaisBean implements Serializable {
    private  static final  long serialVersionUID = 1L;
    @EJB
    private PaisFacade ejbPaisFacade;
    private Pais pais;
    private List<Pais> paisList;
    private List<Provincia> provinciaList;


    public PaisBean (){
    }

    @PostConstruct
    public void init() {
        this.paisList=ejbPaisFacade.findAll();
        this.pais=new Pais();
        this.provinciaList=null;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public void cargarProvincias(AjaxBehaviorEvent event){
        provinciaList=ejbPaisFacade.find(pais.getCodigo()).getProvincias();
        this.pais=new Pais();
    }


    public void crear(){
        this.pais.setNombre(pais.getNombre().toUpperCase());
        ejbPaisFacade.create(pais);
        this.pais=new Pais();
        this.paisList=ejbPaisFacade.findAll();
        Messages.create("Info").detail("Pais Creado").add();
    }



}
