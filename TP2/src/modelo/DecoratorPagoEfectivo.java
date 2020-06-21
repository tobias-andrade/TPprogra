package modelo;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa al abonado con metodo de pago efectivo
 */
public class DecoratorPagoEfectivo extends DecoratorPago{

	/**
	 * Constructor de la clase<br>
	 * @param encapsulado : es el abonado del cual se requeriran todas las acciones, este puede ser PersonaFisica o PersonaJuridica
	 */
	public DecoratorPagoEfectivo(IAbonado encapsulado) {
		super(encapsulado);
	}
}
