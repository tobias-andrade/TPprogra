package estados;

import excepciones.DomicilioEIdentificacionException;
import modelo.Contratacion;
import modelo.Domicilio;

/**
 * @author Federico,Gaston,Tobias <br>
 *Interface que implementaran los estados de los abonados, para tener herencia de tipo
 */
public interface IState {

	public void realizarContratacion(Contratacion contratacion,Domicilio domicilio, int id) throws DomicilioEIdentificacionException;
	
	public void eliminarContratacion(Contratacion contratacion);
	
	public void pagarFactura();
}
