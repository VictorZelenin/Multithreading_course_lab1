package benchmarks;

import main.Main;
import multithreading_multiply.MultiplierThread;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by User on 24.02.2016.
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Benchmarks {


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmarks.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public int[] multiplyParallel() throws InterruptedException,
            IOException {

        int N = 10;
        int numberOfThreads = 5;

        int[][] matrix = new int[N][N];
        int[] vector = new int[N];

        Main.bigInit(matrix, vector);


        int[] result = new int[vector.length];
        int resultIndex = 0;


        MultiplierThread[] threads = new MultiplierThread[numberOfThreads];


        // створюємо задану кількість потоків та запускаємо їх
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new MultiplierThread(matrix, vector, i * vector.length / numberOfThreads,
                    (i + 1) * vector.length / numberOfThreads);
            threads[i].start();


        }


        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
            for (int j = 0; j < threads[i].getResult().length; j++) {
                result[resultIndex] = threads[i].getResult()[j];
                resultIndex++;
            }
        }


//        output(result);

        return result;
    }

    @Benchmark
    public static int[] multiply() throws IOException {

        int N = 10;

        int[][] matrix = new int[N][N];
        int[] vector = new int[N];

        Main.bigInit(matrix, vector);

        int[] result = new int[vector.length];

        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        // записуємо у файл
//        output(result);

        return result;
    }


}
