/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.util.algoritmos;
import co.edu.udistrital.util.listas.NodoDoble;
import co.edu.udistrital.util.listas.ListaEnlazadaDoble;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo Quick Sort (ordenación rápida).
 * Es un algoritmo eficiente de tipo "divide y vencerás". Selecciona un elemento como pivote
 * y particiona la lista de forma que los elementos menores que el pivote queden a un lado
 * y los mayores al otro, luego ordena recursivamente las sublistas.
 *
 * <p><b>Nota Importante:</b> Esta implementación específica utiliza el <strong>último elemento</strong>
 * de la (sub)lista como pivote e intercambia los <strong>datos</strong> contenidos en los nodos,
 * no los nodos en sí. Por lo tanto, requiere que la clase {@link NodoDoble} posea un método {@code setDato(T)}.</p>
 * <p>QuickSort en listas enlazadas puede ser menos eficiente que en arrays debido a la dificultad
 * de acceso aleatorio y la complejidad de la partición.</p>
 *
 * <p>Complejidad Temporal: O(n log n) en promedio, O(n^2) en el peor caso (ej. lista ya ordenada).</p>
 * <p>Complejidad Espacial: O(log n) en promedio (pila recursión), O(n) en peor caso.</p>
 *
 * @param <T> El tipo de elementos en la lista, debe ser {@link Comparable}.
 * @author devapps
 * @version 1.1
 */
public class OrdenamientoQuickSort<T extends Comparable<T>> implements EstrategiaOrdenamiento<T> {

    /**
     * Ordena la lista dada usando el algoritmo Quick Sort, usando el último nodo como pivote
     * y realizando intercambio de datos.
     *
     * @param lista La lista {@link ListaEnlazadaDoble} a ordenar. No debe ser null.
     * @throws NullPointerException si {@code lista} es null.
     * @throws ClassCastException si los elementos no son {@code Comparable}.
     * @throws UnsupportedOperationException si {@link NodoDoble#setDato(Object)} no está disponible (implícito).
     */
    @Override
    public void ordenar(ListaEnlazadaDoble<T> lista) {
        Objects.requireNonNull(lista, "La lista a ordenar no puede ser null.");

        if (lista.getTamanno() <= 1) {
            return; // Nada que ordenar
        }

        NodoDoble<T> cabeza = lista.getCabeza();
        NodoDoble<T> cola = encontrarCola(cabeza); // Necesitamos la cola para el pivote inicial

        quickSortRecursivo(cabeza, cola);

        // Después de ordenar intercambiando datos, la estructura de nodos es la misma,
        // pero los datos están reordenados. La cabeza física puede seguir siendo la misma.
        // Llamar a setCabeza asegura que la cola se recalcule correctamente si fuera necesario
        // (aunque en este caso de intercambio de datos, no debería cambiar estructuralmente).
        lista.setCabeza(cabeza);
    }

    /**
     * Encuentra el último nodo (cola) de una lista enlazada simple.
     *
     * @param nodo El nodo desde el cual empezar a buscar (normalmente la cabeza).
     * @return El último {@link NodoDoble} de la lista, o {@code null} si el nodo inicial es {@code null}.
     */
    private NodoDoble<T> encontrarCola(NodoDoble<T> nodo) {
        if (nodo == null) {
            return null;
        }
        while (nodo.getSiguiente() != null) {
            nodo = nodo.getSiguiente();
        }
        return nodo;
    }

    /**
     * Función recursiva principal de QuickSort. Ordena la (sub)lista delimitada
     * por los nodos {@code cabezaSubLista} y {@code colaSubLista}.
     *
     * @param cabezaSubLista El primer nodo de la (sub)lista a ordenar.
     * @param colaSubLista El último nodo (pivote) de la (sub)lista a ordenar.
     */
    private void quickSortRecursivo(NodoDoble<T> cabezaSubLista, NodoDoble<T> colaSubLista) {
        // Condiciones de parada de la recursión:
        // 1. La sublista es inválida (null).
        // 2. La sublista tiene 0 o 1 elemento (cabeza == cola).
        // 3. La cola está antes que la cabeza (indicativo de sublista vacía o error).
        if (cabezaSubLista == null || colaSubLista == null || cabezaSubLista == colaSubLista || cabezaSubLista == colaSubLista.getSiguiente()) {
            return;
        }

        // 1. Particionar la sublista y obtener la nueva posición del pivote
        //    'resultadoParticion[0]' es el nodo pivote en su posición final.
        //    'resultadoParticion[1]' es el nodo justo antes del pivote final.
        NodoDoble<T>[] resultadoParticion = particionar(cabezaSubLista, colaSubLista);
        NodoDoble<T> nodoPivoteFinal = resultadoParticion[0];
        NodoDoble<T> nodoAntesPivote = resultadoParticion[1];

        // 2. Llamar recursivamente para ordenar la sublista a la izquierda del pivote
        //    La sublista izquierda va desde 'cabezaSubLista' hasta 'nodoAntesPivote'.
        //    Solo se llama si el pivote no terminó siendo la cabeza original de la sublista.
        if (nodoAntesPivote != null && nodoPivoteFinal != cabezaSubLista) {
           quickSortRecursivo(cabezaSubLista, nodoAntesPivote);
        } else if (nodoAntesPivote == null && nodoPivoteFinal != cabezaSubLista){
             // Si nodoAntesPivote es null pero el pivote no es la cabeza,
             // significa que todos los elementos eran >= pivote, la sublista izquierda está vacía.
        }


        // 3. Llamar recursivamente para ordenar la sublista a la derecha del pivote
        //    La sublista derecha va desde 'nodoPivoteFinal.getSiguiente()' hasta 'colaSubLista'.
         if (nodoPivoteFinal != null && nodoPivoteFinal != colaSubLista) { // Evitar llamada si pivote es el último
             quickSortRecursivo(nodoPivoteFinal.getSiguiente(), colaSubLista);
         }
    }

    /**
     * Particiona la (sub)lista (desde {@code cabeza} hasta {@code cola}) usando el dato
     * del nodo {@code cola} como pivote. Reorganiza los <strong>datos</strong> de los nodos
     * para que los menores queden antes del pivote y los mayores después.
     *
     * @param cabeza El primer nodo de la (sub)lista a particionar.
     * @param cola El último nodo (pivote) de la (sub)lista a particionar.
     * @return Un array de {@link NodoDoble}: {@code [nodoPivoteFinal, nodoAntesDelPivoteFinal]}.
     *         El {@code nodoAntesDelPivoteFinal} puede ser {@code null} si el pivote termina en la cabeza.
     * @throws UnsupportedOperationException si {@link NodoDoble#setDato(Object)} no está disponible (implícito).
     */
    private NodoDoble<T>[] particionar(NodoDoble<T> cabeza, NodoDoble<T> cola) {
        // No necesita verificar null aquí si se llama desde quickSortRecursivo con validación previa.
        T valorPivote = cola.getDato();

        // 'i' rastrea la posición donde debería ir el siguiente elemento menor que el pivote.
        // Inicialmente está "antes" de la cabeza.
        NodoDoble<T> i = null;
        NodoDoble<T> actual = cabeza;

        // Recorrer hasta el nodo ANTES de la cola (pivote)
        while (actual != cola) {
            if (actual.getDato().compareTo(valorPivote) < 0) {
                // Elemento menor encontrado. Mover 'i' a la posición correcta e intercambiar datos.
                i = (i == null) ? cabeza : i.getSiguiente(); // Avanzar 'i'
                // Intercambiar datos entre 'actual' y 'i'
                T temp = actual.getDato();
                try {
                     actual.setDato(i.getDato());
                     i.setDato(temp);
                } catch (Exception e) {
                     throw new UnsupportedOperationException(
                         "La partición de QuickSort requiere un método setDato(T) en la clase Nodo.", e);
                }
            }
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }

        // Bucle terminado. Colocar el pivote en su lugar final.
        // La posición final es justo después de 'i'.
        i = (i == null) ? cabeza : i.getSiguiente(); // Avanzar 'i' una última vez

        // Intercambiar dato del pivote (en 'cola') con el dato en la posición 'i'
        T temp = cola.getDato();
         try {
            cola.setDato(i.getDato());
            i.setDato(temp);
         } catch (Exception e) {
            throw new UnsupportedOperationException(
                "La partición de QuickSort requiere un método setDato(T) en la clase Nodo.", e);
         }


        // Encontrar el nodo antes de 'i' (la posición final del pivote)
        NodoDoble<T> nodoAntesPivote = null;
        if (i != cabeza) {
            NodoDoble<T> buscador = cabeza;
            while (buscador != null && buscador.getSiguiente() != i) {
                buscador = buscador.getSiguiente();
            }
            nodoAntesPivote = buscador;
        }

        // Devolver el nodo donde quedó el pivote ('i') y el nodo anterior a él.
        @SuppressWarnings("unchecked")
        NodoDoble<T>[] resultado = (NodoDoble<T>[]) new NodoDoble<?>[2];
        resultado[0] = i;               // Nodo pivote en posición final
        resultado[1] = nodoAntesPivote; // Nodo antes del pivote final
        return resultado;
    }
}