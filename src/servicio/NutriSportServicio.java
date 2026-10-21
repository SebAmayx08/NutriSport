package servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import modelo.Comida;
import modelo.ConsumoAgua;
import modelo.ResumenDiario;
import modelo.RutinaDeportiva;
import modelo.TipoComida;
import util.EntradaInvalidaException;

// Aquí está la lógica del programa y las colecciones donde se guardan los datos.
public class NutriSportServicio {

    // Constantes (RN-03 y RN-04)
    public static final int AGUA_MAX_REGISTRO = 2000;
    public static final int DURACION_MIN = 1;
    public static final int DURACION_MAX = 300;
    public static final int META_AGUA_INICIAL = 2000;
    public static final int META_AGUA_MIN = 500;
    public static final int META_AGUA_MAX = 10000;

    private ArrayList<Comida> comidas = new ArrayList<>();
    private ArrayList<ConsumoAgua> aguas = new ArrayList<>();
    private ArrayList<RutinaDeportiva> rutinas = new ArrayList<>();

    private int siguienteId = 1;              // un solo contador para que cada ID sea único
    private int metaAgua = META_AGUA_INICIAL;

    // ---------- Registrar ----------
    public Comida registrarComida(String nombre, TipoComida tipo, LocalDate fecha)
            throws EntradaInvalidaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new EntradaInvalidaException("El nombre de la comida no puede estar vacío.");
        }
        if (tipo == null) {
            throw new EntradaInvalidaException("Debe elegir un tipo de comida.");
        }
        Comida c = new Comida(siguienteId++, nombre.trim(), tipo, fecha);
        comidas.add(c);
        return c;
    }

    public ConsumoAgua registrarAgua(int ml, LocalDate fecha) throws EntradaInvalidaException {
        if (ml < 1 || ml > AGUA_MAX_REGISTRO) {
            throw new EntradaInvalidaException("El agua debe estar entre 1 y " + AGUA_MAX_REGISTRO + " ml.");
        }
        ConsumoAgua a = new ConsumoAgua(siguienteId++, ml, fecha);
        aguas.add(a);
        return a;
    }

    public RutinaDeportiva registrarRutina(String nombre, int minutos, LocalDate fecha)
            throws EntradaInvalidaException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new EntradaInvalidaException("El nombre de la rutina no puede estar vacío.");
        }
        if (minutos < DURACION_MIN || minutos > DURACION_MAX) {
            throw new EntradaInvalidaException("La duración debe estar entre " + DURACION_MIN
                    + " y " + DURACION_MAX + " minutos.");
        }
        RutinaDeportiva r = new RutinaDeportiva(siguienteId++, nombre.trim(), minutos, fecha);
        rutinas.add(r);
        return r;
    }

    // ---------- Consultar (fecha null = todas las fechas) ----------
    public ArrayList<Comida> consultarComidas(LocalDate fecha) {
        ArrayList<Comida> resultado = new ArrayList<>();
        for (Comida c : comidas) {
            if (fecha == null || c.getFecha().equals(fecha)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public ArrayList<ConsumoAgua> consultarAguas(LocalDate fecha) {
        ArrayList<ConsumoAgua> resultado = new ArrayList<>();
        for (ConsumoAgua a : aguas) {
            if (fecha == null || a.getFecha().equals(fecha)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public ArrayList<RutinaDeportiva> consultarRutinas(LocalDate fecha) {
        ArrayList<RutinaDeportiva> resultado = new ArrayList<>();
        for (RutinaDeportiva r : rutinas) {
            if (fecha == null || r.getFecha().equals(fecha)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // ---------- Eliminar: busca el ID en las tres listas ----------
    public boolean eliminarPorId(int id) {
        for (int i = 0; i < comidas.size(); i++) {
            if (comidas.get(i).getId() == id) {
                comidas.remove(i);
                return true;
            }
        }
        for (int i = 0; i < aguas.size(); i++) {
            if (aguas.get(i).getId() == id) {
                aguas.remove(i);
                return true;
            }
        }
        for (int i = 0; i < rutinas.size(); i++) {
            if (rutinas.get(i).getId() == id) {
                rutinas.remove(i);
                return true;
            }
        }
        return false; // no existe ese ID
    }

    // ---------- Meta de agua ----------
    public int getMetaAgua() {
        return metaAgua;
    }

    public void setMetaAgua(int ml) throws EntradaInvalidaException {
        if (ml < META_AGUA_MIN || ml > META_AGUA_MAX) {
            throw new EntradaInvalidaException("La meta debe estar entre " + META_AGUA_MIN
                    + " y " + META_AGUA_MAX + " ml.");
        }
        this.metaAgua = ml;
    }

    // ---------- Resumen diario (T-13) ----------
    public ResumenDiario generarResumen(LocalDate fecha) {
        // HashMap: tipo de comida -> cantidad (todos empiezan en 0)
        HashMap<TipoComida, Integer> porTipo = new HashMap<>();
        for (TipoComida t : TipoComida.values()) {
            porTipo.put(t, 0);
        }
        for (Comida c : comidas) {
            if (c.getFecha().equals(fecha)) {
                porTipo.put(c.getTipo(), porTipo.get(c.getTipo()) + 1);
            }
        }

        // Comidas principales distintas registradas (máximo 3)
        int principales = 0;
        for (TipoComida t : TipoComida.values()) {
            if (t.esPrincipal() && porTipo.get(t) > 0) {
                principales++;
            }
        }

        int totalMl = 0;
        for (ConsumoAgua a : aguas) {
            if (a.getFecha().equals(fecha)) {
                totalMl += a.getMililitros();
            }
        }
        double porcentaje = totalMl * 100.0 / metaAgua;

        // HashSet: no permite repetidos, así cuenta las actividades distintas
        int totalMinutos = 0;
        HashSet<String> distintas = new HashSet<>();
        for (RutinaDeportiva r : rutinas) {
            if (r.getFecha().equals(fecha)) {
                totalMinutos += r.getMinutos();
                distintas.add(r.getNombre().toLowerCase());
            }
        }

        return new ResumenDiario(fecha, porTipo, principales, totalMl, metaAgua,
                porcentaje, totalMinutos, distintas.size());
    }
}
