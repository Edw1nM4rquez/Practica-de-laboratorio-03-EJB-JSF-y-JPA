package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.RolFacade;
import ec.edu.ups.entidad.Rol;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class RolBean implements Serializable {
    private  static final  long serialVersionUID = 1L;

    @EJB
    private RolFacade ejbRolFacade;
    private Rol rol;
    private List<Rol> rolList;


    public RolBean(){

    }
    @PostConstruct
    public void init() {
        rolList = ejbRolFacade.findAll();
        rol=new Rol();
    }

    public void agregar(){
        rol.setNombre(rol.getNombre().toUpperCase());
        ejbRolFacade.create(rol);
        rolList=ejbRolFacade.findAll();
        rol=new Rol();
        Messages.create("Info").detail("Rol Creado").add();
    }

    public Rol getRol() {
        return rol;
    }

    public List<Rol> getRolList() {
        return rolList;
    }




}
