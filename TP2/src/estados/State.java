package estados;

import java.io.Serializable;

import javax.swing.JOptionPane;

import excepciones.DomicilioEIdentificacionException;
import modelo.Contratacion;
import modelo.Domicilio;
import modelo.IAbonado;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa a los estados e implementa la interfaz IState
 */
public abstract class State implements Cloneable,Serializable,IState{

	IAbonado abonado;
	
	/**
	 * Constructor de la clase State
	 * @param abonado: representa el abonado al cual se lo cambiara de estado
	 */
	public State(IAbonado abonado)
	{
		this.abonado=abonado;
	}
	
	/**
	 *Metodo para realizar contratacion de un abonado, ya esta verificado que la contratacion y el domicilio no sean nulos, este metodo solo se encarga de la accion
	 */
	public void realizarContratacion(Contratacion contratacion,Domicilio domicilio, int id) throws DomicilioEIdentificacionException
	{
		if (abonado.revisarContrataciones(domicilio, id))
		{
			abonado.setContrataciones(contratacion);
			abonado.cambiarEstado("ConContratacion");
		}
	}
	
	/**
	 *Metodo que elimina contratacion de la lista de contrataciones de un aboando<br>
	 *@param contratacion : es la contratacion de un abonado que se eliminara
	 */
	public void eliminarContratacion(Contratacion contratacion) {
		if (contratacion!=null)
		{	
			Domicilio domicilio=contratacion.getDomicilio();
			int i = 0;
			while ((i < abonado.getContrataciones().size()) && !(domicilio.equals(abonado.getContrataciones().get(i).getDomicilio())))
				i++;
			if ((i < abonado.getContrataciones().size()) && (domicilio.equals(abonado.getContrataciones().get(i).getDomicilio()))) {
				this.abonado.getContrataciones().remove(i);
			}
			if (abonado.getContrataciones().size()==0)
				abonado.cambiarEstado("SinContratacion");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Debe seleccionar una contratacion para eliminar");
		}
	}
	
	/**
	 *Metodo que paga la factura de un abonado, este pago se hace sobre la primer factura del abonado, es decir que antes de pagar una factura reciente, el abonado debe pagar desde la factura mas antigua en adelante para no perder el orden
	 */
	public void pagarFactura()
	{
		if (abonado.getFacturas().size()>0)
			abonado.getFacturas().remove(0);
		else
			JOptionPane.showMessageDialog(null,"Abonado no tiene facturas que pagar");
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
