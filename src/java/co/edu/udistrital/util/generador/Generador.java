package co.edu.udistrital.util.generador;

import co.edu.udistrital.util.listas.ListaEnlazadaDoble;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public abstract class Generador<T> {

    private static final String[] NOMBRES = {};
    private static final String[] APELLIDOS = {};
    protected final Random random = new Random();

    private String generarNombre() {
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
        String apellido = APELLIDOS[random.nextInt(APELLIDOS.length)];
        return nombre + " " + apellido;
    }

    private int generarEdad() {
        return 18 + random.nextInt(63);
    }

    public ListaEnlazadaDoble<T> generarLista(int cantidad) {
        ListaEnlazadaDoble<T> lista = new ListaEnlazadaDoble<>();
        Set<String> nombresUsados = new HashSet<>();

        while (nombresUsados.size() < cantidad) {
            String posibleNombre = generarNombre();

            if (nombresUsados.add(posibleNombre)) {
                int edad = generarEdad();
                T nuevaEntidad = crearEntidad(posibleNombre, edad);
                lista.agregar(nuevaEntidad);
            }
        }
        return lista;
    }

    protected abstract T crearEntidad(String nombre, int edad);
}
