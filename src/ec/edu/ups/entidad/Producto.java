package ec.edu.ups.entidad;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codigo;
	private String nombre;
	@Lob
	private byte[] imagen;
	@ManyToOne
	private Categoria categoria;
	private double precio; 
	private double iva;
	private String descripcion;
	private char estado;
	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="producto")
	private List<Stock> bodegasStock;
	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
	private List<FacturaDetalle> facturasDetalle;

	public Producto() {

	}

	public Producto(String codigo, String nombre, byte[] imagen, Categoria categoria,
					double precio, double iva, String descripcion, char estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.imagen = imagen;
		this.categoria = categoria;
		this.precio = precio;
		this.iva = iva;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public List<FacturaDetalle> getFacturasDetalle() {
		return facturasDetalle;
	}

	public void setFacturasDetalle(List<FacturaDetalle> facturasDetalle) {
		this.facturasDetalle = facturasDetalle;
	}

	public List<Stock> getBodegasStock() {
		return bodegasStock;
	}

	public void setBodegasStock(List<Stock> bodegasStock) {
		this.bodegasStock = bodegasStock;
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Producto)) return false;
		Producto producto = (Producto) o;
		return Double.compare(producto.precio, precio) == 0 &&
				Double.compare(producto.iva, iva) == 0 &&
				estado == producto.estado &&
				Objects.equals(codigo, producto.codigo) &&
				Objects.equals(nombre, producto.nombre) &&
				Arrays.equals(imagen, producto.imagen) &&
				Objects.equals(categoria, producto.categoria) &&
				Objects.equals(descripcion, producto.descripcion) &&
				Objects.equals(bodegasStock, producto.bodegasStock) &&
				Objects.equals(facturasDetalle, producto.facturasDetalle);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(codigo, nombre, categoria, precio, iva, descripcion, estado, bodegasStock, facturasDetalle);
		result = 31 * result + Arrays.hashCode(imagen);
		return result;
	}

	@Override
	public String toString() {
		return String.format("Producto[%s, %s,%s,%c]",codigo,nombre,descripcion,estado);
	}


}
