# NutriSport (MVP en Java)

Aplicación de consola para registrar comidas, agua y rutinas deportivas, y ver un resumen diario.

## Estructura
- `src/Main.java` — menú
- `src/modelo` — Comida, TipoComida, ConsumoAgua, RutinaDeportiva, ResumenDiario
- `src/servicio` — NutriSportServicio (lógica y colecciones)
- `src/util` — Validador y EntradaInvalidaException
- `tests/casos_de_prueba.md` — casos de prueba

## Compilar y ejecutar (desde la carpeta src)
```
javac -d ../out Main.java modelo/*.java servicio/*.java util/*.java
cd ../out
java Main
```
Los datos se guardan solo en memoria. El resumen es informativo, no un diagnóstico médico.
