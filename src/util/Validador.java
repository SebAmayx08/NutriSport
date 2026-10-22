package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Lectura segura de datos: repite la pregunta hasta que el dato sea válido.
public class Validador {

    public static int leerEntero(Scanner sc, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            int valor;
            try {
                valor = Integer.parseInt(texto); // falla si hay letras o está vacío
            } catch (NumberFormatException e) {
                System.out.println("Error: debe escribir un número entero.");
                continue;
            }
            if (valor < min || valor > max) {
                System.out.println("Error: el valor debe estar entre " + min + " y " + max + ".");
                continue;
            }
            return valor;
        }
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: el texto no puede estar vacío.");
        }
    }

    // Si el usuario solo presiona Enter, se usa la fecha de hoy (RN-01).
    public static LocalDate leerFecha(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje + " (AAAA-MM-DD, Enter = hoy): ");
            String texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException e) {
                System.out.println("Error: fecha inválida. Use el formato AAAA-MM-DD.");
            }
        }
    }

    // Para consultas: si el usuario presiona Enter devuelve null (significa "todas las fechas").
    public static LocalDate leerFechaOpcional(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje + " (AAAA-MM-DD, Enter = todas): ");
            String texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException e) {
                System.out.println("Error: fecha inválida. Use el formato AAAA-MM-DD.");
            }
        }
    }
}
