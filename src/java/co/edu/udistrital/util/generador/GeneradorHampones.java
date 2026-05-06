package co.edu.udistrital.util.generador;

import co.edu.udistrital.model.Hampon;
import java.util.Random;

/**
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
