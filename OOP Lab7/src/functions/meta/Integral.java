package functions.meta;

import functions.Function;
import functions.FunctionPointIndexOutOfBoundsException;

public class Integral
{
    public static double integral(Function func, double leftX, double rightX, double step) throws FunctionPointIndexOutOfBoundsException
    {
        if (leftX < func.getLeftDomainBorder() || rightX > func.getRightDomainBorder())
            throw new IllegalArgumentException();
        double res = 0;
        for (; step < rightX; step += step)
            res += func.getFunctionValue(step) * step;
        return res;
    }
}
