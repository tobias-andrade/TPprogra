package observadores;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import estados.ConContratacionState;
import estados.MorosoState;
import estados.SinContratacionState;
import modelo.EPT;
import modelo.IAbonado;
import modelo.Sistema;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa el actualizador de datos, este cambiara los estados de abonados
 */
public class ActualizadorDeDatos implements Observer{

	protected EPT observado=null;

	/**
	 * Metodo para agregar observable y adems agregara a este un bservador que sera un objeto de este tipo<br>
	 * @param ept: ept es el emulador del paso del tiempo que sera observado
	 */
	public void agregarObservable(EPT ept)
    {
		ept.addObserver(this);
		this.observado=ept;;
    }

    /**
     * Metodo para borrar observable<br>
     * @param ept: observable que se borrara
     */
    public void borrarObservable(EPT ept)
    {
    	ept.deleteObserver(this);
    	this.observado=ept;
    }
	
	/**
	 *Metodo sobreescrito que realiza la accion de cambiar de estado a los abonados<br>
	 */
	@Override
	public void update(Observable o, Object arg)
	{
		if (this.observado==o)
		{
			Iterator<IAbonado> it=Sistema.getInstance().getAbonados().iterator();
			while (it.hasNext())
			{
				IAbonado aux= it.next();
				if (aux.getFacturas().size()>=2)
					aux.cambiarEstado("Moroso");
				else if (aux.getFacturas().size()<=1 && aux.getContrataciones().size()>0)
					aux.cambiarEstado("ConContratacion");
			}
			
		} else
		    throw new IllegalArgumentException();
	}

}
