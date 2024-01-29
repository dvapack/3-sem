import java.lang.reflect.Method;

public class Main
{
    public static String[] staticMethod(Object o)
    {
        Method[] methods = o.getClass().getMethods();
        String[] result = new String[methods.length];
        for (int i = 0; i < methods.length; ++i)
            result[i] = methods[i].getName();
        return result;
    }

    public static void main(String[] args)
    {

    }
}