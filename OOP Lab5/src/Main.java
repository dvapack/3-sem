import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        // y = 3x
        System.out.println("toString()");
        double[] values = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
        ArrayTabulatedFunction f1 = new ArrayTabulatedFunction(1, 10, values);
        LinkedListTabulatedFunction f2 = new LinkedListTabulatedFunction(1, 10, values);
        System.out.println(f1.toString());
        System.out.println(f2.toString());


        System.out.println("====================================================================");

        System.out.println("equals() для одинаковых функций");
        System.out.println("Первая: " + f1.toString());
        System.out.println("Вторая: " + f2.toString());
        System.out.println(f1.equals(f2));

        System.out.println("====================================================================");

        System.out.println("equals() для разных функций");
        double[] values3 = {4, 6, 9, 12, 17, 18, 21, 27, 29, 30};
        double[] values4 = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
        ArrayTabulatedFunction f3 = new ArrayTabulatedFunction(1, 10, values3);
        LinkedListTabulatedFunction f4 = new LinkedListTabulatedFunction(1, 10, values4);
        System.out.println("Первая: " + f3.toString());
        System.out.println("Вторая: " + f4.toString());
        System.out.println(f3.equals(f4));

        System.out.println("====================================================================");

        System.out.println("hashCode()");
        System.out.println("Первая: " + f1.toString() + ". Ее хеш код: " + f1.hashCode());
        System.out.println("Вторая: " + f2.toString() + ". Ее хеш код: " + f2.hashCode());
        System.out.println("Третья: " + f3.toString() + ". Ее хеш код: " + f3.hashCode());
        System.out.println("Четвертая: " + f4.toString() + ". Ее хеш код: " + f4.hashCode());

        System.out.println("====================================================================");

        System.out.println("клон f1");
        ArrayTabulatedFunction f1Cloned = (ArrayTabulatedFunction) f1.clone();
        System.out.println("Оригинал: " + f1.toString());
        System.out.println("Клон: " + f1Cloned.toString());
        f1Cloned.deletePoint(2);
        System.out.println("удаляем в клоне точку под индексом 2");
        System.out.println("Оригинал: " + f1.toString());
        System.out.println("Клон: " + f1Cloned.toString());
    }
}