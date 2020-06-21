package observadores;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import modelo.EPT;
import modelo.Factura;
import modelo.IAbonado;
import modelo.Sistema;

public class GestorFacturacion implements Observer {
	
	protected EPT observado=null;

	public void agregarObservable(EPT ept)
    {
		ept.addObserver(this);
		this.observado=ept;;
    }

    public void borrarObservable(EPT ept)
    {
    	ept.deleteObserver(this);
    	this.observado=ept;
    }

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
