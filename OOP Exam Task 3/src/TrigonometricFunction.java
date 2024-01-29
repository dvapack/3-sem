abstract public class TrigonometricFunction implements Function
{
    public double getLeftBorder()
    {
        return Double.NEGATIVE_INFINITY;
    }

    public double getRightBorder()
    {
        return Double.POSITIVE_INFINITY;
    }

    public double getValue(double x)
    {
        return 0.;
    }
}
