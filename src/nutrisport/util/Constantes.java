package nutrisport.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public final class Constantes {

    private Constantes() {
       
    }

    // RN-03: agua por registro (propuesta: tope 2000 ml)
    public static final int AGUA_MIN_ML = 1;
    public static final int AGUA_MAX_ML = 2000;

    // RN-03: rutina deportiva (propuesta: máximo 300 minutos)
    public static final int RUTINA_MIN_MINUTOS = 1;
    public static final int RUTINA_MAX_MINUTOS = 300;

    // RN-04: meta diaria de agua (valor inicial referencial, no es recomendación médica)
    public static final int META_AGUA_INICIAL_ML = 2000;
    public static final int META_AGUA_MIN_ML = 500;
    public static final int META_AGUA_MAX_ML = 10000;

    // RN-05: comidas principales = desayuno, almuerzo y cena
    public static final int TOTAL_COMIDAS_PRINCIPALES = 3;

    // RN-01: fecha en formato AAAA-MM-DD
    public static final String FORMATO_FECHA_TEXTO = "AAAA-MM-DD";
    public static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);

    public static final String AVISO_NO_DIAGNOSTICO =
            "Aviso: este resumen es informativo. No es un diagnóstico ni una recomendación médica.";

    // Lab 04: arreglo de opciones del menú (la última opción es "Salir" y se muestra como 0)
    public static final String[] OPCIONES_MENU = {
        "Registrar comida",
        "Registrar consumo de agua",
        "Registrar rutina deportiva",
        "Consultar registros",
        "Eliminar registro por identificador",
        "Configurar meta diaria de agua",
        "Ver resumen diario",
        "Salir"
    };
}
