package co.edu.udistrital.util.generador;

import co.edu.udistrital.model.entities.Corrupto;
import java.util.Random;

/**
 * Generador específico para instanciar objetos de tipo {@link Corrupto} con datos aleatorios.
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public class GeneradorCorruptos extends Generador<Corrupto> {

    @Override
    protected Corrupto crearEntidad(String nombre, int edad) {
        Random random = new Random();
        double dinero = random.nextDouble(50000000);

        return new Corrupto(nombre, edad, dinero);
    }

}
