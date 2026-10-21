package util;

// Excepción propia del proyecto: se lanza cuando un dato no cumple las reglas de negocio.
public class EntradaInvalidaException extends Exception {

    public EntradaInvalidaException(String mensaje) {
        super(mensaje); // guardamos el mensaje para mostrarlo al usuario
    }
}
