package modelo;

import java.time.LocalDate;

public class RutinaDeportiva {

    private int id;
    private String nombre;
    private int minutos;
    private LocalDate fecha;

    public RutinaDeportiva(int id, String nombre, int minutos, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.minutos = minutos;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getMinutos() { return minutos; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        return "[ID " + id + "] " + fecha + " | Rutina | " + nombre + " | " + minutos + " min";
    }
}
