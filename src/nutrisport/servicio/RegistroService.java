package nutrisport.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nutrisport.modelo.Comida;
import nutrisport.modelo.ConsumoAgua;
import nutrisport.modelo.RutinaDeportiva;
import nutrisport.modelo.TipoComida;
import nutrisport.util.Constantes;
import nutrisport.util.DatoInvalidoException;
import nutrisport.util.Validador;


public class RegistroService {

    private final List<Comida> comidas = new ArrayList<>();
    private final List<ConsumoAgua> aguas = new ArrayList<>();
    private final List<RutinaDeportiva> rutinas = new ArrayList<>();

    private int siguienteId = 1;
    private int metaAguaMl = Constantes.META_AGUA_INICIAL_ML;


    public Comida registrarComida(String nombre, TipoComida tipo, LocalDate fecha) throws DatoInvalidoException {

        Comida comida = new Comida(siguienteId, nombre, tipo, fecha);
        comidas.add(comida);
        siguienteId++;
        return comida;
    }

    public ConsumoAgua registrarAgua(int ml, LocalDate fecha) throws DatoInvalidoException {
        ConsumoAgua agua = new ConsumoAgua(siguienteId, ml, fecha);
        aguas.add(agua);
        siguienteId++;
        return agua;
    }

    public RutinaDeportiva registrarRutina(String nombre, int minutos, LocalDate fecha) throws DatoInvalidoException {
        RutinaDeportiva rutina = new RutinaDeportiva(siguienteId, nombre, minutos, fecha);
        rutinas.add(rutina);
        siguienteId++;
        return rutina;
    }



    public List<Comida> listarComidas(LocalDate fecha) {
        List<Comida> resultado = new ArrayList<>();
        for (Comida c : comidas) {
            if (fecha == null || c.getFecha().equals(fecha)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<ConsumoAgua> listarAgua(LocalDate fecha) {
        List<ConsumoAgua> resultado = new ArrayList<>();
        for (ConsumoAgua a : aguas) {
            if (fecha == null || a.getFecha().equals(fecha)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public List<RutinaDeportiva> listarRutinas(LocalDate fecha) {
        List<RutinaDeportiva> resultado = new ArrayList<>();
        for (RutinaDeportiva r : rutinas) {
            if (fecha == null || r.getFecha().equals(fecha)) {
                resultado.add(r);
            }
        }
        return resultado;
    }


    public void eliminarRegistro(int id) throws DatoInvalidoException {
        for (int i = 0; i < comidas.size(); i++) {
            if (comidas.get(i).getId() == id) {
                comidas.remove(i);
                return;
            }
        }
        for (int i = 0; i < aguas.size(); i++) {
            if (aguas.get(i).getId() == id) {
                aguas.remove(i);
                return;
            }
        }
        for (int i = 0; i < rutinas.size(); i++) {
            if (rutinas.get(i).getId() == id) {
                rutinas.remove(i);
                return;
            }
        }
        throw new DatoInvalidoException("No existe un registro con el identificador " + id + ".");
    }



    public int getMetaAgua() {
        return metaAguaMl;
    }

    /** Si el valor es inválido lanza la excepción y la meta anterior se conserva. */
    public void configurarMetaAgua(int ml) throws DatoInvalidoException {
        Validador.validarRango(ml, "meta diaria de agua (ml)",
                Constantes.META_AGUA_MIN_ML, Constantes.META_AGUA_MAX_ML);
        this.metaAguaMl = ml;
    }
}
