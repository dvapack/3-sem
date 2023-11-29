package functions;

import java.io.Serializable;
import java.util.Arrays;

public class ArrayTabulatedFunction  implements TabulatedFunction, Serializable
{
    private FunctionPoint arr[];
    private int pointsCount;

    public ArrayTabulatedFunction(FunctionPoint array[])
    {
        if (array.length < 2)
            throw new IllegalArgumentException("Error! PointsCount < 2");
        if (!(xSorted(array)))
            throw new IllegalArgumentException(("Error! X are not sorted!"));
        pointsCount = array.length;
        this.arr = array;
    }

    public ArrayTabulatedFunction(Function function, double leftX, double rightX, int pointsCount)
    {
        if (leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException("Error! leftX >= rightX || pointsCount < 2");
        double inter = Math.abs(rightX - leftX + 1) / pointsCount;
        this.pointsCount = pointsCount;
        arr = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; ++i)
            arr[i] = new FunctionPoint(leftX + i * inter, function.getFunctionValue(leftX + i * inter));
    }

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        if (leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException("Error! leftX >= rightX || pointsCount < 2");
        double inter = Math.abs(rightX - leftX + 1) / pointsCount;
        this.pointsCount = pointsCount;
        arr = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; ++i)
            arr[i] = new FunctionPoint(leftX + i * inter, 0);
    }


    public ArrayTabulatedFunction(double leftX, double rightX, double[] values)
    {
        if (leftX >= rightX || values.length < 2)
            throw new IllegalArgumentException("Error! leftX >= rightX || pointsCount < 2");
        double inter = (rightX - leftX + 1) / values.length;
        pointsCount = values.length;
        arr = new FunctionPoint[values.length];
        for (int i = 0; i < values.length; ++i)
            arr[i] = new FunctionPoint(leftX + i * inter, values[i]);
    }

    public String toString()
    {
        String str = "{";
        for (int i = 0; i < pointsCount; ++i)
        {
            str += "(" + arr[i].getX() + ";" + arr[i].getY() + ")";
            str += ", ";
        }
        return str += "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof TabulatedFunction))
        {
            return false;
        }
        if (o instanceof ArrayTabulatedFunction f)
        {
            if (f.getPointsCount() != this.getPointsCount())
                return false;
            return Arrays.equals(f.arr, this.arr);
        }
        if (o instanceof LinkedListTabulatedFunction lst)
        {
            if (lst.getPointsCount() != this.getPointsCount())
                return false;
            for (int i = 0; i < pointsCount; ++i)
                if (!(this.arr[i].equals(lst.getPoint(i))))
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int result = 0;
        for (int i = 0; i < pointsCount; ++i)
            result += 31 * Double.hashCode(arr[i].getX()) + Double.hashCode(arr[i].getY());
        return result += Integer.hashCode(pointsCount);
    }

    public Object clone()
    {
        FunctionPoint buf[] = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; ++i)
        {
            buf[i] = arr[i];
        }
        return new ArrayTabulatedFunction(buf);
    }

    private boolean xSorted(FunctionPoint array[])
    {
        for (int i = 0; i < array.length - 1; ++i)
        {
            if (array[i + 1].getX() < array[i].getX())
                return false;
        }
        return true;
    }

    public double getLeftDomainBorder()
    {
        return arr[0].getX();
    }


    public double getRightDomainBorder()
    {
        return arr[arr.length - 1].getX();
    }


    public double getFunctionValue(double x)
    {
        return (x >= arr[0].getX() && x <= arr[pointsCount - 1].getX()) ?
                (((arr[pointsCount - 1].getY() - arr[0].getY()) / (arr[pointsCount - 1].getX() - arr[0].getX())) * (x - arr[pointsCount - 1].getX()) + arr[pointsCount - 1].getY())
                : Double.NaN;
    }


    public int getPointsCount()
    {
        return pointsCount;
    }


    public FunctionPoint getPoint(int index)
    {
        if (index >= 0 && index < pointsCount)
            return arr[index];
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }


    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException, FunctionPointIndexOutOfBoundsException
    {
        if (index >= 0 && index < pointsCount)
        {
            if ((point.getX() >= arr[0].getX() && point.getX() <= arr[pointsCount - 1].getX()) || (point.getX() < arr[0].getX() && index == 0) ||
                    (point.getX() > arr[pointsCount - 1].getX() && index == pointsCount - 1))
                arr[index] = point;
            else
                throw new InappropriateFunctionPointException("Error! X is out of function range");
        } else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }


    public double getPointX(int index)
    {
        if (index >= 0 && index < pointsCount)
            return arr[index].getX();
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }


    public void setPointX(int index, double x) throws InappropriateFunctionPointException, FunctionPointIndexOutOfBoundsException
    {
        if (index >= 0 && index < pointsCount)
        {
            if ((x >= arr[0].getX() && x <= arr[pointsCount - 1].getX()) || (x < arr[0].getX() && index == 0) ||
                    (x > arr[pointsCount - 1].getX() && index == pointsCount - 1))
                arr[index].setX(x);
            else
                throw new InappropriateFunctionPointException("Error! X is out of function range");
        } else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");

    }


    public double getPointY(int index)
    {
        if (index >= 0 && index < pointsCount)
            return arr[index].getY();
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }


    public void setPointY(int index, double y)
    {
        if (index >= 0 && index < pointsCount)
        {
            if (index == 0 || index == pointsCount - 1)
            {
                arr[index].setY(y);
                return;
            }
            double x = (((arr[pointsCount - 1].getX() - arr[0].getX()) / (arr[pointsCount - 1].getY() - arr[0].getY())) * (y - arr[pointsCount - 1].getY()) + arr[pointsCount - 1].getX());
            if (x >= arr[0].getX() && x <= arr[pointsCount - 1].getX())
                arr[index].setY(y);
        } else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }


    public void deletePoint(int index)
    {
        if (pointsCount < 3)
            throw new IllegalStateException("pointsCount < 3");
        if (index >= 0 && index < pointsCount)
        {
            for (int i = index; i < pointsCount - 1; ++i)
                arr[i] = arr[i + 1];
            arr[pointsCount - 1] = null;
            --pointsCount;
        } else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");

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

            if (arr[mid].getX() == x) // already existing point
                return -1;

            if (arr[mid].getX() < x)
                low = mid + 1;
            else if (arr[mid].getX() > x)
                high = mid - 1;
            else
                return mid;
        }

        if ((arr[low].getX() == x) || (arr[high].getX() == x)) // already existing point
            return -1;

        if (arr[low].getX() > x)
            return low;

        if (arr[high].getX() < x)
            return high;

        return -1; // Not found
    }


    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException
    {
        FunctionPoint buff_arr[];
        if (arr.length == pointsCount)
            buff_arr = new FunctionPoint[arr.length * 2];
        else
            buff_arr = new FunctionPoint[arr.length];
        int index = interpolationSearch(point.getX());
        if (index == -1)
            throw new InappropriateFunctionPointException("Error! Point with this X already exists");
        System.arraycopy(arr, 0, buff_arr, 0, index);
        buff_arr[index] = point;
        System.arraycopy(arr, index, buff_arr, index + 1, pointsCount - index);
        arr = buff_arr;
        ++pointsCount;
    }
}
