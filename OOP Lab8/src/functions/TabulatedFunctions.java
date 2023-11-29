package functions;

import java.io.*;

public class TabulatedFunctions
{
    private static TabulatedFunctionFactory factory = new ArrayTabulatedFunction.ArrayTabulatedFunctionFactory();

    private TabulatedFunctions() {}

    public static void setTabulatedFunctionFactory(TabulatedFunctionFactory factory)
    {
        TabulatedFunctions.factory = factory;
    }

    public static TabulatedFunction createTabulatedFunction(double leftX, double rightX, int pointCount)
    {
        try
        {
            return factory.createTabulatedFunction(leftX, rightX, pointCount);
        } catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    public static TabulatedFunction createTabulatedFunction(double leftX, double rightX, double[] values)
    {
        try
        {
            return factory.createTabulatedFunction(leftX, rightX, values);
        } catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    public static TabulatedFunction createTabulatedFunction(FunctionPoint[] points)
    {
        try
        {
            return factory.createTabulatedFunction(points);
        } catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount)
    {
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder())
            throw new IllegalArgumentException("указанные границы для табулирования выходят за область определения функции");
        return createTabulatedFunction(leftX, rightX, pointsCount);
    }

    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException
    {
        DataOutputStream Out = new DataOutputStream(out);
        Out.writeInt(function.getPointsCount());
        for (int i = 0; i < function.getPointsCount(); i++)
        {
            Out.writeDouble(function.getPointX(i));
            Out.writeDouble(function.getPointY(i));
        }
        Out.flush();
    }


    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException
    {
        DataInputStream In = new DataInputStream(in);
        int amount = In.readInt();
        FunctionPoint[] points = new FunctionPoint[amount];
        for (int i = 0; i < amount; i++)
        {
            points[i] = new FunctionPoint(In.readDouble(), In.readDouble());
        }
        return new ArrayTabulatedFunction(points);
    }

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException
    {
        PrintWriter Out = new PrintWriter(out);
        Out.println(function.getPointsCount());
        for (int i = 0; i < function.getPointsCount(); i++)
        {
            Out.println(function.getPointX(i) + " " + function.getPointY(i));
        }
        Out.flush();
    }

    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException
    {
        StreamTokenizer In = new StreamTokenizer(in);
        In.nextToken();
        int amount = (int) In.nval;
        FunctionPoint[] points = new FunctionPoint[amount];
        for (int i = 0; i < amount; i++)
        {
            In.nextToken();
            double x = In.nval;
            In.nextToken();
            double y = In.nval;
            points[i] = new FunctionPoint(x, y);
        }
        return new ArrayTabulatedFunction(points);
    }


}
