/*
    Написать программу, вычисляющую среднее, а также минимальное и
    максимальное значения вещественных чисел, переданных как параметры при запуске
    программы. Полученные три числа следует вывести на экран (консоль).
    Рекомендуется писать программу, состоящую из одного класса, содержащего точку
    входа программы. Параметры при запуске могут указываться с ошибками.
*/

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Press 's' to stop input");
        double arr[] = new double[10];
        int amount = 0;
        try
        {
            Scanner in = new Scanner(System.in);
            while (true)
            {
                if (in.hasNext("s"))
                    break;
                if (in.hasNextDouble())
                {
                    if (amount > arr.length)
                    {
                        double buff_arr[] = new double[arr.length * 2];
                        System.arraycopy(arr, 0, buff_arr, 0, arr.length);
                        arr = buff_arr;
                    }
                    arr[amount] = in.nextDouble();
                    ++amount;
                } else throw new IOException("Not a double");
            }
        } catch (IOException e)
        {
            System.out.println(e);
        }
        //
        double min = arr[0];
        double max = arr[0];
        double med = arr[0];
        for (int i = 1; i < amount; ++i)
        {
            if (min > arr[i])
                min = arr[i];
            if (max < arr[i])
                max = arr[i];
            med += arr[i];
        }
        med = med / amount;
        System.out.println(min + " minimum\n" + med + " medium\n" + max + " maximum");
    }
}