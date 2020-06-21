package persistencia;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Federico,Gaston,Tobias <br>
 * Interface para implementar sus metodos con el objetivo de persistir<br>
 * @param <E>: sera un Serializable y es el sistema que se desea persistir
 */
public interface IPersistencia <E>
{
    void abrirInPut(String nombre) throws IOException;

    void abrirOutPut(String nombre) throws IOException;

    void cerrarOutPut() throws IOException;

    void cerrarInPut() throws IOException;

    void escribir(E objecto) throws IOException;

    E leer() throws IOException, ClassNotFoundException;
}
