package nutrisport.modelo;

import java.time.LocalDate;
import nutrisport.util.Constantes;
import nutrisport.util.DatoInvalidoException;
import nutrisport.util.Validador;

public class ConsumoAgua {

    private final int id;
    private final int ml;
    private final LocalDate fecha;

    public ConsumoAgua(int id, int ml, LocalDate fecha) throws DatoInvalidoException {
        if (fecha == null) {
            throw new DatoInvalidoException("La fecha es obligatoria.");
        }
        this.id = id;
        this.ml = Validador.validarRango(ml, "agua (ml)", Constantes.AGUA_MIN_ML, Constantes.AGUA_MAX_ML);
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getMl() {
        return ml;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "[ID " + id + "] Agua: " + ml + " ml (" + fecha + ")";
    }
}
