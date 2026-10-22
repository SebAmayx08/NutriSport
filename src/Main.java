import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import modelo.Comida;
import modelo.ConsumoAgua;
import modelo.ResumenDiario;
import modelo.RutinaDeportiva;
import modelo.TipoComida;
import servicio.NutriSportServicio;
import util.EntradaInvalidaException;
import util.Validador;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final NutriSportServicio servicio = new NutriSportServicio();

    // Arreglo con las opciones del menú
    private static final String[] OPCIONES = {
        "Registrar comida",
        "Registrar agua",
        "Registrar rutina deportiva",
        "Consultar registros",
        "Eliminar registro por ID",
        "Configurar meta diaria de agua",
        "Ver resumen diario",
        "Salir"
    };

    public static void main(String[] args) {
        try {
            int opcion;
            do {
                mostrarMenu();
                opcion = Validador.leerEntero(sc, "Elija una opción: ", 1, OPCIONES.length);
                switch (opcion) {
                    case 1: registrarComida(); break;
                    case 2: registrarAgua(); break;
                    case 3: registrarRutina(); break;
                    case 4: consultar(); break;
                    case 5: eliminar(); break;
                    case 6: configurarMeta(); break;
                    case 7: verResumen(); break;
                    case 8: System.out.println("¡Hasta pronto!"); break;
                }
            } while (opcion != OPCIONES.length);
        } catch (NoSuchElementException e) {
            // La entrada se cerró (Ctrl+D / Ctrl+Z): salimos sin mostrar el error
            System.out.println("\nEntrada finalizada. ¡Hasta pronto!");
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== NUTRISPORT =====");
        for (int i = 0; i < OPCIONES.length; i++) {
            System.out.println((i + 1) + ". " + OPCIONES[i]);
        }
    }

    private static void registrarComida() {
        String nombre = Validador.leerTexto(sc, "Nombre de la comida: ");
        TipoComida tipo = elegirTipoComida();
        LocalDate fecha = Validador.leerFecha(sc, "Fecha");
        try {
            Comida c = servicio.registrarComida(nombre, tipo, fecha);
            System.out.println("Comida registrada con ID " + c.getId() + ".");
        } catch (EntradaInvalidaException e) {
            System.out.println("No se guardó: " + e.getMessage());
        }
    }

    private static TipoComida elegirTipoComida() {
        TipoComida[] tipos = TipoComida.values();
        System.out.println("Tipo de comida:");
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + ". " + tipos[i].getNombre());
        }
        int op = Validador.leerEntero(sc, "Elija el tipo: ", 1, tipos.length);
        return tipos[op - 1];
    }

    private static void registrarAgua() {
        int ml = Validador.leerEntero(sc, "Cantidad de agua en ml (1-"
                + NutriSportServicio.AGUA_MAX_REGISTRO + "): ", 1, NutriSportServicio.AGUA_MAX_REGISTRO);
        LocalDate fecha = Validador.leerFecha(sc, "Fecha");
        try {
            ConsumoAgua a = servicio.registrarAgua(ml, fecha);
            System.out.println("Agua registrada con ID " + a.getId() + ".");
        } catch (EntradaInvalidaException e) {
            System.out.println("No se guardó: " + e.getMessage());
        }
    }

    private static void registrarRutina() {
        String nombre = Validador.leerTexto(sc, "Nombre de la rutina: ");
        int minutos = Validador.leerEntero(sc, "Duración en minutos ("
                + NutriSportServicio.DURACION_MIN + "-" + NutriSportServicio.DURACION_MAX + "): ",
                NutriSportServicio.DURACION_MIN, NutriSportServicio.DURACION_MAX);
        LocalDate fecha = Validador.leerFecha(sc, "Fecha");
        try {
            RutinaDeportiva r = servicio.registrarRutina(nombre, minutos, fecha);
            System.out.println("Rutina registrada con ID " + r.getId() + ".");
        } catch (EntradaInvalidaException e) {
            System.out.println("No se guardó: " + e.getMessage());
        }
    }

    private static void consultar() {
        System.out.println("Consultar: 1. Comidas  2. Agua  3. Rutinas");
        int categoria = Validador.leerEntero(sc, "Elija la categoría: ", 1, 3);
        LocalDate fecha = Validador.leerFechaOpcional(sc, "Filtrar por fecha");

        if (categoria == 1) {
            ArrayList<Comida> lista = servicio.consultarComidas(fecha);
            if (lista.isEmpty()) System.out.println("Sin registros.");
            for (Comida c : lista) System.out.println(c);
        } else if (categoria == 2) {
            ArrayList<ConsumoAgua> lista = servicio.consultarAguas(fecha);
            if (lista.isEmpty()) System.out.println("Sin registros.");
            for (ConsumoAgua a : lista) System.out.println(a);
        } else {
            ArrayList<RutinaDeportiva> lista = servicio.consultarRutinas(fecha);
            if (lista.isEmpty()) System.out.println("Sin registros.");
            for (RutinaDeportiva r : lista) System.out.println(r);
        }
    }

    private static void eliminar() {
        int id = Validador.leerEntero(sc, "ID del registro a eliminar: ", 1, Integer.MAX_VALUE);
        if (servicio.eliminarPorId(id)) {
            System.out.println("Registro " + id + " eliminado.");
        } else {
            System.out.println("No existe un registro con ese ID. No se eliminó nada.");
        }
    }

    private static void configurarMeta() {
        System.out.println("Meta actual: " + servicio.getMetaAgua() + " ml");
        int nueva = Validador.leerEntero(sc, "Nueva meta en ml ("
                + NutriSportServicio.META_AGUA_MIN + "-" + NutriSportServicio.META_AGUA_MAX + "): ",
                NutriSportServicio.META_AGUA_MIN, NutriSportServicio.META_AGUA_MAX);
        try {
            servicio.setMetaAgua(nueva);
            System.out.println("Meta actualizada a " + nueva + " ml.");
        } catch (EntradaInvalidaException e) {
            System.out.println("Se conserva la meta anterior: " + e.getMessage());
        }
    }

    // Formato del reporte (T-14)
    private static void verResumen() {
        LocalDate fecha = Validador.leerFecha(sc, "Fecha del resumen");
        ResumenDiario r = servicio.generarResumen(fecha);

        System.out.println("\n===== RESUMEN DEL " + r.getFecha() + " =====");
        System.out.println("Comidas por tipo:");
        for (TipoComida t : TipoComida.values()) {
            System.out.println("  - " + t.getNombre() + ": " + r.getComidasPorTipo().get(t));
        }
        System.out.println("Comidas principales registradas: " + r.getPrincipalesRegistradas() + " de "
                + NutriSportServicio.COMIDAS_PRINCIPALES_MAX);
        System.out.println("Agua: " + r.getTotalMl() + " ml de " + r.getMetaMl() + " ml ("
                + String.format("%.1f", r.getPorcentajeMeta()) + "% de la meta)");
        System.out.println("Actividad física: " + r.getTotalMinutos() + " min, "
                + r.getActividadesDistintas() + " actividad(es) distinta(s)");
        System.out.println("Aviso: este resumen es informativo, NO es un diagnóstico médico.");
    }
}
