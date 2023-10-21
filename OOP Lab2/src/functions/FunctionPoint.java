package functions;
//Task 2
public class FunctionPoint
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
