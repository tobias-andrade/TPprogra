package modelo;

import java.io.Serializable;
import java.util.Observable;

/**
 * @author Federico,Gaston,Tobias <br>
 *Clase que representa el emulador de paso del tiempo y es observable 
 */
public class EPT extends Observable implements Serializable{

	private int mes=1;
	
	/**
	 * Constructor sin parametros
	 */
	public EPT() {
		super();
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * Metodo que ademas de incrementar mes le notifica a sus observadores este cambio, para que ellos realicen la tarea que deben hacer
	 */
	public void incrementarMes()
	{
		this.setChanged();
	    this.notifyObservers();
		if (this.mes==12)
			this.mes=1;
		else
			this.mes+=1;
	}
	
}
