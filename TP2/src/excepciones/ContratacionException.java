package excepciones;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa la excepcion lanzada cuando un domicilio seleccionado no posee una contratacion para eliminar
 */
public class ContratacionException extends Exception{

	/**
	 * Constructor de la clase sin parametros que le asigna un mensaje directo
	 */
	public ContratacionException() {
		super("El domicilio no posee contratacion para eliminar");
	}
}
