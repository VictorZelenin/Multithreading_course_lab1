package main;


import java.io.*;

import multithreading_multiply.MultiplierThread;
import static additional_methods.AdditionalClass.*;

/**
 * @author Victor Zelenin
 *         <p>
 *         Created by User on 18.02.2016.
 */
public class Main {

    /**
     * Параметри програми ,
     * N - розмір даних,
     * numberOfThreads - кіліксть потоків
     */
    public final static int N = 10;
    public static int numberOfThreads = 4;


    public static void main(String[] args) throws IOException, InterruptedException {


        int[][] matrix = new int[N][N];
        int[] vector = new int[N];

//        input(matrix, vector);
        bigInit(matrix, vector);


//        multiply(matrix, vector);

        multiplyParallel(matrix, vector, numberOfThreads);


    }


    /**
     * @param matrix Вхідна матриця ( N x N )
     * @param vector Вхідний вектор ( N )
     * @throws InterruptedException
     * @throws IOException
     */

    public static int[] multiplyParallel(int[][] matrix, int[] vector, int numberOfThreads) throws InterruptedException,
            IOException {
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


        output(result);

        return result;
    }

    /**
     * Послідовна реалізаця заданої задачі
     *
     * @param matrix Вхідна матриця ( N x N )
     * @param vector Вхідний вектор ( N )
     * @return Резулютуючий вектор
     * @throws IOException
     */
    public static int[] multiply(int[][] matrix, int[] vector) throws IOException {

        int[] result = new int[vector.length];

        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        // записуємо у файл
        output(result);

        return result;
    }


    public static void bigInit(int[][] matrix, int[] vector) {

        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                matrix[i][j] = (int) Math.round(Math.random() * 100);
            }
        }
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int) Math.round(Math.random() * 100);
        }

    }
}
