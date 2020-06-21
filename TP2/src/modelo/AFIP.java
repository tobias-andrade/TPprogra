package modelo;

import java.util.ArrayList;

import javax.swing.JFrame;

import util.Util;
import vista.IVista;
import vista.VentanaSistema;

/**
 * @author Federico,Gaston,Tobias <br>
 *Thread que rerpesenta la visita a afip, obtiene todas las facturs clonadas y abre la ventana de la visita
 */
public class AFIP extends Thread{

	private Sistema sistema;
	private ArrayList<Factura> facturas;
	private IVista ventana=null;
	
	/**
	 * Constructor de la clase
	 * @param ventana : se pasa la ventana del sistema para saber que se debe seguir enviando a AFIP mientras el sistema este en curso
	 */
	public AFIP(IVista ventana) {
		this.sistema=Sistema.getInstance();
		this.ventana=ventana;
	}

	@Override
	public void run() {
		while (ventana.isEnabled())
		{
			Util.esperaFija(30000);
			facturas=sistema.visitaAfip(ventana.getBotonIncorporar());
		}
		
	}
	
}
