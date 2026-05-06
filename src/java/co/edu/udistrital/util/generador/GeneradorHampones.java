package co.edu.udistrital.util.generador;

import co.edu.udistrital.model.entities.Hampon;
import java.util.Random;

/**
 * Generador específico para instanciar objetos de tipo {@link Hampon} con datos aleatorios.
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public class GeneradorHampones extends Generador<Hampon> {

    @Override
    protected Hampon crearEntidad(String nombre, int edad) {
        Random random = new Random();
        double dinero = random.nextDouble(50000000);
        
        return new Hampon(nombre, edad, dinero);
    }
}
