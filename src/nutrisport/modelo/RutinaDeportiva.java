package nutrisport.modelo;

import java.time.LocalDate;
import nutrisport.util.Constantes;
import nutrisport.util.DatoInvalidoException;
import nutrisport.util.Validador;


public class RutinaDeportiva {

    private final int id;
    private final String nombre;
    private final int minutos;
    private final LocalDate fecha;

    public RutinaDeportiva(int id, String nombre, int minutos, LocalDate fecha) throws DatoInvalidoException {
        if (fecha == null) {
            throw new DatoInvalidoException("La fecha es obligatoria.");
        }
        this.id = id;
        this.nombre = Validador.validarTextoNoVacio(nombre, "nombre de la rutina");
        this.minutos = Validador.validarRango(minutos, "duración (minutos)",
                Constantes.RUTINA_MIN_MINUTOS, Constantes.RUTINA_MAX_MINUTOS);
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMinutos() {
        return minutos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "[ID " + id + "] Rutina: " + nombre + " - " + minutos + " min (" + fecha + ")";
    }
}
