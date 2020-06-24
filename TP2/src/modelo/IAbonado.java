package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.DomicilioEIdentificacionException;

/**
 * @author Federico,Gaston,Tobias <br>
 *Interface que implementaran los abonados, parte del patron decorator y se realiza el double dispatch para delegar acciones a encapsulados
 */
public interface IAbonado extends Cloneable,Serializable,IColeccionFacturas{

	public String getNombre();
	
	public String getDni();
	
	public void setDomicilios(Domicilio domicilio);
	
	public ArrayList<Contratacion> getContrataciones();
	
	public ArrayList<Domicilio> getDomicilios();
	
	public void setContrataciones(Contratacion contratacion);
	
	public void borroDomicilio(Domicilio domicilio);
	
	public boolean revisarContrataciones(Domicilio domicilio, int identificacion) throws DomicilioEIdentificacionException;
	
	public void realizarContratacion(Contratacion contratacion,Domicilio domicilio, int id) throws DomicilioEIdentificacionException;
	
	public abstract double realizarDescuentoOIncremento(double monto);
	
	public void setPago(String pago);
	
	public void eliminarContratacion(Contratacion contratacion);
	
	public String toString();
	
	public Object clone() throws CloneNotSupportedException;
	
	public void pagarFactura();
	
	public void cambiarEstado(String estado);
	
	public double calcularTotal();
}
