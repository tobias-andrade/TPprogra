package modelo;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa el decorador de abonado que paga con cheque
 */
public class DecoratorPagoCheque extends DecoratorPago{

	/**
	 * Contructor de la clase<br>
	 * @param encapsulado: tipo de abonado que tendra este metodo de pago
	 */
	public DecoratorPagoCheque(IAbonado encapsulado) {
		super(encapsulado);
	}


}
