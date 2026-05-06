/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.model.entities;

import java.util.Objects;

/**
 * Representa a un Hampón en el sistema, extendiendo la clase {@link Persona}.
 * Incluye información específica del individuo como la cantidad de dinero que
 * está dispuesto a robar. Implementa {@link Comparable} para definir la
 * ordenación por cantidad de dinero a robar.
 *
 * @author acurr
 */
public class Hampon extends Persona implements Comparable<Hampon> {

    private double dineroARobar;

    /**
     * Construye una nueva instancia de Hampon.
     *
     * @param nombre       Nombre del Hampon. No debe ser null.
     * @param edad         Edad estimada (no negativa).
     * @param dineroARobar El dinero que está dispuesto a robar.
     *
     * @throws NullPointerException     si {@code nombre} es null.
     * @throws IllegalArgumentException si {@code edad} es negativa.
     */
    public Hampon(String nombre, int edad, double dineroARobar) {
        super(nombre, edad);
        this.dineroARobar = dineroARobar;
    }

    // --- Getters ---
    /**
     * @return La canditad {@link dineroARobado} de dinero a robar.
     */
    public double getDineroARobar() {
        return dineroARobar;
    }

    // --- Setters ---
    /**
     * @param dineroARobar
     */
    public void setDineroARobar(double dineroARobar) {
        this.dineroARobar = dineroARobar;
    }

    // --- Overrides ---
    /**
     * Devuelve una representación textual formateada del Hampon.
     *
     * @return Una cadena descriptiva del Hampon.
     */
    @Override
    public String toString() {
        return String.format("Dinero que esta dispuesto a Robar:%s", dineroARobar);
    }

    /**
     * Compara este Hampon con otro objeto para determinar igualdad.
     *
     * @param o El objeto a comparar.
     *
     * @return {@code true} si son el mismo Hampon (nombre, edad y dinero
     *         robado), {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hampon hampon = (Hampon) o;
        return this.getNombre().equalsIgnoreCase(hampon.getNombre()) && this.getEdad() == hampon.getEdad()
                && this.dineroARobar == hampon.dineroARobar;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNombre().toLowerCase(), getEdad(), dineroARobar);
    }

    @Override
    public int compareTo(Hampon otroHampon) {

        int comparacionDinero = Double.compare(otroHampon.getDineroARobar(), this.getDineroARobar());

        if (comparacionDinero == 0) {
            return Integer.compare(this.getEdad(), otroHampon.getEdad());
        }

        return comparacionDinero;
    }

}
