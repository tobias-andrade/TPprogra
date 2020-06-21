package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import estados.State;
import excepciones.ContratacionException;
import excepciones.DomicilioEIdentificacionException;
import excepciones.ServicioInexistenteException;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa al decorador de abonados, en este caso el decorador son los tipos de pago del encapsulado
 */
public abstract class DecoratorPago implements IAbonado{

	protected IAbonado encapsulado;

	/**
	 * Contructor de la clase<br>
	 * @param encapsulado : es el abonado del cual se requeriran todas las acciones, este puede ser PersonaFisica o PersonaJuridica
	 */
	public DecoratorPago(IAbonado encapsulado) {
		super();
		this.encapsulado = encapsulado;
	}

	/**
	 *Getter del nombre de un abonado, se lo delega a su encapsulado
	 */
	@Override
	public String getNombre() {
		return encapsulado.getNombre();
	}

	/**
	 *Getter del DNI de un abonado, se lo delega a su encapsulado
	 */
	@Override
	public String getDni() {
		return encapsulado.getDni();
	}

	@Override
	public void setDomicilios(Domicilio domicilio) {
		encapsulado.setDomicilios(domicilio);
	}

	/**
	 *Setter del tipo de pago de un abonado, solo se usa al crear uno y se lo delega a su encapsulado
	 */
	public void setPago(String pago)
	{
		encapsulado.setPago(pago);
	}
	
	/**
	 *Getter de la lista de contrataciones de un abonado, se lo delega a su encapsulado
	 */
	@Override
	public ArrayList<Contratacion> getContrataciones() {
		return encapsulado.getContrataciones();
	}

	/**
	 *Setter de una contratacion de un abonado, se lo delega a su encapsulado
	 */
	@Override
	public void setContrataciones(Contratacion contratacion) {
		encapsulado.setContrataciones(contratacion);
	}

	@Override
	public void borroDomicilio(Domicilio domicilio) {
		encapsulado.borroDomicilio(domicilio);
	}

	public ArrayList<Domicilio> getDomicilios()
	{
		return encapsulado.getDomicilios();
	}
	
	/**
	 *Metodo que revisa que no exista contratacion con el mismo numero de identificacion o que el domicilio no posea ya una contratacion, se lo delega a su encapsulado<br>
	 *Puede propagar una excepcion
	 */
	@Override
	public boolean revisarContrataciones(Domicilio domicilio, int identificacion)
			throws DomicilioEIdentificacionException {
		return encapsulado.revisarContrataciones(domicilio, identificacion);
	}

	/**
	 *Metodo que agrega una contratacion a la lista de contrataciones de un abonado, se lo delega a su encapsulado
	 */
	@Override
	public void realizarContratacion(Contratacion contratacion,Domicilio domicilio, int id) throws DomicilioEIdentificacionException{
		encapsulado.realizarContratacion(contratacion,domicilio,id);
		
	}

	/**
	 *Metodo que realiza el descuento o la ncrementacion del total a pagar de una factura dependiendo el tipo de abonado
	 */
	@Override
	public double realizarDescuentoOIncremento(double monto) {
		return encapsulado.realizarDescuentoOIncremento(monto);
	}

	/**
	 *Metodo que calcula el total a pagar en una factura de un abonado
	 */
	public double calcularTotal() {
		return encapsulado.calcularTotal();
	}


	/**
	 *Metodo que elimina una contratacion de la lista de contrataciones de un abonado, se lo delega a su encapsulado
	 */
	@Override
	public void eliminarContratacion(Contratacion contratacion) {
		encapsulado.eliminarContratacion(contratacion);
	}

	public ArrayList<Factura> getFacturas() {
		return this.encapsulado.getFacturas();
	}

	/**
	 *Metodo que agrega factura a la lista de acturs de un abonado, se lo delega a su encapsulado
	 */
	public void agregarFactura(Factura factura) {
		this.encapsulado.agregarFactura(factura);;
	}
	
	/**
	 *Metodo que paga la factura las antigua de la lista de facturas de un abonado, se lo delega a su encapsulado
	 */
	public void pagarFactura()
	{
		encapsulado.pagarFactura();
	}
	
	/**
	 *Este metodo cambia el estado del abonado
	 *@param estado: el nuevo estado que se le atribuira al abonado
	 */
	public void cambiarEstado(String estado)
	{
		encapsulado.cambiarEstado(estado);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		DecoratorPago clon=(DecoratorPago) super.clone();
		if (this.encapsulado!=null)
			clon.encapsulado=(Abonado) this.encapsulado.clone();
		return clon;
	}

	@Override
	public String toString() {
		return encapsulado.toString();
	}
	
	
}
