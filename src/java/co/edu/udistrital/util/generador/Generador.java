package co.edu.udistrital.util.generador;

import co.edu.udistrital.util.listas.ListaEnlazadaSimple;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Clase abstracta base para la generación automatizada de entidades.
 * Proporciona métodos para generar listas de entidades con datos aleatorios.
 *
 * @param <T> El tipo de entidad a generar.
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public abstract class Generador<T> {

    private static final String[] NOMBRES = {
        // Femeninos
        "María", "Sofía", "Valentina", "Isabella", "Camila", "Mariana", "Valeria", "Lucía", "Daniela", "Paula",
        "Andrea", "Natalia", "Ana", "Sara", "Victoria", "Laura", "Elena", "Carmen", "Rosa", "Marta",
        "Patricia", "Julia", "Alba", "Claudia", "Clara", "Carolina", "Gabriela", "Alejandra", "Diana", "Luisa",
        "Margarita", "Teresa", "Beatriz", "Silvia", "Lorena", "Alicia", "Raquel", "Mónica", "Inés", "Celia",
        "Marina", "Eva", "Nuria", "Gloria", "Blanca", "Aurora", "Rocío", "Lourdes", "Yolanda", "Milagros",
        // Masculinos
        "Alejandro", "Carlos", "Juan", "Diego", "Mateo", "Nicolás", "Samuel", "Daniel", "Luis", "José",
        "Fernando", "Jorge", "Gabriel", "Martín", "Andrés", "David", "Pablo", "Miguel", "Manuel", "Eduardo",
        "Javier", "Roberto", "Ricardo", "Francisco", "Pedro", "Hugo", "Julián", "Simón", "Tomás", "Lucas",
        "Joaquín", "Ignacio", "Sebastián", "Emilio", "Antonio", "Marcos", "Rafael", "Mario", "Óscar", "Rubén",
        "Víctor", "Raúl", "Arturo", "Guillermo", "Enrique", "Gerardo", "Alberto", "Rodrigo", "Gonzalo", "Bruno"
    };

    private static final String[] APELLIDOS = {
        "García", "Rodríguez", "González", "Fernández", "López", "Martínez", "Sánchez", "Pérez", "Gómez", "Martín",
        "Jiménez", "Ruiz", "Hernández", "Díaz", "Moreno", "Muñoz", "Álvarez", "Romero", "Alonso", "Gutiérrez",
        "Navarro", "Torres", "Domínguez", "Vázquez", "Ramos", "Gil", "Ramírez", "Serrano", "Blanco", "Molina",
        "Morales", "Suárez", "Ortega", "Delgado", "Castro", "Ortiz", "Rubio", "Marín", "Sanz", "Núñez",
        "Iglesias", "Medina", "Garrido", "Cortés", "Castillo", "Santos", "Lozano", "Guerrero", "Cano", "Prieto",
        "Méndez", "Cruz", "Calvo", "Gallego", "Vidal", "León", "Márquez", "Herrera", "Peña", "Flores",
        "Cabrera", "Campos", "Vega", "Fuentes", "Carrasco", "Diez", "Reyes", "Aguilar", "Nieto", "Santana",
        "Pascual", "Herrero", "Giménez", "Montero", "Hidalgo", "Lorenzo", "Ibáñez", "Garzón", "Ferrer", "Rojas",
        "Salazar", "Arias", "Páez", "Soto", "Silva", "Vargas", "Ríos", "Mejía", "Mendoza", "Parra",
        "Acosta", "Cárdenas", "Pineda", "Montoya", "Escobar", "Valencia", "Jaramillo", "Osorio", "Guzmán", "Restrepo"
    };
    protected final Random random = new Random();

    private String generarNombre() {
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
        String apellido = APELLIDOS[random.nextInt(APELLIDOS.length)];
        return nombre + " " + apellido;
    }

    private int generarEdad() {
        return 18 + random.nextInt(63);
    }

    public ListaEnlazadaSimple<T> generarLista(int cantidad) {
        ListaEnlazadaSimple<T> lista = new ListaEnlazadaSimple<>();
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
