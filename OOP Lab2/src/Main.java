import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main
{
    public static void main(String[] args)
    {
        // y = 3x
        //Task 7
        double[] values = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
        TabulatedFunction f = new TabulatedFunction(1, 10, values);
        System.out.println("====================================================================");
        System.out.println("! Проверка конструктора !");
        for (int i = 0; i < f.getPointsCount(); ++i)
            System.out.print(f.getPoint(i).getY() + " ");

        System.out.println('\n' + "====================================================================");
        System.out.println("! Проверка получения значений функции при х от 0 до 12 !");
        for (double i = 0; i <= f.getPointsCount() + 1; i+=0.5)
            System.out.print(f.getFunctionValue(i) + " ");

        System.out.println('\n' + "====================================================================");
        System.out.println("! Проверка добавления точек !");
        System.out.println("Сейчас в массиве [" + f.getPointsCount() + "] точек");
        for (int i = 0; i < f.getPointsCount(); ++i)
            System.out.print(f.getPoint(i).getY() + " ");
        f.addPoint(new FunctionPoint(1.5, 4.5));
        f.addPoint(new FunctionPoint(2.5, 7.5));
        f.addPoint(new FunctionPoint(3.5, 10.5));
        f.addPoint(new FunctionPoint(4.5, 13.5));
        f.addPoint(new FunctionPoint(6.5, 19.5));
        f.addPoint(new FunctionPoint(7.5, 22.5));
        for (int i = 0; i < f.getPointsCount(); ++i)
            System.out.print(f.getPoint(i).getY() + " ");
        System.out.println();
        System.out.println("Сейчас в массиве [" + f.getPointsCount() + "] точек");

        System.out.println("====================================================================");
        System.out.println("! Проверка удаления точки !");
        System.out.println("Сейчас в массиве [" + f.getPointsCount() + "] точек");
        f.deletePoint(5);
        System.out.println("Сейчас в массиве [" + f.getPointsCount() + "] точек");
        System.out.println(f.getFunctionValue(0));
        System.out.println("====================================================================");
    }
}