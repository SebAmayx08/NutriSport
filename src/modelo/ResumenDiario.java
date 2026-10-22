package modelo;

import java.time.LocalDate;
import java.util.Map;

// Guarda los resultados calculados para un día. No calcula nada: solo almacena datos.
public class ResumenDiario {

    private LocalDate fecha;
    private Map<TipoComida, Integer> comidasPorTipo;
    private int principalesRegistradas;   // de 3
    private int totalMl;
    private int metaMl;
    private double porcentajeMeta;
    private int totalMinutos;
    private int actividadesDistintas;

    public ResumenDiario(LocalDate fecha, Map<TipoComida, Integer> comidasPorTipo,
                         int principalesRegistradas, int totalMl, int metaMl,
                         double porcentajeMeta, int totalMinutos, int actividadesDistintas) {
        this.fecha = fecha;
        this.comidasPorTipo = comidasPorTipo;
        this.principalesRegistradas = principalesRegistradas;
        this.totalMl = totalMl;
        this.metaMl = metaMl;
        this.porcentajeMeta = porcentajeMeta;
        this.totalMinutos = totalMinutos;
        this.actividadesDistintas = actividadesDistintas;
    }

    public LocalDate getFecha() { return fecha; }
    public Map<TipoComida, Integer> getComidasPorTipo() { return comidasPorTipo; }
    public int getPrincipalesRegistradas() { return principalesRegistradas; }
    public int getTotalMl() { return totalMl; }
    public int getMetaMl() { return metaMl; }
    public double getPorcentajeMeta() { return porcentajeMeta; }
    public int getTotalMinutos() { return totalMinutos; }
    public int getActividadesDistintas() { return actividadesDistintas; }
}
