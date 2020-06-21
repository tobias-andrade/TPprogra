package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.Sistema;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa la ventana de AFIP que aparece cada 30 segundos despues de su ultima visita ientras el programa esta en curso
 */
public class VentanaAfip extends JFrame {

	private JTextArea area = new JTextArea();
	private JButton boton=null;
	
	/**
	 * Constructor de la clase
	 * @param boton: se le pasa el boton incorporar para bloquearlo mientras esta ventata esta en curso
	 */
	public VentanaAfip(JButton boton)
    {
		this.boton=boton;
		JScrollPane scroll=new JScrollPane(area);
		this.getContentPane().add(scroll);
		this.setVisible(true);
		this.setTitle("AFIP");
		this.setBounds(100, 50, 1000, 650);
    }

	/**
	 *Metodo que ademas de indicar si la ventana es visible o no, habilita o deshabilita el boton de incorporar abonado al sistema
	 */
	@Override
	public void setVisible(boolean b)
	{
		if (b)
		{
			Sistema.getInstance().setRecursoCompartido(true);
			boton.setEnabled(false);
		}
		else
		{
			Sistema.getInstance().terminaVisitaAfip();
			boton.setEnabled(true);
		}
		super.setVisible(b);
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}

}
