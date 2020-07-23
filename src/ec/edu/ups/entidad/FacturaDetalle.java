package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FacturaDetalle implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private int cantida;
	private double total;
	@ManyToOne
	private Producto producto;
	@ManyToOne
	private FacturaCabecera facturaCabecera;
	
	public FacturaDetalle() {

	}

	public FacturaDetalle(int codigo, int cantida, double total, Producto producto, FacturaCabecera facturaCabecera) {
		this.codigo = codigo;
		this.cantida = cantida;
		this.total = total;
		this.producto = producto;
		this.facturaCabecera = facturaCabecera;
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCantida() {
		return cantida;
	}

	public void setCantida(int cantida) {
		this.cantida = cantida;
	}

	public double getTotal() {
		return producto.getPrecio()*this.cantida;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public FacturaCabecera getFacturaCabecera() {
		return facturaCabecera;
	}

	public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
		this.facturaCabecera = facturaCabecera;
	}
	

}
