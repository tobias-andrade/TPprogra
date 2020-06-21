package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Contratacion;
import modelo.Domicilio;
import modelo.Factura;
import modelo.IAbonado;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;

/**
 * @author Federico,Gaston,Tobias <br>
 * Ventana principal del sistema, en ella se realizara todo lo que se desee
 */
public class VentanaSistema extends JFrame implements MouseListener,IVista {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldDNI;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textFieldDireccion;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JTextField textFieldNumeroIdentificacion;
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private JTextField textFieldCantidadServicios;
	private JButton btnIncorporarAbonado = new JButton("Incorporar");
	private JRadioButton rdbtnFisica = new JRadioButton("Fisica");
	private JRadioButton rdbtnJuridica = new JRadioButton("Juridica");
	private JRadioButton rdbtnEfectivo = new JRadioButton("Efectivo");
	private JRadioButton rdbtnCheque = new JRadioButton("Cheque");
	private JRadioButton rdbtnTarjeta = new JRadioButton("Tarjeta");
	private JButton btnAgregarDomicilio = new JButton("Agregar Domicilio");
	private JButton btnDesvincularAbonado = new JButton("Desvincular");
	private JRadioButton rdbtnInternet100 = new JRadioButton("Internet100");
	private JRadioButton rdbtnInternet500 = new JRadioButton("Internet500");
	private JButton btnAgregarContratacion = new JButton("Agregar");
	private JButton btnEliminarContratacion = new JButton("Eliminar");
	private JRadioButton rdbtnCelular = new JRadioButton("Celular");
	private JRadioButton rdbtnTelefono = new JRadioButton("Telefono");
	private JRadioButton rdbtnTVCable = new JRadioButton("TV Cable");
	private JButton btnAvanzarMes = new JButton("Avanzar Mes");
	private JButton btnAnadirServicio = new JButton("Añadir");
	private JButton btnPagar = new JButton("Pagar");
	private JList<IAbonado> listaAbonado = new JList<IAbonado>();
	private DefaultListModel<IAbonado> listModelAbonado = new DefaultListModel<IAbonado>();
	private JList<Domicilio> listaDomicilio = new JList<Domicilio>();
	private DefaultListModel<Domicilio> listModelDomicilio = new DefaultListModel<Domicilio>();
	private JList<Contratacion> listaContratacion = new JList<Contratacion>();
	private DefaultListModel<Contratacion> listModelContratacion = new DefaultListModel<Contratacion>();
	private JList<Factura> listaFactura = new JList<Factura>();
	private DefaultListModel<Factura> listModelFactura = new DefaultListModel<Factura>();
	private JTextArea textAreaMes = new JTextArea();
	private IAbonado abonado=null;
	
	private Controlador controlador;

	/**
	 * Constructor de la ventana<br>
	 * @param aclist: actionListener que en este caso es el controlador, para llevar a cabo todas las acciones al presionar los botones
	 */
	public VentanaSistema(Controlador aclist) {
		this.controlador=aclist;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1000, 650);
		setTitle("Sistema");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panelSupAbonado = new JPanel();
		panelSupAbonado.setBorder(new TitledBorder(null, "Abonados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSuperior.add(panelSupAbonado);
		panelSupAbonado.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelSupAbonado.add(scrollPane);
		
		
		scrollPane.setViewportView(listaAbonado);
		
		JPanel panelSupDomicilio = new JPanel();
		panelSupDomicilio.setBorder(new TitledBorder(null, "Domicilios:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSuperior.add(panelSupDomicilio);
		panelSupDomicilio.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelSupDomicilio.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(listaDomicilio);
		
		JPanel panelSupContrataciones = new JPanel();
		panelSupContrataciones.setBorder(new TitledBorder(null, "Contrataciones:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSuperior.add(panelSupContrataciones);
		panelSupContrataciones.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panelSupContrataciones.add(scrollPane_2);
		
		
		scrollPane_2.setViewportView(listaContratacion);
		
		JPanel panelSupFacturas = new JPanel();
		panelSupFacturas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Facturas a pagar:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelSuperior.add(panelSupFacturas);
		panelSupFacturas.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panelSupFacturas.add(scrollPane_3);
		
		
		scrollPane_3.setViewportView(listaFactura);
		
		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior);
		panelInferior.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panelAbonado = new JPanel();
		panelAbonado.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInferior.add(panelAbonado);
		panelAbonado.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panelNombre = new JPanel();
		panelNombre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAbonado.add(panelNombre);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		panelNombre.add(lblNewLabel_3);
		
		textFieldNombre = new JTextField();
		panelNombre.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JPanel panelDNI = new JPanel();
		panelDNI.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAbonado.add(panelDNI);
		
		JLabel lblNewLabel_4 = new JLabel("DNI:");
		panelDNI.add(lblNewLabel_4);
		
		textFieldDNI = new JTextField();
		panelDNI.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		JPanel panelTipo = new JPanel();
		panelTipo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAbonado.add(panelTipo);
		panelTipo.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		panelTipo.add(lblNewLabel_5);
		
		JPanel panelTipoAbonadoRDBTN = new JPanel();
		panelTipoAbonadoRDBTN.setBorder(null);
		panelTipo.add(panelTipoAbonadoRDBTN);
		panelTipoAbonadoRDBTN.setLayout(new GridLayout(2, 0, 0, 0));
		
		
		buttonGroup.add(rdbtnFisica);
		panelTipoAbonadoRDBTN.add(rdbtnFisica);
		
		
		buttonGroup.add(rdbtnJuridica);
		panelTipoAbonadoRDBTN.add(rdbtnJuridica);
		
		JPanel panelPago = new JPanel();
		panelPago.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAbonado.add(panelPago);
		panelPago.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Pago:");
		panelPago.add(lblNewLabel_6);
		
		JPanel panelPagoAbonadoRDBTN = new JPanel();
		panelPago.add(panelPagoAbonadoRDBTN);
		panelPagoAbonadoRDBTN.setLayout(new GridLayout(3, 0, 0, 0));
		
	
		buttonGroup_1.add(rdbtnEfectivo);
		panelPagoAbonadoRDBTN.add(rdbtnEfectivo);
		
	
		buttonGroup_1.add(rdbtnCheque);
		panelPagoAbonadoRDBTN.add(rdbtnCheque);
		
		
		buttonGroup_1.add(rdbtnTarjeta);
		panelPagoAbonadoRDBTN.add(rdbtnTarjeta);
		
		JPanel panelAgregarOEliminar = new JPanel();
		panelAgregarOEliminar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAbonado.add(panelAgregarOEliminar);
		panelAgregarOEliminar.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panelAgregarOEliminar.add(panel_3);
		btnIncorporarAbonado.addActionListener(controlador);
		
		
		panel_3.add(btnIncorporarAbonado);
		
		JPanel panel_4 = new JPanel();
		panelAgregarOEliminar.add(panel_4);
		btnDesvincularAbonado.addActionListener(controlador);
		
		
		panel_4.add(btnDesvincularAbonado);
		
		JPanel panelDomicilio = new JPanel();
		panelDomicilio.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInferior.add(panelDomicilio);
		panelDomicilio.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("");
		panelDomicilio.add(lblNewLabel_7);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDomicilio.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_9 = new JLabel("Direccion:");
		panel_5.add(lblNewLabel_9);
		
		textFieldDireccion = new JTextField();
		panel_5.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(null);
		panelDomicilio.add(panel_6);
		
		
		btnAgregarDomicilio.addActionListener(controlador);
		panel_6.add(btnAgregarDomicilio);
		
		JLabel lblNewLabel_8 = new JLabel("");
		panelDomicilio.add(lblNewLabel_8);
		
		JPanel panelContratacion = new JPanel();
		panelContratacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInferior.add(panelContratacion);
		panelContratacion.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelAg = new JPanel();
		panelContratacion.add(panelAg);
		panelAg.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelTipoContratacion = new JPanel();
		panelTipoContratacion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAg.add(panelTipoContratacion);
		panelTipoContratacion.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_10 = new JLabel("Tipo:");
		panelTipoContratacion.add(lblNewLabel_10);
		
		JPanel panelEleccionContratacion = new JPanel();
		panelTipoContratacion.add(panelEleccionContratacion);
		panelEleccionContratacion.setLayout(new GridLayout(2, 0, 0, 0));
		
		buttonGroup_4.add(rdbtnInternet100);
		panelEleccionContratacion.add(rdbtnInternet100);
		
		
		buttonGroup_4.add(rdbtnInternet500);
		panelEleccionContratacion.add(rdbtnInternet500);
		
		JPanel panelNumeroIdentificacion = new JPanel();
		panelNumeroIdentificacion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAg.add(panelNumeroIdentificacion);
		
		JLabel lblNewLabel_11 = new JLabel("N° identificacion");
		panelNumeroIdentificacion.add(lblNewLabel_11);
		
		textFieldNumeroIdentificacion = new JTextField();
		textFieldNumeroIdentificacion.setColumns(10);
		panelNumeroIdentificacion.add(textFieldNumeroIdentificacion);
		
		JPanel panelAgregarEliminarContratacion = new JPanel();
		panelAgregarEliminarContratacion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAg.add(panelAgregarEliminarContratacion);
		panelAgregarEliminarContratacion.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_7_1 = new JPanel();
		panelAgregarEliminarContratacion.add(panel_7_1);
		btnAgregarContratacion.addActionListener(controlador);
		
		panel_7_1.add(btnAgregarContratacion);
		
		JPanel panel_8_1 = new JPanel();
		panelAgregarEliminarContratacion.add(panel_8_1);
		btnEliminarContratacion.addActionListener(controlador);
		
		panel_8_1.add(btnEliminarContratacion);
		
		JPanel panelAgregaServicio = new JPanel();
		panelAgregaServicio.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Servicios:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelContratacion.add(panelAgregaServicio);
		panelAgregaServicio.setLayout(new GridLayout(0, 1, 0, 0));
		
		buttonGroup_3.add(rdbtnCelular);
		panelAgregaServicio.add(rdbtnCelular);
		
		buttonGroup_3.add(rdbtnTelefono);
		panelAgregaServicio.add(rdbtnTelefono);
		
		buttonGroup_3.add(rdbtnTVCable);
		panelAgregaServicio.add(rdbtnTVCable);
		
		JPanel panel_7 = new JPanel();
		panelAgregaServicio.add(panel_7);
		
		JLabel lblNewLabel_12 = new JLabel("Cantidad:");
		panel_7.add(lblNewLabel_12);
		
		textFieldCantidadServicios = new JTextField();
		textFieldCantidadServicios.setColumns(10);
		panel_7.add(textFieldCantidadServicios);
		
		JPanel panel_8 = new JPanel();
		panelAgregaServicio.add(panel_8);
		
		
		btnAnadirServicio.addActionListener(controlador);
		panel_8.add(btnAnadirServicio);
		
		JPanel panelFactura = new JPanel();
		panelFactura.setBorder(null);
		panelInferior.add(panelFactura);
		panelFactura.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panelPagarFactura = new JPanel();
		panelPagarFactura.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFactura.add(panelPagarFactura);
		panelPagarFactura.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		btnPagar.addActionListener(controlador);
		panelPagarFactura.add(btnPagar);
		
		JLabel lblNewLabel = new JLabel("");
		panelFactura.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panelFactura.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panelFactura.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("Mes en curso:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		
		panel_2.add(textAreaMes);
		textAreaMes.setFont(new Font("Dialog", Font.PLAIN, 26));
		
		JPanel panel = new JPanel();
		panelFactura.add(panel);
		
		
		btnAvanzarMes.addActionListener(controlador);
		panel.add(btnAvanzarMes);
		
		listaAbonado.setModel(listModelAbonado);
		listaAbonado.addMouseListener(this);
		listaDomicilio.setModel(listModelDomicilio);
		listaContratacion.setModel(listModelContratacion);
		listaFactura.setModel(listModelFactura);
		
		setVisible(true);
	}
	
	public JButton getBotonIncorporar()
	{
		return btnIncorporarAbonado;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 *Metodo sobreescrito que al presionar un abonado de la lista de abonados, refresca las demas listas para que contegan los datos de ese abonado seleccionado 
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		this.abonado=listaAbonado.getSelectedValue();
		if (abonado!=null)
		{
			controlador.refrescarListaDomicilio();
			controlador.refrescarListaContratacion();
			controlador.refrescarListaFactura();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DefaultListModel<IAbonado> getListModelAbonado() {
		return listModelAbonado;
	}

	@Override
	public DefaultListModel<Domicilio> getListModelDomicilio() {
		return listModelDomicilio;
	}

	@Override
	public DefaultListModel<Contratacion> getListModelContratacion() {
		return listModelContratacion;
	}

	@Override
	public DefaultListModel<Factura> getListModelFactura() {
		return listModelFactura;
	}

	@Override
	public IAbonado getAbonado() {
		return abonado;
	}

	@Override
	public JTextArea getTextAreaMes() {
		return textAreaMes;
	}

	@Override
	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	@Override
	public JTextField getTextFieldDNI() {
		return textFieldDNI;
	}

	@Override
	public JTextField getTextFieldCantidadServicios() {
		return textFieldCantidadServicios;
	}

	@Override
	public JTextField getTextFieldDireccion() {
		return textFieldDireccion;
	}

	@Override
	public JRadioButton getRadioButtonEfectivo() {
		return rdbtnEfectivo;
	}

	@Override
	public JRadioButton getRadioButtonCheque() {
		return rdbtnCheque;
	}

	@Override
	public JRadioButton getRadioButtonTarjeta() {
		return rdbtnTarjeta;
	}

	@Override
	public JRadioButton getRadioButtonInternet100() {
		return rdbtnInternet100;
	}

	@Override
	public JRadioButton getRadioButtonInternet500() {
		return rdbtnInternet500;
	}

	@Override
	public JRadioButton getRadioButtonCelular() {
		return rdbtnCelular;
	}

	@Override
	public JRadioButton getRadioButtonTvCable() {
		return rdbtnTVCable;
	}

	@Override
	public JRadioButton getRadioButtonTelefono() {
		return rdbtnTelefono;
	}

	@Override
	public JTextField getTextFieldNumeroIdentificacion() {
		return textFieldNumeroIdentificacion;
	}

	@Override
	public JList<Contratacion> getListaContratacion() {
		return listaContratacion;
	}

	@Override
	public JRadioButton getRadioButtonFisica() {
		return rdbtnFisica;
	}

	@Override
	public JRadioButton getRadioButtonJuridica() {
		return rdbtnJuridica;
	}

	@Override
	public JList<Domicilio> getListaDomicilio() {
		return listaDomicilio;
	}

}
