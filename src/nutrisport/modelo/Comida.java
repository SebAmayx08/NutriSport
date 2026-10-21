package nutrisport.modelo;

import java.time.LocalDate;
import nutrisport.util.DatoInvalidoException;
import nutrisport.util.Validador;

public class Comida {

    private final int id;
    private final String nombre;
    private final TipoComida tipo;
    private final LocalDate fecha;

    public Comida(int id, String nombre, TipoComida tipo, LocalDate fecha) throws DatoInvalidoException {
        if (tipo == null) {
            throw new DatoInvalidoException("El tipo de comida es obligatorio.");
        }
        if (fecha == null) {
            throw new DatoInvalidoException("La fecha es obligatoria.");
        }
        this.id = id;
        this.nombre = Validador.validarTextoNoVacio(nombre, "nombre de la comida");
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoComida getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "[ID " + id + "] " + tipo + ": " + nombre + " (" + fecha + ")";
    }
}
