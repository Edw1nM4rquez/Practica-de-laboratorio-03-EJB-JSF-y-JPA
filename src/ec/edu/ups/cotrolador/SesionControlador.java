package ec.edu.ups.cotrolador;

import ec.edu.ups.entidad.Empleado;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class SesionControlador implements Serializable {

    private Empleado empleado;

    public void verificarSesion(){
        try {
            FacesContext context= FacesContext.getCurrentInstance();
             empleado= (Empleado) context.getExternalContext().getSessionMap().get("Empleado");
            if(empleado==null){
               context.getExternalContext().redirect("/DistribuidoraJSF_war_exploded/index.xhtml");
            }
        }catch (Exception exception){

        }
    }

    public void cerrarSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/DistribuidoraJSF_war_exploded/index.xhtml");
        empleado=new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
