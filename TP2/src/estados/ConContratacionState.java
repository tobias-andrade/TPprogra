package estados;

import excepciones.ContratacionException;
import excepciones.DomicilioEIdentificacionException;
import excepciones.ServicioInexistenteException;
import modelo.Contratacion;
import modelo.Domicilio;
import modelo.IAbonado;
import modelo.Internet100;
import modelo.Internet500;

/**
 * @author Federico,Gaston,Tobias <br>
 *Representa el estado con contratacion de un abonado
 */
public class ConContratacionState extends State {

	/**
	 * COnstructor de la clase
	 * @param abonado: parametro que indica el abonado al cual se le asignara este estado
	 */
	public ConContratacionState(IAbonado abonado) {
		super(abonado);
	}

}
