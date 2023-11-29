package threads;

import functions.Functions;

public class Integrator extends Thread
{
    Task task;
    Semaphore semaphore;
    boolean flag = true;

    public Integrator(Task task, Semaphore semaphore)
    {
        this.task = task;
        this.semaphore = semaphore;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < this.task.getAmountOfTasks() && flag; i++)
        {
            if (this.task.func == null)
            {
                continue;
            }
            try
            {
                semaphore.beginRead();
                System.out.println("Result <" + task.leftX + "> <" + task.rightX + "> <" + task.step + "> <" + Functions.integral(task.func, task.leftX, task.rightX, task.step) + ">");
                semaphore.endRead();
            } catch (Exception ex)
            {
                System.out.println("Интегратор прервали и он корректно завершил свою работу");
            }
        }
    }

    public void interrupt()
    {
        super.interrupt();
        flag = false;
    }
}
