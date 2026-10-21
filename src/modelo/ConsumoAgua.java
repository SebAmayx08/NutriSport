package modelo;

import java.time.LocalDate;

public class ConsumoAgua {

    private int id;
    private int mililitros;
    private LocalDate fecha;

    public ConsumoAgua(int id, int mililitros, LocalDate fecha) {
        this.id = id;
        this.mililitros = mililitros;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public int getMililitros() { return mililitros; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        return "[ID " + id + "] " + fecha + " | Agua | " + mililitros + " ml";
    }
}
