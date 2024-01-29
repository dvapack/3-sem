import java.util.Scanner;

public class Main
{
    public static class NumberHolder
    {
        private static NumberHolder instance;
        private double value;

        private NumberHolder()
        {
            // Приватный конструктор, чтобы предотвратить создание экземпляров класса извне
            value = 0.0;
        }

        public static NumberHolder getInstance()
        {
            if (instance == null)
                instance = new NumberHolder();
            return instance;
        }

        public void add(double num)
        {
            value += num;
        }

        public void multiply(double num)
        {
            value *= num;
        }

        public void setValue(double num)
        {
            value = num;
        }

        public double getValue()
        {
            return value;
        }
    }

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Input real number: ");
        double value = in.nextDouble();
        NumberHolder numb = NumberHolder.getInstance();

        numb.setValue(value);
        numb.multiply(2);
        System.out.println("Multiplied by 2: " + numb.getValue());
        numb.add(2);
        System.out.println("Added 2: " + numb.getValue());

    }
}