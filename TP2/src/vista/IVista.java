package vista;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Contratacion;
import modelo.Domicilio;
import modelo.Factura;
import modelo.IAbonado;
import modelo.Sistema;

/**
 * @author Federico,Gaston,Tobias <br>
 *Interface que implementara la ventana principal del sistema para que el controlador le solicite los datos correspondientes para poder realizar las acciones correspondientes
 */
public interface IVista {

	public void repaint();
	
	public boolean isEnabled();
	
	public JButton getBotonIncorporar();
	
	public void setVisible(boolean b);
	
	public DefaultListModel<IAbonado> getListModelAbonado();
	
	public DefaultListModel<Domicilio> getListModelDomicilio();
	
	public DefaultListModel<Contratacion> getListModelContratacion();
	
	public DefaultListModel<Factura> getListModelFactura();
	
	public IAbonado getAbonado();
	
	public JTextArea getTextAreaMes();
	
	public JTextField getTextFieldNombre();
	
	public JTextField getTextFieldDNI();
	
	public JTextField getTextFieldCantidadServicios();
	
	public JTextField getTextFieldDireccion();
	
	public JTextField getTextFieldNumeroIdentificacion();
	
	public JRadioButton getRadioButtonEfectivo();
	
	public JRadioButton getRadioButtonCheque();
	
	public JRadioButton getRadioButtonTarjeta();
	
	public JRadioButton getRadioButtonInternet100();
	
	public JRadioButton getRadioButtonInternet500();
	
	public JRadioButton getRadioButtonCelular();
	
	public JRadioButton getRadioButtonTvCable();
	
	public JRadioButton getRadioButtonTelefono();
	
	public JRadioButton getRadioButtonFisica();
	
	public JRadioButton getRadioButtonJuridica();
	
	public JList<Contratacion> getListaContratacion();
	
	public JList<Domicilio> getListaDomicilio();
	
}
