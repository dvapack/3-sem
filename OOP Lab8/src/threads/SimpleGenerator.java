package threads;

import functions.basic.Log;

import java.util.Random;

public class SimpleGenerator implements Runnable
{
    private final Task newTask;

    public SimpleGenerator(Task newTask)
    {
        this.newTask = newTask;
    }

    @Override
    public void run()
    {
        synchronized (newTask)
        {
            //Task newTask = new Task(100);
            Random rnd = new Random();
            for (int i = 0; i < newTask.getAmountOfTasks(); ++i)
            {
                newTask.func = new Log(rnd.nextInt(9) + 2);
                newTask.leftX = rnd.nextInt(100);
                newTask.rightX = rnd.nextInt(100) + 100;
                newTask.step = (double) 1 / (rnd.nextDouble(100000.) + 1.0);
                System.out.println(i + ": Source <" +  newTask.leftX + "> <" +  newTask.rightX + "> <" + newTask.step + ">");
            }
        }
    }
}
