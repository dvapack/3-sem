package functions.meta;

import functions.Function;

public class Scale implements Function
{

    private Function f;
    private double scaleX;
    private double scaleY;

    public Scale(Function ishod, double scaleX, double scaleY)
    {
        this.f = ishod;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public double getLeftDomainBorder()
    {
        return f.getLeftDomainBorder()*scaleX;
    }

    public double getRightDomainBorder()
    {
        return f.getRightDomainBorder()*scaleX;
    }

    public double getFunctionValue(double x)
    {
        return f.getFunctionValue(x)*scaleY;
    }

    public double getScaleX()
    {
        return scaleX;
    }

    public Function getF()
    {
        return f;
    }

    public double getScaleY()
    {
        return scaleY;
    }
}
