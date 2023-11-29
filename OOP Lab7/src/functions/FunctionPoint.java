package functions;

import java.io.Serializable;
//Task 2
public class FunctionPoint implements Serializable
{
    private double X;
    private double Y;

    public FunctionPoint()
    {
        X = 0;
        Y = 0;
    }

    public FunctionPoint(double x, double y)
    {
        this.X = x;
        this.Y = y;
    }

    public FunctionPoint(FunctionPoint point)
    {
        this.X = point.getX();
        this.Y = point.getY();
    }

    public String toString()
    {
        return "(" + X + ";" + Y + ")";
    }

    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        FunctionPoint obj = (FunctionPoint) o;
        return this.X == obj.X && this.Y == obj.Y;
    }

    public int hashCode()
    {
        return 31 * Double.hashCode(X) + Double.hashCode(Y); // в интернете сказали 31 значит 31...
    }

    public Object clone()
    {
        return new FunctionPoint(this);
    }

    public double getX()
    {
        return X;
    }

    public double getY()
    {
        return Y;
    }

    public void setX(double x)
    {
        this.X = x;
    }

    public void setY(double y)
    {
        this.Y = y;
    }
}
