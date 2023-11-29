import functions.*;
import functions.basic.Cos;
import functions.basic.Log;
import threads.*;

import java.io.IOException;
import java.util.Random;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        System.out.print("Task 1");
        double[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(1, 10, arr);
        for (FunctionPoint p : function1)
        {
            System.out.println(p);
        }
        System.out.println("\nTask 2");
        Function f = new Cos();
        TabulatedFunction tf;
        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        System.out.println(tf.getClass());
        TabulatedFunctions.setTabulatedFunctionFactory(new LinkedListTabulatedFunction.LinkedListTabulatedFunctionFactory());
        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        System.out.println(tf.getClass());
        TabulatedFunctions.setTabulatedFunctionFactory(new ArrayTabulatedFunction.ArrayTabulatedFunctionFactory());
        tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        System.out.println(tf.getClass());
    }
}