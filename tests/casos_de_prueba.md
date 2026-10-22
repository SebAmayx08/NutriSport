# Casos de prueba — NutriSport

Cada caso se ejecuta por consola. Completar las columnas "Resultado obtenido" y "Estado" al probar.

| ID | Historia | Entrada / acción | Resultado esperado | Resultado obtenido | Estado |
|----|----------|------------------|--------------------|--------------------|--------|
| CP-01 | HU-01 | Escribir `9` y luego `abc` en el menú | Muestra error y repite el menú sin cerrarse | | |
| CP-02 | HU-02 | Registrar comida "Avena", tipo Desayuno, fecha Enter | Confirma con ID y la comida queda guardada | | |
| CP-03 | HU-02/04 | Registrar comida con nombre vacío | Rechaza, vuelve a pedir el nombre, no guarda | | |
| CP-04 | HU-03 | Registrar agua `500` | Confirma con ID y fecha de hoy | | |
| CP-05 | HU-03/04 | Registrar agua `0`, `-5`, `abc`, `2500` | Cada valor se rechaza y se vuelve a pedir | | |
| CP-06 | HU-04 | Fecha `2025-02-30` | Error de fecha inválida y repite la pregunta | | |
| CP-07 | HU-05 | Rutina "Yoga", `30` min | Confirma con ID y se guarda | | |
| CP-08 | HU-05/04 | Rutina con duración `0` y luego `301` | Se rechazan y no se guardan | | |
| CP-09 | HU-06 | Consultar rutinas con una fecha sin datos | Muestra "Sin registros." | | |
| CP-10 | HU-07 | Eliminar ID existente y luego ID `999` | El primero desaparece de las consultas; el segundo informa que no existe | | |
| CP-11 | HU-08/09 | Meta `2000`; registrar 1000 ml; resumen de hoy | Muestra 1000 ml de 2000 ml (50.0 %) y el aviso de no diagnóstico | | |
