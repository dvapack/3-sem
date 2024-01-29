import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        char symbolToFind;
        String fileName;
        int count = 0;
        Scanner in = new Scanner(System.in);
        symbolToFind = in.next().toCharArray()[0];
        fileName = in.next();
        in.close();
        //
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = input.readLine()) != null)
            {
                char[] charArray = line.toCharArray();
                for (int i = 0; i < charArray.length; ++i)
                    if (charArray[i] == symbolToFind)
                        ++count;
            }
            input.close();
            System.out.println(count);
        } catch (IOException e)
        {
            System.out.println("Такого файла не существует или произошла ошибка чтения");
        }
    }
}