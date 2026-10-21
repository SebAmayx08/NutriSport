package modelo;

import java.time.LocalDate;

public class Comida {

    // Atributos privados (encapsulamiento)
    private int id;
    private String nombre;
    private TipoComida tipo;
    private LocalDate fecha;

    public Comida(int id, String nombre, TipoComida tipo, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public TipoComida getTipo() { return tipo; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        return "[ID " + id + "] " + fecha + " | Comida | " + tipo.getNombre() + " | " + nombre;
    }
}
