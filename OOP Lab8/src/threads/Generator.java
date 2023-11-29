package threads;

import functions.basic.Log;

import java.util.Random;

public class Generator extends Thread
{
    Task newTask;
    Semaphore semaphore;
    boolean flag = true;

    public Generator(Task newTask, Semaphore semaphore)
    {
        this.newTask = newTask;
        this.semaphore = semaphore;
    }

    @Override
    public void run()
    {
        Random rnd = new Random();
        for (int i = 0; i < this.newTask.getAmountOfTasks() && flag; i++)
        {
            try
            {
                semaphore.beginWrite();
                newTask.func = new Log(rnd.nextInt(9) + 2);
                newTask.leftX = rnd.nextInt(100);
                newTask.rightX = rnd.nextInt(100) + 100;
                newTask.step = (double) 1 / (rnd.nextDouble(100000.) + 1.0);
                System.out.println(i + ": Source <" +  newTask.leftX + "> <" +  newTask.rightX + "> <" + newTask.step + ">");
                semaphore.endWrite();
            } catch (InterruptedException ex)
            {
                System.out.println("Генератор прервали во время сна, он корректно завершил свою работу");
            }
        }
    }

    public void interrupt()
    {
        super.interrupt();
        flag = false;
    }
}
