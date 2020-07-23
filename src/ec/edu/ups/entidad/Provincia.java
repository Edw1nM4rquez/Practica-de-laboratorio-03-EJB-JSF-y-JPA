package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nombre;
	@ManyToOne
	private Pais pais;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia",fetch = FetchType.LAZY)
	private List<Ciudad> ciudades;
	
	public Provincia() {
	}
	
	public Provincia( String nombre, Pais pais) {
		super();
		this.nombre = nombre;
		this.pais = pais;
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
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public List<Ciudad> getCiudades() {
		return ciudades;
	}
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Provincia)) return false;
		Provincia provincia = (Provincia) o;
		return codigo == provincia.codigo &&
				nombre.equals(provincia.nombre) &&
				pais.equals(provincia.pais);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nombre, pais);
	}

	@Override
	public String toString() {
		return String.format("Provincia[%o, %s]",codigo,nombre);
	}
}
