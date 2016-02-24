package multithreading_multiply;

/**
 *
 * @author Victor Zelenin
 *
 *
 * Created by User on 18.02.2016.
 */
public class MultiplierThread extends Thread {

    /** Вхідні дані. */
    private final int[][] matrix;
    private final int[] vector;

    /** Границі обчислень для поточного потоку. */
    private int startRow;
    private int endRow;

    /** Проміжний вектор відовіді. */
    private int[] result;

    public final int N;


    /**
     *  @param matrix    Вхідна матриця
     *  @param vector    Вхідний вектор
     *  @param startRow  Початковий індекс
     *  @param endRow    Кінцевий індекс
     */
    public MultiplierThread(int[][] matrix, int[] vector, int startRow, int endRow) {
        N = vector.length;
        if (matrix.length != vector.length) {
            throw new UnsupportedOperationException();
        } else {
            this.matrix = matrix;
            this.vector = vector;
            this.startRow = startRow;
            this.endRow = endRow;
            result = new int[endRow - startRow];

//            this.start();

        }

    }

    public int[] getResult() {
        return result;
    }


    /** Основний метод потоку. */
    @Override
    public void run() {

        int k = 0;
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[k] += matrix[i][j] * vector[j];
            }
            k++;
        }

    }


}
