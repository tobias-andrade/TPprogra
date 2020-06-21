package estados;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import modelo.Contratacion;
import modelo.IAbonado;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa al estado sin contratacion de un abonado fisico unicamente
 */
public class SinContratacionState extends State{

	/**
	 * Constructor de la clase
	 * @param abonado : representa el abonado al cual se le atribuye este estado
	 */
	public SinContratacionState(IAbonado abonado) {
		super(abonado);
	}

	/**
	 *Metodo heredado para pagar factura, solo muestra mensaje por pantalla
	 */
	public void pagarFactura()
	{
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null,"No se puede pagar factura, estado SinContratacion");
	}
	
	/**
	 *Metodo heredado para eliminar contratacion, solo muestra mensaje por pantalla
	 */
	@Override
	public void eliminarContratacion(Contratacion contratacion) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null,"No se puede eliminar contratacion, estado SinContratacion");
	}

}
