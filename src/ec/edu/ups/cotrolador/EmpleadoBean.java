package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.EmpleadoFacade;
import ec.edu.ups.entidad.Empleado;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class EmpleadoBean implements Serializable  {
    private  static final  long serialVersionUID = 1L;

    @EJB
    private EmpleadoFacade ejbEmpleadoFacade;
    private Empleado empleado;
    private List<Empleado> list;
    public EmpleadoBean(){ }

    @PostConstruct
    public void init() {
        empleado = new Empleado();
        list = ejbEmpleadoFacade.findAll();
    }

    public void crear() {
        if (ejbEmpleadoFacade.find(empleado.getCedula())==null){
            empleado.setEstado('A');
            empleado.setNombre(empleado.getNombre().toUpperCase());
            empleado.setApellido(empleado.getApellido().toUpperCase());
            ejbEmpleadoFacade.create(empleado);
            list = ejbEmpleadoFacade.findAll();
            this.empleado=new Empleado();
            Messages.create("Info").detail("Empleado Creado").add();
        }else {
            Messages.create("Fatal!").fatal().detail("Ya existe un empleado con la misma cedula").add();
        }
    }

    public void iniciarSesion(){
        Empleado emp;
        String redireccion;
            try {
                emp = ejbEmpleadoFacade.iniciarSesion(empleado);
                if (emp!=null){
                    if (emp.getRol().getNombre().equals("ADMINISTRADOR")){
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Empleado",emp);
                        redireccion = "/DistribuidoraJSF_war_exploded/Views/private/administrador/dashboard.xhtml";
                        FacesContext.getCurrentInstance().getExternalContext().redirect(redireccion);
                        empleado=new Empleado();
                    }else if (emp.getRol().getNombre().equals("VENDEDOR")){
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Empleado",emp);
                        redireccion = "/DistribuidoraJSF_war_exploded/Views/private/vendedor/dashboard.xhtml";
                        FacesContext.getCurrentInstance().getExternalContext().redirect(redireccion);
                        empleado=new Empleado();
                    }
                }else {
                    Messages.create("Error!").error().detail("Credenciales Incorrectas").add();
                }
            }catch (Exception e){

            }
    }

    public List<Empleado> getList() {
        return list;
    }

    public void setList(List<Empleado> list) {
        this.list = list;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
