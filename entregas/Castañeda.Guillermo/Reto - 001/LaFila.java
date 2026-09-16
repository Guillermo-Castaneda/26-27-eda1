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

        int personasAtendidas = 0;
        int personasDesistidas = 0;

        for (int minuto = 1; minuto <= 120; minuto++) {

            if (aleatorio.nextDouble() < 0.6) {
                if (size(fila) >= 30 && aleatorio.nextBoolean()) {
                    personasDesistidas++;
                } else {
                    boolean esPreferente = aleatorio.nextDouble() < 0.15;
                    Persona nueva = new Persona(minuto, esPreferente);

                    if (minuto >= 20 && esPreferente) {
                        int posicion = 0;
                        for (int indice = 0; indice < size(fila); indice++) {
                            if (fila.get(indice).preferente) {
                                posicion = indice + 1;
                            }
                        }
                        fila.add(posicion, nueva);
                    } else {
                        fila.add(nueva);
                    }
                }
            }

            if (minuto >= 20) {

                if (!estaVacia(fila) && aleatorio.nextDouble() < 0.1) {
                    if (!(size(fila) >= 30 && aleatorio.nextBoolean())) {
                        int posicionConocido = aleatorio.nextInt(size(fila));
                        fila.add(posicionConocido + 1, new Persona(minuto, false));
                    } else {
                        personasDesistidas++;
                    }
                }

                if (size(fila) >= 2 && aleatorio.nextDouble() < 0.05) {
                    int posicion = aleatorio.nextInt(size(fila));
                    fila.remove(posicion);
                }
            }

            if (aleatorio.nextDouble() < 0.4 && !estaVacia(fila)) {
                fila.remove(0);
                personasAtendidas++;
            }

            System.out.println("Minuto " + minuto + " - Longitud de la fila: " + size(fila) + " metros (" + size(fila) + " personas)");
        }

        System.out.println("\n--- RESUMEN DE LA SIMULACION ---");
        System.out.println("Personas atendidas: " + personasAtendidas);
        System.out.println("Personas en fila al cierre: " + size(fila));
        System.out.println("Personas que desistieron por cola larga: " + personasDesistidas);
    }
}