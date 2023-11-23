package functions.meta;

import functions.Function;

public class Power implements Function
{
    private Function f1;
    private double stepen;

    public Power(Function ishod, double stepen)
    {
        this.f1 = ishod;
        this.stepen = stepen;
    }

    public Function getF1()
    {
        return f1;
    }

    public double getF2()
    {
        return stepen;
    }

    public double getLeftDomainBorder()
    {
        return f1.getLeftDomainBorder();
    }

    public double getRightDomainBorder()
    {
        return f1.getRightDomainBorder();
    }

    public double getFunctionValue(double x)
    {
        return Math.pow(f1.getFunctionValue(x), stepen);
    }
}
