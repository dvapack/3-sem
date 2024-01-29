import java.lang.Number;

public class Main
{
    class Generic<T extends Number>
    {
        T[] nums;

        Generic(T[] nums)
        {
            this.nums = nums;
        }

        double min()
        {
            double result = nums[0].doubleValue();
            for (int i = 0; i < nums.length; ++i)
                if (result > nums[i].doubleValue()) result = nums[i].doubleValue();
            return result;
        }

        double max()
        {
            double result = nums[0].doubleValue();
            for (int i = 0; i < nums.length; ++i)
                if (result < nums[i].doubleValue()) result = nums[i].doubleValue();
            return result;
        }
    }


    public static void main(String[] args)
    {

    }


}