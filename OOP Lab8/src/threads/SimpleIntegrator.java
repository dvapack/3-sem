package threads;

import functions.Functions;

public class SimpleIntegrator implements Runnable
{
    private final Task task;

    public SimpleIntegrator(Task task)
    {
        this.task = task;
    }

    @Override
    public void run()
    {
        synchronized (task)
        {
            for (int i = 0; i < task.getAmountOfTasks(); ++i)
                if (this.task.func != null)
                    System.out.println("Result <" + task.leftX + "> <" + task.rightX + "> <" + task.step + "> <" + Functions.integral(task.func, task.leftX, task.rightX, task.step) + ">");
        }
    }
}
