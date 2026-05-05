/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.model;

import java.util.Objects;

/**
 * Representa a un Corrupto de APOCO, extendiendo la clase {@link Persona}.
 * Incluye información específica del individuo como la cantidad de dinero robado.
 * Implementa {@link Comparable}<{@link Corrupto}> para definir la ordenación .
 *
 * @see Persona
 * @author devapps (modificado)
 * @version 1.2 - Corregido Comparable
 */
// La declaración ahora es correcta y no entra en conflicto con la superclase
public class Corrupto extends Persona implements Comparable<Corrupto> {
    /** Formateador estándar para mostrar fechas de forma localizada y legible. */

    /** Fecha de inicio del pontificado (obligatoria). */
    private int  dineroRobado;


    /**
     * Construye una nueva instancia de Papa.
     *
     * @param nombre Nombre papal. No debe ser null.
     * @param edad Edad estimada (no negativa).
     * @param dineroRobado EL dinero que ha robado
     * @throws NullPointerException si {@code nombre} o {@code fechaInicioPapado} son null.
     * @throws IllegalArgumentException si {@code edad} es negativa.
     */
    public Corrupto (String nombre, int edad, int dineroRobado) {
        super(nombre, edad);
        this.dineroRobado = dineroRobado;
    }

    // --- Getters ---
    /** @return La canditad {@link dineroRobado} de dinero robado. */
    public int getDineroRobado() { return dineroRobado; }

    // --- Setters ---
    /** @param dineroRobado La nueva fecha (no debe ser null). */
    public void setDineroRobado(int dineroRobado) {
        this.dineroRobado = dineroRobado; }

    // --- Overrides ---

    /**
     * Devuelve una representación textual formateada del Papa.
     * @return Una cadena descriptiva del Papa.
     */
    @Override
    public String toString() {
        return String.format("Dinero Robado:%s", dineroRobado);
    }

    /**
     * Compara este Corrupto con otro objeto para determinar igualdad.
     * @param o El objeto a comparar.
     * @return {@code true} si son el mismo Corrupto (nombre, edad y dinero robado), {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corrupto corrupto = (Corrupto) o;
        return this.getNombre().equalsIgnoreCase(corrupto.getNombre()) && this.getEdad()==corrupto.getEdad() &&
               this.dineroRobado==corrupto.dineroRobado;
    }
        @Override
    public int hashCode() {
        
        return Objects.hash(getNombre().toLowerCase(), getEdad(), dineroRobado);
    }
    @Override
    public int compareTo(Corrupto otroCorrupto) {
        // Ejemplo: Ordenar de menor a mayor cantidad de dinero robado
        return Integer.compare(this.dineroRobado, otroCorrupto.dineroRobado);
        
        
    }

}




