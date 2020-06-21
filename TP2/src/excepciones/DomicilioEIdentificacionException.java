package excepciones;

/**
 * @author Federico,Gaston,Tobias <br>
 *         Clase que puede ser lanzada en dos tipos de ocaciones, cuando ya
 *         existe una contratacion precreada con el mismo domicilio o el mismo
 *         numero de indetificaion. Por otro lado puede lanzarse cuando se
 *         requiere eliminar una contratacion de un domicilio existente, pero
 *         este no contiene ninguna contratacion
 */
public class DomicilioEIdentificacionException extends Exception {

	private boolean elProblemaDomicilio;
	/**
	 * Contructor de la clase con parametro<br>
	 * @param bool : si es verdadero: el problema es que ya existe un mismo domicilio con contratacion, si es falso: significa que ya hay una contratacion con el numero de identificacion ingresado
	 */
	public DomicilioEIdentificacionException(boolean bool) {
		this.elProblemaDomicilio=bool;
	}

	/**
	 * Contructor con un parametro<br>
	 * 
	 * @param mensaje : parametro de tipo String con el mensaje que llevara la
	 *                excepcion
	 */
	public DomicilioEIdentificacionException(String mensaje) {
		super(mensaje);
	}

	public boolean isElProblemaDomicilio() {
		return elProblemaDomicilio;
	}

	public void setElProblemaDomicilio(boolean elProblemaDomicilio) {
		this.elProblemaDomicilio = elProblemaDomicilio;
	}

	
	
}
