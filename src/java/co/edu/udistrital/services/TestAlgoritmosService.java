package co.edu.udistrital.services;

import co.edu.udistrital.model.dto.ResultadoPrueba; // Asumiendo que este es tu DTO
import co.edu.udistrital.util.algoritmos.EstrategiaOrdenamiento;
import co.edu.udistrital.util.listas.ListaEnlazadaSimple;

/**
 * Servicio genérico para probar cualquier algoritmo de ordenamiento sobre
 * cualquier tipo de lista (Corruptos o Hampones).
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public class TestAlgoritmosService<T extends Comparable<T>> {

    /**
     * @param lista           La lista original (no se modificará)
     * @param algoritmo       La instancia del algoritmo a probar (ej. new
     *                        OrdenamientoBurbuja<>())
     * @param nombreAlgoritmo El nombre en texto para el reporte (ej. "Burbuja")
     *
     * @return DTO con los resultados de la prueba
     */
    public ResultadoPrueba test(ListaEnlazadaSimple<T> lista, 
            EstrategiaOrdenamiento<T> algoritmo, String nombreAlgoritmo) {

        // 1. Clonamos la lista manteniendo el genérico <T>
        ListaEnlazadaSimple<T> listaClon = lista.clonarLista();

        // 2. Usamos 'long' para los nanosegundos
        long tiempoInicio = System.nanoTime();

        // 3. Ejecutamos el algoritmo específico y capturamos iteraciones
        long iteraciones = algoritmo.ordenar(listaClon);

        long tiempoFin = System.nanoTime();
        long tiempoTotal = tiempoFin - tiempoInicio;

        // 4. Retornamos el DTO empaquetado
        return new ResultadoPrueba(nombreAlgoritmo, iteraciones, tiempoTotal);
    }
}
