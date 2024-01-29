import java.io.Serializable;
import java.util.Iterator;

public class Vector implements Serializable, Iterable<Double>
{
    private double[] arr;

    public Vector()
    {
        arr = new double[0];
    }

    public Vector(int size)
    {
        arr = new double[size];
    }

    public double getElement(int i)
    {
        return arr[i];
    }

    public void setElement(double num, int i)
    {
        arr[i] = num;
    }

    public int getLength()
    {
        return arr.length;
    }

    public Iterator<Double> iterator()
    {
        return new Iterator<Double>()
        {
            private int currentIndex = 0;

            @Override
            public boolean hasNext()
            {
                return currentIndex < arr.length;
            }

            @Override
            public Double next()
            {
                return arr[currentIndex++];
            }
        };
    }
}
