package observadores;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import modelo.EPT;
import modelo.Factura;
import modelo.IAbonado;
import modelo.Sistema;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que agregara las facturas a los abonados del sistema
 */
public class GestorFacturacion implements Observer {
	
	protected EPT observado=null;

	/**
	 * Metodo que agrega observable, y a ese observable le agregara un observador que sera un objeto de esta clase precisamente<br>
	 * @param ept : observable al que se le realizara esta accion
	 */
	public void agregarObservable(EPT ept)
    {
		ept.addObserver(this);
		this.observado=ept;;
    }

    /**
     * Metodo que elimina un observado de la lista de este observador<br>
     * @param ept : observable que sera eliminado de la lista de observados
     */
    public void borrarObservable(EPT ept)
    {
    	ept.deleteObserver(this);
    	this.observado=ept;
    }

	/**
	 *Metodo sobreescrito que agregara las facturas correspondientes a cada abonado al incrementar de mes
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{	
		if (this.observado==arg0)
		{
			Iterator<IAbonado> it=Sistema.getInstance().getAbonados().iterator();
			while (it.hasNext())
			{
				IAbonado aux=it.next(); 
				double monto=aux.calcularTotal();
				if (monto>0)
					aux.agregarFactura(new Factura(aux.getNombre(),monto,aux.realizarDescuentoOIncremento(monto),Sistema.getInstance().getEpt().getMes()));
			}
		} else
		    throw new IllegalArgumentException();
	}
	
}
