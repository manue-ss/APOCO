package co.edu.udistrital.model.dto;

/**
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public class ResultadoPrueba {

    private final String nombreAlgoritmo;
    private final long iteraciones;
    private final double tiempo;

    public ResultadoPrueba(String nombreAlgoritmo, Long iteraciones, double tiempo) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.iteraciones = iteraciones;
        this.tiempo = tiempo;
    }

    public ResultadoPrueba() {
        this.nombreAlgoritmo = "";
        this.iteraciones = 0;
        this.tiempo = 0.0f;
    }

    public String getNombreAlgoritmo() {
        return nombreAlgoritmo;
    }

    public Long getIteraciones() {
        return iteraciones;
    }

    public double getTiempo() {
        return tiempo;
    }

}
