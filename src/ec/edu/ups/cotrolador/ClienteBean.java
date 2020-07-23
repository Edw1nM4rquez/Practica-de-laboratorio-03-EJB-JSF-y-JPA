package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.entidad.Cliente;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ClienteBean implements Serializable {
    private  static final  long serialVersionUID = 1L;

    @EJB
    private ClienteFacade ejbClienteFacade;
    private Cliente cliente;
    private List<Cliente> clientes;

    public ClienteBean(){ }

    @PostConstruct
    public void init() {
        clientes = ejbClienteFacade.findAll();
        cliente = new Cliente();
    }


    public void crear() {
        if (verificarCedula(cliente.getCedula())){
            if (ejbClienteFacade.find(this.cliente.getCedula())==null){
                cliente.setEstado("Activo");
                cliente.setNombre(cliente.getNombre().toUpperCase());
                cliente.setApellido(cliente.getApellido().toUpperCase());
                cliente.setPassword("default");
                ejbClienteFacade.create(cliente);
                actualizar();
                Messages.create("Info").detail("Cliente agregado correctamente").add();
            }else {
                Messages.create("Fatal!").fatal().detail("Ya existe un cliente con la misma cédula").add();
            }
        }else {
            Messages.create("Fatal!").fatal().detail("La cédula ingresada no es correcta").add();
        }
    }

    public void delete(Cliente clienteE) {
        clienteE.setEstado("Eliminado");
        ejbClienteFacade.edit(clienteE);
        Messages.create("Info").detail("Cliente eliminado correctamente").add();
    }

    public void recuperarCliente(Cliente clienteE) {
        clienteE.setEstado("Activo");
        ejbClienteFacade.edit(clienteE);
        Messages.create("Info").detail("Cliente recuperado correctamente").add();
    }
    public void actualizarCliente(Cliente clienteE) {
        ejbClienteFacade.edit(clienteE);
        Messages.create("Info").detail("Cliente editado correctamente").add();
    }

    public void actualizar(){
        cliente=new Cliente();
        clientes=ejbClienteFacade.findAll();
    }


    public boolean verificarCedula(String cedula){
        int total = 0;
        int tamanoLongitudCedula = 10;
        int [] coeficientes = {2,1,2,1,2,1,2,1,2};
        int numeroProvincias = 24;
        int tercerDigito= 6;

        if (cedula.matches("[0-9]*") && cedula.length() == tamanoLongitudCedula)
        {
            int provincia = Integer.parseInt(cedula.charAt(0)+""+cedula.charAt(1));
            int digitoTres = Integer.parseInt(cedula.charAt(2) + "");
            if ((provincia > 0 && provincia <= numeroProvincias) && digitoTres < tercerDigito)
            {
                int digitoVerificadorRecibido = Integer.parseInt(cedula.charAt(9)+"");
                for (int i=0; i<coeficientes.length; i++)
                {
                    int valor = Integer.parseInt(coeficientes[i]+"")*
                            Integer.parseInt(cedula.charAt(i)+"");
                    total = valor >= 10? total + (valor-9): total + valor;
                }
                int digitoVerificadorObtenido = total >= 10?
                        (total %10) != 0 ?
                                10 - (total % 10) :
                (total % 10) : total;
                if(digitoVerificadorObtenido == digitoVerificadorRecibido)
                {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}

