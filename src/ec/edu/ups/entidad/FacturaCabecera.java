package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
public class FacturaCabecera implements Serializable{
	 private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int numero;
	 @Temporal(TemporalType.DATE)
	 private Date fecha;
	 @ManyToOne
	 private Cliente cliente;
	 @ManyToOne
	 private Empleado empleado;
	 private double subtotal;
	 private double total;
	 private double iva;
	 private String estado;
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaCabecera")
	 private List<FacturaDetalle> facturaDetalles;
	 
	public FacturaCabecera() {

		}

	public FacturaCabecera(Date fecha, Cliente cliente, Empleado empleado, double subtotal,
						   double total, double iva, String estado) {
		this.fecha = fecha;
		this.cliente = cliente;
		this.empleado = empleado;
		this.subtotal = subtotal;
		this.total = total;
		this.iva = iva;
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public double getSubtotal() {

		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<FacturaDetalle> getFacturaDetalles() {
		return facturaDetalles;
	}

	public void setFacturaDetalles(List<FacturaDetalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}


}
