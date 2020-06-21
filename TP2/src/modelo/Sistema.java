package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import excepciones.AbonadoException;
import excepciones.DomicilioEIdentificacionException;
import util.Util;
import vista.VentanaAfip;

/**
 * @author Federico,Gaston,Tobias <br>
 *         Clase principal, representa el sistema de servicios que contiene
 *         todas las instrucciones, para realizar cualquier tipo de operacion
 *         debera trabajrse con esta clase y esta se encargara de realizar too
 *         tipo de tareas y de la comunicacion
 */
public class Sistema implements Serializable{

	private static Sistema instance;
	private EPT ept= new EPT();
	private ArrayList<IAbonado> abonados = new ArrayList<IAbonado>();
	private boolean recursoCompartido=false;
	
	private Sistema()
	{
		
	}

	/**
	 * Constructor de la clase, se utiliza patron Singleton porque solo puede haber
	 * una instancia del sistema
	 * 
	 * @return la instancia del sistema
	 */
	public static Sistema getInstance() {
		if (instance == null)
		{
			instance=new Sistema();
		}
		return instance;
	}
	
	/**
	 * Metodo que crea a un abonado, y luego lo añade al sistema, este proceso puede tardar unos segundos por lo tanto se lo separa en partes para aprovechar concurrencia<br>
	 * 
	 * @param nombre : parametro de tipo String que sera el nombre del abonado
	 * @param dni    : parametro de tipo String que sera el dni del abonado
	 * @param tipo   : parametro de tipo String que sera el tipo de persona del
	 *               abonado
	 * @param pago   : parametro de tipo String que sera la forma de pago del
	 *               abonado<br>
	 *               <b>Pre:</b>El tipo de persona debe ser o Fisica o Juridica
	 *               unicamente. En cuanto al pago debe ser: Trajeta, Cheque o
	 *               efectivo. Y por ultimo el DNI debe ser un numero acorde a la documentacion actual<br>
	 *               <b>Post:</b>El resultadp sera la insercion del abonado al
	 *               sistema y la creacion de su factura
	 */
	public void agregarAbonado(String nombre, String dni, String tipo, String pago)
	{
		
		IAbonado abonado = AbonadoFactory.factory(nombre, dni, tipo, pago);
		comenzarAAgregarAbonado();
		Util.espera(6000);//simula tiempo de ingresar abonado
		this.abonados.add(abonado);
		terminaDeIngresarseAbonado();
	}
	
	/**
	 * Metodo sincronizado que comienza la tarea de agregar un abonado al sistema y toma el recurso compartido
	 */
	public synchronized void comenzarAAgregarAbonado()
	{
		JOptionPane.showMessageDialog(null,"Dando de alta abonado, agregando al sistema (esto puede tardar unos segundos)");
		recursoCompartido=true;
		notifyAll();
	}
	
	/**
	 * Metodo sincronizado que concluye la entrada del abonado al sistema y libera el recurso compartido
	 */
	public synchronized void terminaDeIngresarseAbonado()
	{
		this.recursoCompartido=false;
		notifyAll();
	}
	
	/**
	 * Metodo que le indica a un abonado que pague su factura<br>
	 * @param abonado : abonado que realizara la tarea de pagar la factura
	 */
	public void pagarFactura(IAbonado abonado)
	{
		abonado.pagarFactura();
	}
	
	/**
	 * Metodo que incrementa mes, le delega la accion al EPT del sistema
	 */
	public void incrementarMes()
	{
		ept.incrementarMes();
	}

	/**
	 * Metodo que settea true o false al recurso compartido, dependiendo si este esta en uso<br>
	 * @param recursoCompartido : recurso de la zona critica que debe ser cuidado
	 */
	public void setRecursoCompartido(boolean recursoCompartido) {
		this.recursoCompartido = recursoCompartido;
	}

	public EPT getEpt() {
		return ept;
	}

	public void setEpt(EPT ept) {
		this.ept = ept;
	}

	/**
	 * Metodo que se encargara de agregar celulares a una contratacion
	 * 
	 * @param contratacion : contratacion a la cual se le agregara un servicio de celular<br>
	 * @param cantidad      : cantidad de celulares a agregar <br>
	 *                  Este metodo en caso de producirse algun error capta las
	 *                  excepciones y muestra el mensaje por pantalla
	 */
	public void agregaCelular(Contratacion contratacion, int cantidad) {
		contratacion.agregarCelular(cantidad);
	}

	/**
	 * Metodo que se encargara de listar todas las facturas del sistema
	 */
	public void listarTodasFacturasDelSistema() {
		Iterator<IAbonado> it = this.getAbonados().iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	/**
	 * Metodo que elimina una contratacion
	 * 
	 * @param abonado    : representa el abonado al cual
	 *                  se le desea eliminar una contratacion<br>
	 * @param contratacion : la contratacion que sera eliminada
	 */
	public void eliminarContratacion(IAbonado abonado, Contratacion contratacion) {
			abonado.eliminarContratacion(contratacion);
	}

	/**
	 * Metodo que comienza la visita de AFIP al sistema, si el recurso compartido esta en uso, se espera a que sea liberado
	 */
	public synchronized void esperaParaComenzarVisitaAfip()
	{		
		while (recursoCompartido)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		recursoCompartido=true;
		notifyAll();
	}
	
	/**
	 * Metodo que realiza la visita de AFIP<br>
	 * @param boton : boton incorporar que se pasa para que al abrirse la ventana de AFIP sea bloqueado, para no poder ingresar ningun abonado al sistema mientras AFIP esta de visita<br>
	 * @return el retorno es la lista con todas las facturas del sistema clonadas
	 */
	public ArrayList<Factura> visitaAfip(JButton boton)
	{
		ArrayList<Factura> respuesta= new ArrayList<Factura>();
		if (recursoCompartido)
			JOptionPane.showMessageDialog(null,"AFIP hara su visita al terminar de ingresar abonado en curso");
		esperaParaComenzarVisitaAfip();
		VentanaAfip afip=new VentanaAfip(boton);
		Iterator<IAbonado> it= abonados.iterator();
		while (it.hasNext())
		{
			Iterator<Factura> it2= it.next().getFacturas().iterator();
			while (it2.hasNext())
			{
				Factura aux=null;
				try {
					aux= (Factura) it2.next().clone();
					respuesta.add(aux);
					afip.getArea().append(aux.toString());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		if (respuesta.size()==0)
			afip.getArea().append("NO HAY FACTURAS EN EL SISTEMA");
		Util.esperaFija(12000);
		afip.setVisible(false);
		terminaVisitaAfip();
		return respuesta;
	}
	
	/**
	 * Metodo que concluye con la visita de AFIP al sistema
	 */
	public synchronized void terminaVisitaAfip()
	{
		recursoCompartido=false;
		notifyAll();
	}
	
	/**
	 * Metodo que muestra la factura de un abonado especifico
	 * 
	 * @param nombre : parametro de tipo String que sera el nombre del abonado del
	 *               cual se desea mostrar su factura <br>
	 *               Si el nombre del abonado no esta en el sistema se mostrara el
	 *               error
	 */
	public void mostrarFacturaDe(String nombre) {
		IAbonado factura;
		try {
			factura = this.buscaAbonado(nombre);
			System.out.println(factura);
		} catch (AbonadoException e) {
			System.out.println(e.getMessage() + ", no se puede mostrar una factura inexistente");
		}
	}

	/**
	 * Metodo que se encargara de agregar telefonos a una contratacion
	 * 
	 * @param contratacion : contratacion a la cual se le agregara un servicio de telefono<br>
	 * @param cantidad      : cantidad de telefonos a agregar <br>
	 *                  Este metodo en caso de producirse algun error capta las
	 *                  excepciones y muestra el mensaje por pantalla
	 */
	public void agregaTelefono(Contratacion contratacion,int cantidad) { // DOCUMENTAR
		contratacion.agregarTelefono(cantidad);
	}

	/**
	 * Metodo que se encargara de agregar TvCable a una contratacion
	 * 
	 * @param contratacion : contratacion a la cual se le agregara un servicio de TvCable<br>
	 * @param cantidad      : cantidad de TvCable a agregar <br>
	 *                  Este metodo en caso de producirse algun error capta las
	 *                  excepciones y muestra el mensaje por pantalla
	 */
	public void agregaTvCable(Contratacion contratacion, int cantidad) {
		contratacion.agregarTvCable(cantidad);
	}

	public void eliminarAbonado(IAbonado abonado)
	{
		if (abonados.size()>0)
			abonados.remove(abonado);
		else
			JOptionPane.showMessageDialog(null,"No hay abonados en el sistema para eliminar");
	}
	
	public ArrayList<IAbonado> getAbonados() {
		return abonados;
	}

	/**
	 * Metodo que agrega un domicilio a un abonado
	 * 
	 * @param abonado    : parametro de tipo IAboando que representa el
	 *                  abonado al cual se le agregara un domicilio<br>
	 * @param direccion : parametro de tipo String que representa la direccion del
	 *                  domicilio a crear <br>
	 *                  Si no se encuentra un abonado en el sistema con el nombre
	 *                  pasado por parametro se notificara
	 */
	public void agregaDomicilio(IAbonado abonado, String direccion) { // DOCUMENTAR
		Domicilio domicilio = new Domicilio(direccion);
			abonado.setDomicilios(domicilio);
	}

	/**
	 * Metodo que realiza la contratacion y la añade a la lista de contrataciones de un abonado<br>
	 * @param abonado : abonado al cual se le añadira una contratacion<br>
	 * @param domicilio : domicilio al cual se le querra añadir la contratacion<br>
	 * @param id : numero de identificaion de la nueva contratacion<br>
	 * @param contratacion : contratacion a añadir en la lista del abonado
	 */
	public void realizarContratacion(IAbonado abonado,Domicilio domicilio, int id, Contratacion contratacion) {
		try {
			abonado.realizarContratacion(contratacion,domicilio,id);
		} catch (DomicilioEIdentificacionException e) {
			if (e.isElProblemaDomicilio())
				JOptionPane.showMessageDialog(null,"El domicilio requerido ya tiene contratacion, no se puede agregar otra a este");
			else
				JOptionPane.showMessageDialog(null,"Ya hay una contratacion con el numero de identificacion ingresado");
		}
	}

	/**
	 * Metodo que clonara a una factura especifica
	 * 
	 * @param nombre : parametro de tipo String que representa el nombre del abonado
	 *               al cual se le desea duplicar su factura
	 * @return el resultado sera una duplicacion de la factura del abonado que se
	 *         desee <br>
	 *         En caso de no poder ser clonada, se notificara por pantalla
	 */
	public IAbonado clonarFactura(String nombre) {
		IAbonado clonada = null, aux = null;
		try {
			aux = buscaAbonado(nombre);
			clonada = (IAbonado) aux.clone();
		} catch (AbonadoException e) {
			System.out.println(e.getMessage() + " para clonar su factura");
		} catch (CloneNotSupportedException e) {
			System.out.println("La factura de " + nombre + " no puede ser clonada\n");
		}
		return clonada;
	}

	/**
	 * Metodo que settea la instancia del sistema, sirve unicamente para la apertura del sistema<br>
	 * @param instance : sistema el cual implementa Patron Singleton
	 */
	public void setInstance(Sistema instance)
	{
		this.instance=instance;
	}

	/**
	 * Metodo que busca a un abonado en el sistema por su nombre
	 * 
	 * @param nombre : parametro de tipo String que representa el nombre del abonado
	 *               a buscar
	 * @return el retorno es el abonado con dicho nombre
	 * @throws AbonadoException : se lanza en el caso de que el sistema no tenga un
	 *                          abonado con el nombre pasado por parametro
	 */
	private IAbonado buscaAbonado(String nombre) throws AbonadoException {
		IAbonado aux = null;
		int i = 0;
		while ((i < abonados.size()) && !(nombre.equals(abonados.get(i).getNombre())))
			i++;
		if ((i < abonados.size()) && (nombre.equals(abonados.get(i).getNombre())))
			aux = abonados.get(i);
		else
			throw new AbonadoException();
		return aux;
	}

}