package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Ciudad implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int codigo;
	private String nombre;
	@ManyToOne
	private Provincia provincia;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudad")
	private List<Bodega> bodegas;
	
	public Ciudad() {

	}
	public Ciudad(String nombre, Provincia provincia) {
		super();
		this.nombre = nombre;
		this.provincia = provincia;
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
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public List<Bodega> getBodegas() {
		return bodegas;
	}
	public void setBodegas(List<Bodega> bodegas) {
		this.bodegas = bodegas;
	}


	@Override
	public String toString() {
		return String.format("Ciudad[%o, %s]",codigo,nombre);
	}
}
