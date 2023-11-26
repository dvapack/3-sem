package functions;

import java.io.*;

public class TabulatedFunctions
{
    private TabulatedFunctions(){};

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount)
    {
        if (leftX < function.getLeftDomainBorder() || rightX > function.getRightDomainBorder())
            throw new IllegalArgumentException("указанные границы для табулирования выходят за область определения функции");
        return new ArrayTabulatedFunction(function, leftX, rightX, pointsCount);
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
        for (int i = 0; i < function.getPointsCount(); i++) {
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
        for (int i = 0; i < amount; i++) {
            In.nextToken();
            double x = In.nval;
            In.nextToken();
            double y = In.nval;
            points[i] = new FunctionPoint(x, y);
        }
        return new ArrayTabulatedFunction(points);
    }


}
