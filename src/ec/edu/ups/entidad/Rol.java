package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Rol implements Serializable{
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
	private List<Empleado> empleadosRoles;
	
	public Rol() {
		
	}
	
	
	public Rol(String nombre) {
		super();
		this.nombre = nombre;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Empleado> getEmpleadosRoles() {
		return empleadosRoles;
	}


	public void setEmpleadosRoles(List<Empleado> empleadosRoles) {
		this.empleadosRoles = empleadosRoles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Rol)) return false;
		Rol rol = (Rol) o;
		return codigo == rol.codigo &&
				Objects.equals(nombre, rol.nombre) &&
				Objects.equals(empleadosRoles, rol.empleadosRoles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nombre, empleadosRoles);
	}

	@Override
	public String toString() {
		return String.format("Rol[%o, %s]",codigo,nombre);
	}
	

}
