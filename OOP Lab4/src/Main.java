import functions.Function;
import functions.Functions;
import functions.TabulatedFunction;
import functions.TabulatedFunctions;
import functions.basic.Cos;
import functions.basic.Exp;
import functions.basic.Log;
import functions.basic.Sin;

import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        System.out.println("Синус");
        Sin sin = new Sin();
        for (double x = 0; x <2 * Math.PI ; x +=0.1)
            System.out.println("sin(" + x + ") = " + sin.getFunctionValue(x));
        System.out.println("====================================================================");

        System.out.println("Косинус");
        Cos cos = new Cos();
        for (double x = 0; x <2 * Math.PI ; x +=0.1)
            System.out.println("cos(" + x + ") = " + cos.getFunctionValue(x));
        System.out.println("====================================================================");

        System.out.println("Табулированный синус");
        TabulatedFunction tSin = TabulatedFunctions.tabulate(sin, 0, 2*Math.PI, 10);
        for (double x = 0; x < 2 * Math.PI; x += 0.1)
            System.out.println("sin(" + x + ") = " + tSin.getFunctionValue(x));
        System.out.println("====================================================================");


        System.out.println("Табулированный косинус");
        TabulatedFunction tCos = TabulatedFunctions.tabulate(cos, 0, 2*Math.PI, 10);
        for (double x = 0; x< 2 * Math.PI; x += 0.1)
            System.out.println("cos(" + x + ") = " + tCos.getFunctionValue(x));
        System.out.println("====================================================================");


        System.out.println("Основное тригонометрическое по табулированным функциям");
        Function sum = Functions.sum(Functions.mult(tSin, tSin), Functions.mult(tCos, tCos));
        for (double x = 0; x < 2 * Math.PI; x += 0.1)
            System.out.println("sin^2(" + x + ") + cos^2(" + x + ") = " + sum.getFunctionValue(x));
        System.out.println("====================================================================");


        System.out.println("Запись табулированной экспоненты в файл");
        Exp exp = new Exp();
        TabulatedFunction tExp = TabulatedFunctions.tabulate(exp, 0, 10, 11);
        FileWriter out = new FileWriter("exp.txt");
        TabulatedFunctions.writeTabulatedFunction(tExp, out);
        out.flush();
        out.close();
        System.out.println("====================================================================");


        System.out.println("Обычная экспонента");
        for (int i = 0; i < 11; i++)
            System.out.println("x = " + i + " y =  " + exp.getFunctionValue(i));
        System.out.println("====================================================================");


        System.out.println("Табулированная экспонента из файла");
        FileReader in = new FileReader("exp.txt");
        TabulatedFunction tExp1 = TabulatedFunctions.readTabulatedFunction(in);
        for (int x = 0; x < 11; x++)
            System.out.println("x = " + x + " y =  " + tExp1.getFunctionValue(x));
        in.close();
        System.out.println("====================================================================");


        System.out.println("Запись табулированного логарифма в файл");
        Log ln = new Log(Math.E);
        TabulatedFunction tLn = TabulatedFunctions.tabulate(ln, 0.1, 10.1, 11);
        OutputStream output = new FileOutputStream("log.txt");
        TabulatedFunctions.outputTabulatedFunction(tLn, output);
        output.flush();
        output.close();
        System.out.println("====================================================================");


        System.out.println("Обычный логарифм");
        InputStream in_ln = new FileInputStream("log.txt");
        for (double i = 0.1; i < 11.1; i++)
            System.out.println("x = " + i + " y =  " + ln.getFunctionValue(i));
        System.out.println("====================================================================");


        System.out.println("Табулированный логарифм из файла");
        TabulatedFunction tLn1 = TabulatedFunctions.inputTabulatedFunction(in_ln);
        in_ln.close();
        for (double x = 0.1; x < 11.1; x++)
            System.out.println("x = " + x + " y =  " + tLn1.getFunctionValue(x));
        System.out.println("====================================================================");


        System.out.println("Композиция логарифма и экспоненты");
        Log ln2 = new Log(Math.E);
        Exp exp2 = new Exp();
        Function f = Functions.composition(ln2, exp2);
        TabulatedFunction tF = TabulatedFunctions.tabulate(f, 0, 10, 11);
        for (int x = 0; x < 11; x++)
            System.out.println("x = " + x + " y =  " + tF.getFunctionValue(x));
        System.out.println("====================================================================");


        System.out.println("Сериализация");
        FileOutputStream output2 = new FileOutputStream("ln_exp_composition.txt");
        ObjectOutputStream serial = new ObjectOutputStream(output2);
        serial.writeObject(tF);
        serial.close();
        System.out.println("====================================================================");


        System.out.println("Десериализация");
        FileInputStream input2 = new FileInputStream("ln_exp_composition.txt");
        ObjectInputStream deserial = new ObjectInputStream(input2);
        TabulatedFunction tF1 = (TabulatedFunction) deserial.readObject();
        deserial.close();
        for (int x = 0; x < 11; x++)
            System.out.println("x = " + x + " y =  " + tF1.getFunctionValue(x));
    }
}