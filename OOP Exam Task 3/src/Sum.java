public class Sum implements Function
{
    private Function first;
    private Function second;

    public Sum(Function first, Function second)
    {
        this.first = first;
        this.second = second;
    }

    public double getLeftBorder()
    {
        return first.getLeftBorder() + second.getLeftBorder();
    }

    public double getRightBorder()
    {
        return first.getRightBorder() + second.getRightBorder();
    }

    public double getValue(double x)
    {
        return first.getValue(x) + second.getValue(x);
    }
}
