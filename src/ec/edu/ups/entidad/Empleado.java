package ec.edu.ups.entidad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Empleado extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Rol rol;
	private char estado;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
	private List<FacturaCabecera> facturasCabeceraEmpleado;

	
	public Empleado() {

	}

	public Empleado(String cedula, String nombre, String apellido, String direccion, String telefono,
					String correo, Rol rol, char estado, String password) {
		super(cedula, nombre, apellido, direccion, telefono, correo);
		this.rol = rol;
		this.estado = estado;
		this.password = password;
	}

	public List<FacturaCabecera> getFacturasCabeceraEmpleado() {
		return facturasCabeceraEmpleado;
	}

	public void setFacturasCabeceraEmpleado(List<FacturaCabecera> facturasCabeceraEmpleado) {
		this.facturasCabeceraEmpleado = facturasCabeceraEmpleado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
