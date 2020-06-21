package vista;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.Sistema;

public class VentanaAfip extends JFrame {

	private JTextArea area = new JTextArea();
	private JButton boton=null;
	
	public VentanaAfip(JButton boton)
    {
		this.boton=boton;
		JScrollPane scroll=new JScrollPane(area);
		this.getContentPane().add(scroll);
		this.setVisible(true);
		this.setTitle("AFIP");
		this.setBounds(100, 50, 1000, 650);
    }

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
