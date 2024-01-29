import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String inputFileName;
        String outputFileName;
        Scanner in = new Scanner(System.in);
        inputFileName = in.next();
        outputFileName = in.next();
        //десериализация
        double[] arr = new double[0];
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(inputFileName));
            arr = (double[]) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Ошибка чтения из файла");
        }
        //сортировка
        Arrays.sort(arr);
        //сериализация
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(outputFileName));
            output.writeObject(arr);
            output.close();
        } catch (IOException e)
        {
            System.out.println("Ошибка чтения из файла");
        }

    }

}