package functions.basic;

import functions.Function;

public class Log implements Function
{
    private double osnova;

    public Log(double osnova)
    {
        if (osnova != 1 && osnova > 0)
            this.osnova = osnova;
        else
            throw new IllegalArgumentException("Error! Основание > 0 && != 1 ");
    }

    public double getLeftDomainBorder()
    {
        return 0.0;
    }

    public double getRightDomainBorder()
    {
        return Double.POSITIVE_INFINITY;
    }

    public double getFunctionValue(double x)
    {
        if (x > 0)
            return Math.log(x) / Math.log(osnova);
        else
            throw new IllegalArgumentException("Error! x > 0");
    }
}
