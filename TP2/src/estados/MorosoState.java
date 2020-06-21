package estados;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import excepciones.ContratacionException;
import excepciones.DomicilioEIdentificacionException;
import excepciones.ServicioInexistenteException;
import modelo.Contratacion;
import modelo.Domicilio;
import modelo.IAbonado;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase moroso state que representa el estado moroso de un abonado fisico unicamente
 */
public class MorosoState extends State {


	/**
	 * Constructor de la clase MorosoState
	 * @param abonado : representa el abonado al cual se le atribuye este estado
	 */
	public MorosoState(IAbonado abonado) {
		super(abonado);
		JOptionPane.showMessageDialog(null,"Abonado "+abonado.getNombre()+" en estado Moroso durante el mes en curso por falta de pago, recargo del 30% en su proxima factura a pagar");
		double costo=abonado.getFacturas().get(0).getCosto();
		double costoModificado=abonado.getFacturas().get(0).getCostoConModificacion();
		abonado.getFacturas().get(0).setCosto(costo*1.3);
		abonado.getFacturas().get(0).setCostoConModificacion(costoModificado*1.3);
	}

	/**
	 *Metodo heredado para realizar contratacion, solo muestra un mensaje por pantalla
	 */
	@Override
	public void realizarContratacion(Contratacion contratacion, Domicilio domicilio, int id)
	{
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null,"No se puede realizar contratacion, estado moroso hasta no deber dos facturas consecutivas");
		
	}

	/**
	 *etodo heredado para eliminar contratacion, solo muestra mensaje por pantalla
	 */
	@Override
	public void eliminarContratacion(Contratacion contratacion) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null,"No se puede eliminar contratacion, estado moroso");
	}

}
