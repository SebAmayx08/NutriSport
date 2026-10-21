package nutrisport.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class Validador {

    private Validador() {
    }



    public static String validarTextoNoVacio(String valor, String campo) throws DatoInvalidoException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DatoInvalidoException("El valor de '" + campo + "' no puede estar vacío.");
        }
        return valor.trim();
    }

    public static int validarRango(int valor, String campo, int min, int max) throws DatoInvalidoException {
        if (valor < min || valor > max) {
            throw new DatoInvalidoException("'" + campo + "' debe estar entre " + min + " y " + max + ".");
        }
        return valor;
    }

    public static int validarEntero(String texto, String campo, int min, int max) throws DatoInvalidoException {
        String limpio = validarTextoNoVacio(texto, campo);
        int valor;
        try {
            valor = Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("'" + campo + "' debe ser un número entero (sin letras ni decimales).");
        }
        return validarRango(valor, campo, min, max);
    }

    public static LocalDate validarFecha(String texto) throws DatoInvalidoException {
        String limpio = validarTextoNoVacio(texto, "fecha");
        try {
            return LocalDate.parse(limpio, Constantes.FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            throw new DatoInvalidoException(
                    "Fecha inválida. Use el formato " + Constantes.FORMATO_FECHA_TEXTO + " con una fecha real.");
        }
    }

    // ---------------------------------------------------------------
    // Lecturas con reintento
    // ---------------------------------------------------------------

    public static String leerTexto(Scanner scanner, String campo) {
        while (true) {
            System.out.print(campo + ": ");
            try {
                return validarTextoNoVacio(scanner.nextLine(), campo);
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static int leerEntero(Scanner scanner, String campo, int min, int max) {
        while (true) {
            System.out.print(campo + ": ");
            try {
                return validarEntero(scanner.nextLine(), campo, min, max);
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /** Si el usuario solo presiona Enter se usa la fecha del sistema (RN-01). */
    public static LocalDate leerFechaOHoy(Scanner scanner) {
        while (true) {
            System.out.print("Fecha (" + Constantes.FORMATO_FECHA_TEXTO + ", Enter = hoy): ");
            String linea = scanner.nextLine();
            if (linea.trim().isEmpty()) {
                return LocalDate.now();
            }
            try {
                return validarFecha(linea);
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /** Si el usuario solo presiona Enter devuelve null (sin filtro de fecha). */
    public static LocalDate leerFechaOpcional(Scanner scanner) {
        while (true) {
            System.out.print("Filtrar por fecha (" + Constantes.FORMATO_FECHA_TEXTO + ", Enter = todas): ");
            String linea = scanner.nextLine();
            if (linea.trim().isEmpty()) {
                return null;
            }
            try {
                return validarFecha(linea);
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
