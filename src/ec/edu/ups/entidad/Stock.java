package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Stock implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;	
	@ManyToOne
	private Producto producto;
	@ManyToOne
	private Bodega bodega;
	private int catidad;

	public Stock() {

	}
	
	public Stock(int codigo, Producto producto, Bodega bodega, int catidad) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.bodega = bodega;
		this.catidad = catidad;
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public int getCatidad() {
		return catidad;
	}

	public void setCatidad(int catidad) {
		this.catidad = catidad;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Stock)) return false;
		Stock stock = (Stock) o;
		return codigo == stock.codigo &&
				catidad == stock.catidad &&
				Objects.equals(producto, stock.producto) &&
				Objects.equals(bodega, stock.bodega);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, producto, bodega, catidad);
	}
	@Override
	public String toString() {
		return String.format("Stock[%o, %o]",codigo,catidad);
	}


}
