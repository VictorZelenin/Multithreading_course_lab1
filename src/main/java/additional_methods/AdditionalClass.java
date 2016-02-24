package additional_methods;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import main.Main;

/**
 * Допоміжний клас для ініціалізації матриці та вектору ,
 * шляхом генерації нового файлу "in.txt" з випадковими числами,
 * зчитування файлу та виведення результату роботи.
 *
 * @author Victor Zelenin
 *         <p>
 *         Created by User on 20.02.2016.
 */
public class AdditionalClass {


    /**
     * Метод, який генерує випадкові матрицю та вектор, та записує їх до файлу
     *
     * @param N Велечина матриці та вектору
     * @return Файл із випадковою матрецею та вектором
     * @throws IOException
     */
    public static File createRandomTestFile(int N) throws IOException {
        File file = new File("in.txt");
        PrintWriter writer = new PrintWriter(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Can not create file");
            }
        }

        writer.print(N);
        writer.write('\n');

        for (int i = 0; i < N; i++) {
            writer.print('\n');
            for (int j = 0; j < N; j++) {

                writer.print(Math.round(Math.random() * 100));
                writer.print('\t');


            }
        }

        writer.print("\n\n");

        for (int i = 0; i < N; i++) {
            writer.println(Math.round(Math.random() * 100));
        }


        writer.close();


        return file;
    }

    /**
     * Метод для зчитування даних із файлу "in.txt"
     *
     * @param matrix Вхідна матриця
     * @param vector Вхідний вектор
     * @throws IOException
     */
    public static void input(int[][] matrix, int[] vector) throws IOException {

        createRandomTestFile(Main.N);

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("in.txt"));


        } catch (FileNotFoundException e) {
            System.err.println("No such file");
        }

        int N = scanner.nextInt();


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            vector[i] = scanner.nextInt();
        }
    }

    /**
     * Метод для запису результату обчислень в файл "out.txt"
     *
     * @param array Результуючий вектор
     * @return Новий файл із результатом
     * @throws IOException
     */
    public static File output(int[] array) throws IOException {
        File out = new File("out.txt");

        if (!out.exists()) {
            out.createNewFile();
        }

        PrintWriter writer = new PrintWriter(out);

        for (int i = 0; i < array.length; i++) {
            writer.println(array[i]);
        }

        writer.close();

        return out;
    }


}
