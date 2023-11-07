import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

/***
 *
 Realizar una aplicación que calcule el valor de Pi (con el método indicado en clase) utilizando 32 threads
 con un Executor y con la devolución de los valores de cada thread en un Future.
 El valor de cada thread se acumulará y se dividirá entre el número de threads para calcular el valor final
 de pi como media de los calculados por los diferentes threads.
 Tened cuidado con la elección de la secuencia de números aleatorios utilizando ThreadLocalRandom
 o Random con una semilla diferente para cada Thread.
 Se pedirá por teclado el tiempo, en segundos, que se debe tardar en ejecutar la búsqueda de Pi y ejecutar
 durante ese tiempo.
 */

public class Main {
    //creamos la clase callable

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      /////////////
        double valorOptimo = 3.14159265358979323846;
      //////////
      //primero creamos un executor la pull de hilos
     ExecutorService MyExecutor = Executors.newFixedThreadPool(32);
        // Lista para almacenar los Futuros
        List<Future<Double>> futures = new ArrayList<>();
        // pedimos al usuario tiempo ejecucion hilos
        Scanner scanner = new Scanner(System.in);
        int tiempo = 0;
        // Crear y enviar tareas
        for (int i = 0; i < 32; i++) {
            Future<Double> tarea = MyExecutor.submit(new Chilo());
            futures.add(tarea);
        }
        //esperamos a que terminen todos los hilos esto se pide por pantalla
       futures.get(31);

        // Mostrar los resultados de todos los futures y aprobechamos para sumar todos los valores para la media
        Double media = 0.0;
        for (int i = 0; i < 32 ; i++) {
            System.out.println("Resultado del hilo " + (i+1) + ": " + futures.get(i).get().doubleValue());
            media+= futures.get(i).get().doubleValue();
        }
        media = media/32;
        System.out.println("MEDIA: "+media);
        double errorRelativo = (Math.abs(valorOptimo - media) / valorOptimo) * 100.0;
        System.out.println("Error relativo: " + errorRelativo + " %");

        System.out.println("Ahora con el tiempo maximo que se le dio al programa para cada hilo");


        System.out.print("Por favor, introduce un valor: ");
        try {
             tiempo = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.err.println("No se pudo convertir el String a un número entero válido.");
        }

        List<Future<Double>> futures2 = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            Future<Double> tarea2 = MyExecutor.submit(new CHiloTime(tiempo));
            futures2.add(tarea2);
        }
        //esperamos a que terminen todos los hilos esto se pide por pantalla
        Thread.sleep(tiempo*1000);

        // Mostrar los resultados de todos los futures y aprobechamos para sumar todos los valores para la media
         media = 0.0;
        for (int i = 0; i < 32 ; i++) {
            System.out.println("Resultado del hilo " + (i+1) + ": " + futures.get(i).get().doubleValue());
            media+= futures2.get(i).get().doubleValue();
        }
        media = media/32;
        errorRelativo = (Math.abs(valorOptimo - media) / valorOptimo) * 100.0;
        System.out.println("MEDIA: "+media);
        System.out.println("Error relativo: " + errorRelativo + " %");
        // Cerrar el ExecutorService
        MyExecutor.shutdown();
    }
}


