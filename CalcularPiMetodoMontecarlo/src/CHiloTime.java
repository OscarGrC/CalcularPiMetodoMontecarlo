import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class CHiloTime implements Callable<Double> {
    // Tiempo en segundos
    private final int tiempoSegundos;

    public CHiloTime(int tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
    }

    @Override
    public Double call() throws Exception {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + tiempoSegundos * 1000;

        int valoresMenores1 = 0;
        int totalValores = 0;

        while (System.currentTimeMillis() < endTime) {
            double valorAleatorioA = ThreadLocalRandom.current().nextDouble(0, 1);
            double valorAleatorioB = ThreadLocalRandom.current().nextDouble(0, 1);

            // Calculamos la raíz de x^2 + y^2
            double resultado = Math.sqrt(Math.pow(valorAleatorioA, 2) + Math.pow(valorAleatorioB, 2));

            if (resultado <= 1) {
                valoresMenores1++;
            }

            totalValores++;
        }
        Double media = ((double) valoresMenores1 * 4) / totalValores;
        return media;
    }
}
