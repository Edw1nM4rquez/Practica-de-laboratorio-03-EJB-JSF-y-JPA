package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int codigo;
	private String nombre;
	@ManyToOne
	private Ciudad ciudad;
	private String direccion;
	private char estado;
	@OneToMany(mappedBy = "bodega")
	private List<Stock> productosStock;

	public Bodega() {

	}

	public Bodega(String nombre, Ciudad ciudad, String direccion, char estado) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.estado = estado;
	}

	public List<Stock> getProductosStock() {
		return productosStock;
	}

	public void setProductosStock(List<Stock> productosStock) {
		this.productosStock = productosStock;
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

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return String.format("Bodega[%o, %s, %s, %c]",codigo,nombre,direccion,estado);
	}



}
