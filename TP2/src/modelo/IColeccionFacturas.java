package modelo;

import java.util.ArrayList;

/**
 * @author Federico,Gaston,Tobias <br>
 *Interface que usaran los abonados, los cuales poseen una lista de facturas o coleccion de estas
 */
public interface IColeccionFacturas {

	public ArrayList<Factura> getFacturas();

	public void agregarFactura(Factura factura);
	
}
