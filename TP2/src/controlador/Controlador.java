package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import javax.swing.JOptionPane;

import modelo.AFIP;
import modelo.Contratacion;
import modelo.Domicilio;
import modelo.Factura;
import modelo.IAbonado;
import modelo.Internet100;
import modelo.Internet500;
import modelo.Sistema;
import observadores.ActualizadorDeDatos;
import observadores.GestorFacturacion;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import vista.IVista;
import vista.VentanaSistema;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa al controlador, se encarga de independizar la vista del modelo
 */
public class Controlador implements ActionListener {

	private IVista vista;
	private IPersistencia<Serializable> persistencia=new PersistenciaBIN();
	private Sistema sistema;
	
	/**
	 * Constructor de la clase, no necesita parametros y se encarga de crear la ventana
	 */
	public Controlador()
	{
		GestorFacturacion gestor= new GestorFacturacion();
		ActualizadorDeDatos actualizador= new ActualizadorDeDatos();
		
		//En caso de querer volver a empezar con un nuevo sistema descomentar este sector y comentar el try-catch siguiente
		
		/*sistema=Sistema.getInstance();
		this.vista=new VentanaSistema(this);
		refrescaListas();
		AFIP afip=new AFIP(vista);
		afip.start();*/
		
		try {
			persistencia.abrirInPut("sistema.bin");
			sistema=(Sistema) persistencia.leer();
			persistencia.cerrarInPut();
			sistema.setInstance(sistema);
			this.vista=new VentanaSistema(this);
			refrescaListas();
			AFIP afip=new AFIP(vista);
			afip.start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error al abrir datos del sistema en archivo");
		} catch (ClassNotFoundException e) {
			
		}
		gestor.agregarObservable(sistema.getEpt());
		actualizador.agregarObservable(sistema.getEpt());
	}
	
	/**
	 * Metodo que refresca la lista de los abonados en la ventana
	 */
	public void refrescarListaAbonado()
	{
		vista.getListModelAbonado().clear();
		Iterator<IAbonado> it = sistema.getAbonados().iterator();
		while (it.hasNext())
		{	
			vista.getListModelAbonado().addElement(it.next());
		}
	}

	/**
	 * Metodo que refresca la lista de los domicilios al cambiar de abonado seleccionado
	 */
	public void refrescarListaDomicilio()
	{
		vista.getListModelDomicilio().clear();
		Iterator<Domicilio> it = vista.getAbonado().getDomicilios().iterator();
		while (it.hasNext())
		{	
			vista.getListModelDomicilio().addElement(it.next());
		}
	}
	
	/**
	 * Metodo que refresca la lista de las contrataciones al seleccionar un abonado
	 */
	public void refrescarListaContratacion()
	{
		this.vista.getListModelContratacion().clear();
		Iterator<Contratacion> it = vista.getAbonado().getContrataciones().iterator();
		while (it.hasNext())
		{	
			vista.getListModelContratacion().addElement(it.next());
		}
	}
	
	/**
	 * Metodo que refresca la lista de las facturas al seleccionar un nuevo abonado
	 */
	public void refrescarListaFactura()
	{
		vista.getListModelFactura().clear();
		Iterator<Factura> it = vista.getAbonado().getFacturas().iterator();
		while (it.hasNext())
		{	
			vista.getListModelFactura().addElement(it.next());
		}
	}
	
	/**
	 * Metodo que refresca las listas de todos los abonados, domicilios, contrataciones y facturas luego de cada accion en la ventana
	 */
	public void refrescaListas() {
		refrescarListaAbonado();
		if (vista.getAbonado()!=null)
		{	
			refrescarListaDomicilio();
			refrescarListaContratacion();
			refrescarListaFactura();
		}
		vista.repaint();
		vista.getTextAreaMes().setText(sistema.getEpt().getMes()+"");
		try {
			persistencia.abrirOutPut("sistema.bin");
			persistencia.escribir(sistema);
			persistencia.cerrarOutPut();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error al guardar los datos del sistema en archivo");
		}
	}
	
	/**
	 *Metodo actionPerformed que indica que accion se debe realizar por cada accion que ocurre en la ventana
	 */
	public void actionPerformed(ActionEvent e) {
		if (vista.getAbonado()!=null && sistema.getAbonados()!=null)
		{
			if (e.getActionCommand().equals("Pagar"))
				sistema.pagarFactura(vista.getAbonado());
			else if (e.getActionCommand().equals("Agregar Domicilio"))
			{
				String direccion=vista.getTextFieldDireccion().getText();
				if (sistema.getAbonados().size()>0)
					sistema.agregaDomicilio(vista.getAbonado(), direccion);
				else
					JOptionPane.showMessageDialog(null,"No hay abonados en el sistema para agregarle un domicilio");
			}
			else if (e.getActionCommand().equals("Desvincular"))
				sistema.eliminarAbonado(vista.getAbonado());
			else if (e.getActionCommand().equals("Añadir"))
				agregarServicioAContratacion();
			else if (e.getActionCommand().equals("Eliminar"))
			{
				Contratacion contratacion=vista.getListaContratacion().getSelectedValue();
				sistema.eliminarContratacion(vista.getAbonado(),contratacion);
			}
			else if (e.getActionCommand().equals("Agregar"))
				realizarContratacion();
		}
		if (e.getActionCommand().equals("Avanzar Mes"))
			sistema.incrementarMes();
		else
			if (e.getActionCommand().equals("Incorporar"))
				incoorporarAbonado();
			else if (vista.getAbonado()==null)
				JOptionPane.showMessageDialog(null,"Debe seleccionar un abonado de la lista para realizar operacion");
		refrescaListas();
	}
	
	/**
	 * Metodo que agrega una contratacion a la lista de contrataciones de un abonado si se dan las condiciones
	 */
	public void realizarContratacion()
	{
		Domicilio domicilio=vista.getListaDomicilio().getSelectedValue();
		if (domicilio!=null)
		{
			Contratacion contratacion=null;
			int ident= Integer.parseInt(vista.getTextFieldNumeroIdentificacion().getText());
			if (vista.getRadioButtonInternet100().isSelected())
				contratacion= new Internet100(ident, domicilio);
			else
				if (vista.getRadioButtonInternet500().isSelected())
					contratacion=new Internet500(ident, domicilio);
			sistema.realizarContratacion(vista.getAbonado(),domicilio,ident,contratacion);
		}
		else
			JOptionPane.showMessageDialog(null,"Debe seleccionar un domicilio para añadirle una contratacion");
	}
	
	/**
	 * Metodo que agrega un servicio a una contrataicon indicada en la ventana, en caso de no seleccionar ninguna contratacion se notifica por pantalla
	 */
	public void agregarServicioAContratacion()
	{
		Contratacion contratacion=vista.getListaContratacion().getSelectedValue();
		String cant=null;
		if (contratacion!=null)
		{
			cant=vista.getTextFieldCantidadServicios().getText();
			if (!cant.equals(""))
			{	
				int cantidad=Integer.parseInt(cant);
				if (cantidad>0)
				{
					if (vista.getRadioButtonCelular().isSelected())
						sistema.agregaCelular(contratacion, cantidad);
					else
						if (vista.getRadioButtonTvCable().isSelected())
							sistema.agregaTvCable(contratacion, cantidad);
						else
							if (vista.getRadioButtonTelefono().isSelected())
								sistema.agregaTelefono(contratacion, cantidad);	
							else
								JOptionPane.showMessageDialog(null,"Debe seleccionar un servicio (Celular, Telefono o TvCable)");
				}
				else
					JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a 0 obligatoriamente");
			}
			else
				JOptionPane.showMessageDialog(null,"Debe ingresar cantidad de servicios a añadir");
		}
		else
			JOptionPane.showMessageDialog(null,"Debe seleccionar una contratacion para añadirle un servicio");
	}
	
	/**
	 * Metodo que incorpora un abonado al sistema si se completan todos los datos pedidos en la ventana
	 */
	public void incoorporarAbonado()
	{
		String nombre=vista.getTextFieldNombre().getText();
		String dni=vista.getTextFieldDNI().getText();
		String tipo=null;
		String pago=null;
		if (vista.getRadioButtonFisica().isSelected())
			tipo="Fisica";
		else
			if (vista.getRadioButtonJuridica().isSelected())
				tipo="Juridica";
		if (vista.getRadioButtonTarjeta().isSelected())
			pago="Tarjeta";
		else if (vista.getRadioButtonEfectivo().isSelected())
			pago="Efectivo";
		else
			if (vista.getRadioButtonCheque().isSelected())
				pago="Cheque";
		if (!nombre.equals("") && !dni.equals("") && tipo!=null && pago!=null)
			sistema.agregarAbonado(nombre, dni, tipo, pago);
		else
			JOptionPane.showMessageDialog(null,"Debe completar los datos para incoorporar un aboando al sistema");
	}
}
