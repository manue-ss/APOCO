package co.edu.udistrital.util.generador;

import co.edu.udistrital.model.Corrupto;
import java.util.Random;

/**
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
