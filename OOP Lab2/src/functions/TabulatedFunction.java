package functions;

public class TabulatedFunction
{
    private FunctionPoint arr[];
    private int pointsCount;

    //Task 3
    public TabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        double inter = Math.abs(rightX - leftX + 1) / pointsCount;
        this.pointsCount = pointsCount;
        arr = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; ++i)
            arr[i] = new FunctionPoint(leftX + i * inter, 0);
    }

    //Task 3
    public TabulatedFunction(double leftX, double rightX, double[] values)
    {
        double inter = (rightX - leftX + 1) / values.length;
        pointsCount = values.length;
        arr = new FunctionPoint[values.length];
        for (int i = 0; i < values.length; ++i)
            arr[i] = new FunctionPoint(leftX + i * inter, values[i]);
    }

    //Task 4
    public double getLeftDomainBorder()
    {
        return arr[0].getX();
    }

    //Task 4
    public double getRightDomainBorder()
    {
        return arr[arr.length - 1].getX();
    }

    //Task 4
    public double getFunctionValue(double x)
    {
        return (x >= arr[0].getX() && x <= arr[pointsCount - 1].getX()) ?
                (((arr[pointsCount - 1].getY() - arr[0].getY()) / (arr[pointsCount - 1].getX() - arr[0].getX())) * (x - arr[pointsCount - 1].getX()) + arr[pointsCount - 1].getY())
                : Double.NaN;
    }

    //Task 5
    public int getPointsCount()
    {
        return pointsCount;
    }

    //Task 5
    public FunctionPoint getPoint(int index)
    {
        return (index >= 0 && index < pointsCount) ? arr[index] : arr[0];
    }

    //Task 5
    public void setPoint(int index, FunctionPoint point)
    {
        if ((point.getX() >= arr[0].getX() && point.getX() <= arr[pointsCount - 1].getX()) || (point.getX() < arr[0].getX() && index == 0) ||
                (point.getX() > arr[pointsCount - 1].getX() && index == pointsCount - 1))
            arr[index] = point;
    }

    //Task 5
    public double getPointX(int index)
    {
        return arr[index].getX();
    }

    //Task 5
    public void setPointX(int index, double x)
    {
        if ((x >= arr[0].getX() && x <= arr[pointsCount - 1].getX()) || (x < arr[0].getX() && index == 0) ||
                (x > arr[pointsCount - 1].getX() && index == pointsCount - 1))
            arr[index].setX(x);
    }

    //Task 5
    public double getPointY(int index)
    {
        return arr[index].getY();
    }

    //Task 5
    public void setPointY(int index, double y)
    {
        if (index == 0 || index == pointsCount - 1)
            arr[index].setY(y);
        else
        {
            double x = (((arr[pointsCount - 1].getX() - arr[0].getX()) / (arr[pointsCount - 1].getY() - arr[0].getY())) * (y - arr[pointsCount - 1].getY()) + arr[pointsCount - 1].getX());
            if (x >= arr[0].getX() && x <= arr[pointsCount - 1].getX())
                arr[index].setY(y);
        }
    }

    //Task 6
    public void deletePoint(int index)
    {
        for (int i = index; i < pointsCount - 1; ++i)
            arr[i] = arr[i + 1];
        --pointsCount;
    }

    //For addPoint method
    private int interpolationSearch(double x)
    {
        if (arr[0].getX() > x)
            return 0;
        if (arr[pointsCount - 1].getX() <= x)
            return pointsCount - 1;
        int mid;
        int low = 0;
        int high = pointsCount - 1;
        while (arr[low].getX() < x && arr[high].getX() > x)
        {
            if (arr[high] == arr[low]) // Защита от деления на 0
                break;
            mid = (int) (low + ((x - arr[low].getX()) * (high - low)) / (arr[high].getX() - arr[low].getX()));

            if (arr[mid].getX() < x)
                low = mid + 1;
            else if (arr[mid].getX() > x)
                high = mid - 1;
            else
                return mid;
        }

        if (arr[low].getX() >= x)
            return low;

        if (arr[high].getX() <= x)
            return high;

        return 0; // Not found
    }

    //Task 6
    public void addPoint (FunctionPoint point)
    {
        FunctionPoint buff_arr[];
        if (arr.length == pointsCount)
            buff_arr = new FunctionPoint[arr.length*2];
        else
            buff_arr = new FunctionPoint[arr.length];
        int index = interpolationSearch(point.getX());
        System.arraycopy(arr, 0, buff_arr, 0, index);
        buff_arr[index] = point;
        System.arraycopy(arr, index, buff_arr, index + 1, pointsCount - index);
        arr = buff_arr;
        ++pointsCount;
    }
}
