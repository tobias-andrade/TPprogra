package estados;


import modelo.IAbonado;


/**
 * @author Federico,Gaston,Tobias <br>
 *Representa el estado con contratacion de un abonado
 */
public class ConContratacionState extends State {

	/**
	 * COnstructor de la clase
	 * @param abonado : parametro que indica el abonado al cual se le asignara este estado
	 */
	public ConContratacionState(IAbonado abonado) {
		super(abonado);
	}

}
