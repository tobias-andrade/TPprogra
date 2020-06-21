package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Federico,Gaston,Tobias <br>
 *Persistencia binaria del sistema
 */
public class PersistenciaBIN implements IPersistencia <Serializable>
{

    private FileOutputStream fileoutput;
    private FileInputStream fileinput;
    private ObjectOutputStream objectoutput;
    private ObjectInputStream objectinput;

    /**
     *Metodo para abrir el archivo que se leera
     */
    public void abrirInPut(String nombre) throws IOException
    {
        fileinput = new FileInputStream(nombre);
        objectinput = new ObjectInputStream(fileinput);

    }

    /**
     *Metodo para abrir el archivo en el que se sobreescribira el sistema
     */
    public void abrirOutPut(String nombre) throws IOException
    {
        fileoutput = new FileOutputStream(nombre);
        objectoutput = new ObjectOutputStream(fileoutput);

    }

    /**
     *Metodo para cerra el archivo en el que se escribio
     */
    public void cerrarOutPut() throws IOException
    {
        if (objectoutput != null)
            objectoutput.close();
    }

    /**
     *Metodo para cerrar el archivo en el que se leyo
     */
    public void cerrarInPut() throws IOException
    {
        if (objectinput != null)
            objectinput.close();

    }


    /**
     *Metodo para escribir o sobreescribir el sistema en el archivo
     */
    public void escribir(Serializable serializable) throws IOException
    {
        if (objectoutput != null)
            objectoutput.writeObject(serializable);
    }

    /**
     *Metodo para leer el sistema de un archivo
     */
    public Serializable leer() throws IOException, ClassNotFoundException
    {
        Serializable serializable = null;
        if (objectinput != null)
            serializable = (Serializable) objectinput.readObject();
        return serializable;
    }

	

}
