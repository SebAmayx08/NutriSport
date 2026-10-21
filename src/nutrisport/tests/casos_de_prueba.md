# Casos de prueba - NutriSport

Pruebas manuales por consola. Cada caso se ejecuta con `java -cp out nutrisport.Main` y se registra el resultado obtenido.

**Cómo completar la tabla:** ejecute el caso, escriba lo que realmente mostró el programa en *Resultado obtenido* y marque el *Estado* (✅ Pasó / ❌ Falló). Si falla, abra un Issue y enlácelo en *Evidencia*.

**Datos de referencia:** meta de agua inicial = 2000 ml · tope de agua por registro = 2000 ml · rutina = 1 a 300 min · fecha en formato `AAAA-MM-DD`.

| ID | Historia | Sprint | Descripción | Entradas | Resultado esperado | Resultado obtenido | Estado | Responsable | Evidencia |
|----|----------|--------|-------------|----------|--------------------|--------------------|--------|-------------|-----------|
| CP-01 | HU-01 | 1 | Menú: opción válida y regreso al menú | `2`, `500`, Enter | Registra el agua y el menú vuelve a aparecer | _pendiente_ | ⬜ | | |
| CP-02 | HU-01 / HU-04 | 1 | Menú: opción inexistente y texto | `9`, luego `abc` | Muestra error por cada entrada, repite la pregunta y no se cierra | _pendiente_ | ⬜ | | |
| CP-03 | HU-01 | 1 | Salir del programa | `0` | Muestra "¡Hasta pronto!" y termina sin excepciones | _pendiente_ | ⬜ | | |
| CP-04 | HU-02 | 1 | Registrar comida válida | Nombre `Arepa con huevo`, tipo `1`, Enter (fecha de hoy) | Confirma con ID y la comida queda guardada como Desayuno | _pendiente_ | ⬜ | | |
| CP-05 | HU-02 / HU-04 | 1 | Comida con nombre vacío | Nombre vacío (Enter), luego `Pollo` | Rechaza el vacío, vuelve a pedir el nombre y no guarda nada hasta que sea válido | _pendiente_ | ⬜ | | |
| CP-06 | HU-02 / HU-04 | 1 | Tipo de comida fuera de la lista | Tipo `7`, luego `x`, luego `2` | Error en `7` y en `x`; acepta `2` (Almuerzo) | _pendiente_ | ⬜ | | |
| CP-07 | HU-03 | 1 | Agua válida | `500`, Enter | Registro con fecha de hoy y confirmación | _pendiente_ | ⬜ | | |
| CP-08 | HU-03 / HU-04 | 1 | Agua inválida (cero, negativa, letras, sobre el tope) | `0`, `-5`, `abc`, `2500` | Cada valor se rechaza con mensaje y se vuelve a pedir; no se guarda | _pendiente_ | ⬜ | | |
| CP-09 | HU-04 | 1 | Fecha inválida | `2026-02-30`, `20/10/2026`, luego `2026-10-20` | Rechaza las dos primeras y acepta la tercera | _pendiente_ | ⬜ | | |
| CP-10 | HU-05 | 1 | Rutina válida | Nombre `Correr`, minutos `30`, Enter | Registro con confirmación | _pendiente_ | ⬜ | | |
| CP-11 | HU-05 / HU-04 | 1 | Duración inválida de rutina | `0`, `-10`, `treinta`, `301` | Todas se rechazan sin guardar y se vuelve a pedir | _pendiente_ | ⬜ | | |
| CP-12 | HU-06 | 2 | Consultar por categoría | Registrar 2 comidas y 1 agua; consultar categoría `1` sin fecha (Enter) | Lista solo las 2 comidas | _pendiente_ | ⬜ | | |
| CP-13 | HU-06 | 2 | Filtrar por fecha | Registrar comida el `2026-10-20` y otra el `2026-10-19`; consultar `2026-10-19` | Muestra solo la comida del 19 | _pendiente_ | ⬜ | | |
| CP-14 | HU-06 | 2 | Consulta sin resultados | Categoría `3` (rutinas) sin rutinas registradas | Muestra "Sin registros." sin fallar | _pendiente_ | ⬜ | | |
| CP-15 | HU-07 | 2 | Eliminar registro existente | Eliminar el ID de un registro creado | Confirma la eliminación y el registro ya no aparece en consultas | _pendiente_ | ⬜ | | |
| CP-16 | HU-07 | 2 | Eliminar ID inexistente o no numérico | `99`, luego `abc` | `99`: "No existe un registro..."; `abc`: error de número entero; los datos no cambian | _pendiente_ | ⬜ | | |
| CP-17 | HU-08 | 2 | Configurar meta válida | `3000` | Meta actualizada a 3000 ml | _pendiente_ | ⬜ | | |
| CP-18 | HU-08 | 2 | Meta inválida conserva la anterior | `100`, `abc`, luego ver resumen | Rechaza ambas entradas y el resumen sigue usando la meta anterior | _pendiente_ | ⬜ | | |
| CP-19 | HU-09 | 3 | Resumen con datos | Desayuno + almuerzo, agua `500` + `500`, rutinas `Correr` 30 min y `correr` 20 min, meta 2000 | Comidas principales **2 de 3**; agua **1000 ml = 50.0 %**; minutos **50**; actividades distintas **1** | _pendiente_ | ⬜ | | |
| CP-20 | HU-09 | 3 | Resumen de un día sin datos | Resumen de `2026-01-01` | Todo en cero (0 de 3, 0 ml, 0.0 %, 0 min, 0 actividades) y aviso de que no es un diagnóstico | _pendiente_ | ⬜ | | |
| CP-21 | HU-09 | 3 | Resumen respeta la fecha | Datos en dos fechas distintas; resumen de una de ellas | Los totales incluyen solo los registros de esa fecha | _pendiente_ | ⬜ | | |

## Resumen de ejecución

- Casos ejecutados: _0 de 21_
- Casos que pasaron: _0_
- Casos que fallaron: _0_
- Fecha de ejecución: _pendiente_
- Ejecutado por: _pendiente_

> Mínimo exigido por el informe: 8 casos ejecutados y registrados antes de la entrega.
