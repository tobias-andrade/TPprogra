package modelo;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa el decorador de pago con tarjeta de un abonado
 */
public class DecoratorPagoTarjeta extends DecoratorPago{

	/**
	 * Constructor de la clase<br>
	 * @param encapsulado : abonado que sera el tipo, PersonaFisica o PersonaJuridica
	 */
	public DecoratorPagoTarjeta(IAbonado encapsulado) {
		super(encapsulado);
	}


}
