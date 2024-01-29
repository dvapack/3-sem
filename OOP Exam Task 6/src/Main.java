import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        double[][] matrix;
        int rows;
        int columns;
        double left;
        double right;
        String fileName;
        Scanner in = new Scanner(System.in);
        rows = in.nextInt();
        columns = in.nextInt();
        left = in.nextDouble();
        right = in.nextDouble();
        fileName = in.next();
        matrix = new double[rows][columns];
        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < columns; ++j);
            // matrix[i][j] = не понимаю как сделать это распределение
        }
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.writeObject(matrix);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}