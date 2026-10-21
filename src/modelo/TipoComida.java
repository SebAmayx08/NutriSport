package modelo;

// Lista cerrada de tipos de comida (RN-02).
public enum TipoComida {
    DESAYUNO("Desayuno", true),
    ALMUERZO("Almuerzo", true),
    CENA("Cena", true),
    MERIENDA("Merienda", false);

    private final String nombre;
    private final boolean principal; // true si cuenta como comida principal

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
}
