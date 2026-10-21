package nutrisport.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nutrisport.modelo.Comida;
import nutrisport.modelo.ConsumoAgua;
import nutrisport.modelo.RutinaDeportiva;
import nutrisport.modelo.TipoComida;
import nutrisport.util.Constantes;

/**
 * Calcula y da formato al resumen de un día (RN-05).
 * Lab 04: HashMap (comidas por tipo) y HashSet (actividades distintas).
 */
public class ResumenDiario {

    private final LocalDate fecha;
    private final int metaAguaMl;

    private final Map<TipoComida, List<Comida>> comidasPorTipo = new HashMap<>();
    private final Set<String> actividadesDistintas = new HashSet<>();
    private int totalMl = 0;
    private int totalMinutos = 0;

    public ResumenDiario(RegistroService servicio, LocalDate fecha) {
        this.fecha = fecha;
        this.metaAguaMl = servicio.getMetaAgua();

        for (Comida c : servicio.listarComidas(fecha)) {
            List<Comida> lista = comidasPorTipo.get(c.getTipo());
            if (lista == null) {
                lista = new ArrayList<>();
                comidasPorTipo.put(c.getTipo(), lista);
            }
            lista.add(c);
        }
        for (ConsumoAgua a : servicio.listarAgua(fecha)) {
            totalMl += a.getMl();
        }
        for (RutinaDeportiva r : servicio.listarRutinas(fecha)) {
            totalMinutos += r.getMinutos();
            actividadesDistintas.add(r.getNombre().toLowerCase());
        }
    }

    /** Cuántos de los 3 tipos principales (desayuno, almuerzo, cena) tienen al menos una comida. */
    public int getComidasPrincipales() {
        int cantidad = 0;
        for (TipoComida tipo : TipoComida.values()) {
            if (tipo.esPrincipal() && comidasPorTipo.containsKey(tipo)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int getTotalMl() {
        return totalMl;
    }

    public double getPorcentajeMeta() {
        return (totalMl * 100.0) / metaAguaMl;
    }

    public int getTotalMinutos() {
        return totalMinutos;
    }

    public int getActividadesDistintas() {
        return actividadesDistintas.size();
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== RESUMEN DIARIO - ").append(fecha).append(" =====\n");

        sb.append("\nComidas por tipo:\n");
        for (TipoComida tipo : TipoComida.values()) {
            List<Comida> lista = comidasPorTipo.get(tipo);
            int cantidad = (lista == null) ? 0 : lista.size();
            sb.append("  - ").append(tipo).append(": ").append(cantidad);
            if (lista != null) {
                sb.append(" (");
                for (int i = 0; i < lista.size(); i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(lista.get(i).getNombre());
                }
                sb.append(")");
            }
            sb.append("\n");
        }
        sb.append("Comidas principales registradas: ").append(getComidasPrincipales())
                .append(" de ").append(Constantes.TOTAL_COMIDAS_PRINCIPALES).append("\n");

        sb.append("\nHidratación:\n");
        sb.append("  Total de agua: ").append(totalMl).append(" ml\n");
        sb.append("  Meta diaria: ").append(metaAguaMl).append(" ml (")
                .append(String.format("%.1f", getPorcentajeMeta())).append("% de la meta)\n");

        sb.append("\nActividad física:\n");
        sb.append("  Minutos de actividad: ").append(totalMinutos).append("\n");
        sb.append("  Actividades distintas: ").append(actividadesDistintas.size()).append("\n");

        sb.append("\n").append(Constantes.AVISO_NO_DIAGNOSTICO).append("\n");
        return sb.toString();
    }
}
