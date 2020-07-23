package ec.edu.ups.entidad;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Cliente extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;

	private String password;
	private String estado;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private List<FacturaCabecera> facturasCabeceraCliente;

	public Cliente() {

	}

	public Cliente(String cedula, String nombre, String apellido, String direccion, String telefono, String correo, String password, String estado) {
		super(cedula, nombre, apellido, direccion, telefono, correo);
		this.password = password;
		this.estado = estado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<FacturaCabecera> getFacturasCabeceraCliente() {
		return facturasCabeceraCliente;
	}

	public void setFacturasCabeceraCliente(List<FacturaCabecera> facturasCabeceraCliente) {
		this.facturasCabeceraCliente = facturasCabeceraCliente;
	}
}
