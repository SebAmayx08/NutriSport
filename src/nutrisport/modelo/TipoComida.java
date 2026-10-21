package nutrisport.modelo;

import nutrisport.util.DatoInvalidoException;

/**
 * Lista cerrada de tipos de comida (RN-02).
 * Desayuno, almuerzo y cena cuentan como comidas principales (RN-05).
 */
public enum TipoComida {
    DESAYUNO("Desayuno", true),
    ALMUERZO("Almuerzo", true),
    CENA("Cena", true),
    MERIENDA("Merienda", false);

    private final String nombre;
    private final boolean principal;

    TipoComida(String nombre, boolean principal) {
        this.nombre = nombre;
        this.principal = principal;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esPrincipal() {
        return principal;
    }

    /** Convierte la opción del menú (1..4) en un tipo de comida. */
    public static TipoComida desdeOpcion(int opcion) throws DatoInvalidoException {
        TipoComida[] valores = values();
        if (opcion < 1 || opcion > valores.length) {
            throw new DatoInvalidoException("Tipo de comida inexistente: " + opcion);
        }
        return valores[opcion - 1];
    }

    @Override
    public String toString() {
        return nombre;
    }
}
