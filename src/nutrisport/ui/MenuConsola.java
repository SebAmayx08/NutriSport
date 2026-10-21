package nutrisport.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import nutrisport.modelo.Comida;
import nutrisport.modelo.ConsumoAgua;
import nutrisport.modelo.RutinaDeportiva;
import nutrisport.modelo.TipoComida;
import nutrisport.servicio.RegistroService;
import nutrisport.servicio.ResumenDiario;
import nutrisport.util.Constantes;
import nutrisport.util.DatoInvalidoException;
import nutrisport.util.Validador;

public class MenuConsola {

    private final Scanner scanner = new Scanner(System.in);
    private final RegistroService servicio = new RegistroService();

    public void iniciar() {
        int opcion = -1;
        System.out.println("=== NutriSport - Registro de hábitos ===");
        try {
            do {
                mostrarMenu();
                opcion = Validador.leerEntero(scanner, "Seleccione una opción", 0, Constantes.OPCIONES_MENU.length - 1);
                try {
                    switch (opcion) {
                        case 1:
                            registrarComida();
                            break;
                        case 2:
                            registrarAgua();
                            break;
                        case 3:
                            registrarRutina();
                            break;
                        case 4:
                            consultarRegistros();
                            break;
                        case 5:
                            eliminarRegistro();
                            break;
                        case 6:
                            configurarMeta();
                            break;
                        case 7:
                            verResumen();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opción inexistente.");
                    }
                } catch (DatoInvalidoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (opcion != 0);
        } catch (NoSuchElementException e) {
            System.out.println("\nSe terminó la entrada de datos. Cerrando el programa...");
        }
        scanner.close();
        System.out.println("¡Hasta pronto!");
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        int ultima = Constantes.OPCIONES_MENU.length - 1;
        for (int i = 0; i < ultima; i++) {
            System.out.println((i + 1) + ". " + Constantes.OPCIONES_MENU[i]);
        }
        System.out.println("0. " + Constantes.OPCIONES_MENU[ultima]);
    }

    private void registrarComida() throws DatoInvalidoException {
        String nombre = Validador.leerTexto(scanner, "Nombre de la comida");
        TipoComida[] tipos = TipoComida.values();
        System.out.println("Tipo de comida:");
        for (int i = 0; i < tipos.length; i++) {
            System.out.println("  " + (i + 1) + ". " + tipos[i]);
        }
        int opcionTipo = Validador.leerEntero(scanner, "Tipo", 1, tipos.length);
        TipoComida tipo = TipoComida.desdeOpcion(opcionTipo);
        LocalDate fecha = Validador.leerFechaOHoy(scanner);

        Comida comida = servicio.registrarComida(nombre, tipo, fecha);
        System.out.println("OK. Comida registrada con ID " + comida.getId() + ": " + comida);
    }

    private void registrarAgua() throws DatoInvalidoException {
        int ml = Validador.leerEntero(scanner, "Agua consumida (ml)", Constantes.AGUA_MIN_ML, Constantes.AGUA_MAX_ML);
        LocalDate fecha = Validador.leerFechaOHoy(scanner);

        ConsumoAgua agua = servicio.registrarAgua(ml, fecha);
        System.out.println("OK. Agua registrada con ID " + agua.getId() + ": " + agua);
    }

    private void registrarRutina() throws DatoInvalidoException {
        String nombre = Validador.leerTexto(scanner, "Nombre de la rutina");
        int minutos = Validador.leerEntero(scanner, "Duración (minutos)",
                Constantes.RUTINA_MIN_MINUTOS, Constantes.RUTINA_MAX_MINUTOS);
        LocalDate fecha = Validador.leerFechaOHoy(scanner);

        RutinaDeportiva rutina = servicio.registrarRutina(nombre, minutos, fecha);
        System.out.println("OK. Rutina registrada con ID " + rutina.getId() + ": " + rutina);
    }

    private void consultarRegistros() {
        System.out.println("Categoría a consultar:");
        System.out.println("  1. Comidas");
        System.out.println("  2. Agua");
        System.out.println("  3. Rutinas deportivas");
        int categoria = Validador.leerEntero(scanner, "Categoría", 1, 3);
        LocalDate fecha = Validador.leerFechaOpcional(scanner);

        switch (categoria) {
            case 1:
                imprimirLista(servicio.listarComidas(fecha));
                break;
            case 2:
                imprimirLista(servicio.listarAgua(fecha));
                break;
            case 3:
                imprimirLista(servicio.listarRutinas(fecha));
                break;
            default:
                System.out.println("Categoría inexistente.");
        }
    }

    private <T> void imprimirLista(List<T> registros) {
        if (registros.isEmpty()) {
            System.out.println("Sin registros.");
            return;
        }
        for (T registro : registros) {
            System.out.println(registro);
        }
    }

    private void eliminarRegistro() throws DatoInvalidoException {
        int id = Validador.leerEntero(scanner, "Identificador del registro a eliminar", 1, Integer.MAX_VALUE);
        servicio.eliminarRegistro(id);
        System.out.println("OK. Registro " + id + " eliminado.");
    }

    private void configurarMeta() throws DatoInvalidoException {
        System.out.println("Meta actual: " + servicio.getMetaAgua() + " ml");
        int meta = Validador.leerEntero(scanner, "Nueva meta diaria de agua (ml)",
                Constantes.META_AGUA_MIN_ML, Constantes.META_AGUA_MAX_ML);
        servicio.configurarMetaAgua(meta);
        System.out.println("OK. Meta diaria actualizada a " + meta + " ml.");
    }

    private void verResumen() {
        LocalDate fecha = Validador.leerFechaOHoy(scanner);
        ResumenDiario resumen = new ResumenDiario(servicio, fecha);
        System.out.println(resumen.generarReporte());
    }
}
