package persistencia;

import java.io.IOException;
import java.io.Serializable;

public interface IPersistencia <E>
{
    void abrirInPut(String nombre) throws IOException;

    void abrirOutPut(String nombre) throws IOException;

    void cerrarOutPut() throws IOException;

    void cerrarInPut() throws IOException;

    void escribir(E objecto) throws IOException;

    E leer() throws IOException, ClassNotFoundException;
}
