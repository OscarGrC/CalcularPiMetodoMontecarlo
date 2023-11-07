import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Chilo implements Callable<Double> {
    //con los 1000 resultados
    Double[] resultados = new Double[1000];
    int valoresMenores1 = 0;

    //creamos el generador aleatorio con un random con semilla o con ThreadLocalRandom
    // Random random = new Random(System.nanoTime());
    @Override
    public Double call() throws Exception {
        //inicializamos valores
        for (int i = 0; i < 1000; i++) {
            double valorAleatorioA = ThreadLocalRandom.current().nextDouble(0, 1); // Generar aleatorio entre 0 y 1
            double valorAleatorioB = ThreadLocalRandom.current().nextDouble(0, 1); // Generar aleatorio entre 0 y 1

            // calculamos la raiz de x2 + y2
            resultados[i] = Math.sqrt(Math.pow(valorAleatorioA, 2) + Math.pow(valorAleatorioB, 2));
            if (resultados[i] < 1) {
                valoresMenores1++;
            }
        }
        Double resultado = ((double) valoresMenores1 * 4) / 1000;
        return resultado;
    }
}
