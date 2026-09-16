import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulacionFila {

    static class Persona {
        int minutoLlegada;
        boolean preferente;

        public Persona(int minutoLlegada, boolean preferente) {
            this.minutoLlegada = minutoLlegada;
            this.preferente = preferente;
        }
    }

    public static int size(List<Persona> lista) {
        return lista.size();
    }

    public static boolean estaVacia(List<Persona> lista) {
        return lista.isEmpty();
    }

    public static void main(String[] argumentos) {
        Random aleatorio = new Random();
        List<Persona> fila = new ArrayList<>();

        for (int minuto = 1; minuto <= 120; minuto++) {

            if (aleatorio.nextDouble() < 0.6) {
                fila.add(new Persona(minuto, false));
            }

            System.out.println("Minuto " + minuto + " - Longitud de la fila: " + size(fila) + " metros (" + size(fila) + " personas)");
        }
    }
}