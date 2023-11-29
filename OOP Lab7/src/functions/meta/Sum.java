package functions.meta;

import functions.Function;
import functions.FunctionPoint;


public class Sum implements Function
{
    private Function f1;
    private Function f2;

    public Sum(Function f1, Function f2)
    {
        this.f1 = f1;
        this.f2 = f2;
    }

    public Function getF1()
    {
        return f1;
    }

    public Function getF2()
    {
        return f2;
    }

    @Override
    public double getLeftDomainBorder()
    {
        return Math.min(f1.getLeftDomainBorder(), f2.getLeftDomainBorder());
    }

    @Override
    public double getRightDomainBorder()
    {
        return Math.min(f2.getRightDomainBorder(), f2.getRightDomainBorder());
    }

    @Override
    public double getFunctionValue(double x)
    {
        return (f1.getFunctionValue(x) + f2.getFunctionValue(x));
    }
}
