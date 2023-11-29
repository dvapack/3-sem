package threads;

import functions.Function;

public class Task
{
    public Function func;
    public double leftX;
    public double rightX;
    public double step;
    private int amountOfTasks;

    public Task(int amountOfTasks)
    {
        this.amountOfTasks = amountOfTasks;
    }

    public int getAmountOfTasks()
    {
        return amountOfTasks;
    }

    public void setFunction(double leftX, double rightX, double step)
    {

    }
}
