import functions.Functions;
import functions.basic.Log;
import threads.*;

import java.io.IOException;
import java.util.Random;

public class Main
{
    public static void nonThread()
    {
        Task newTask = new Task(100);
        Random rnd = new Random();
        for (int i = 0; i < newTask.getAmountOfTasks(); ++i)
        {
            Log log = new Log(rnd.nextInt(9) + 2);
            int leftX = rnd.nextInt(100);
            int rightX = rnd.nextInt(100) + 100;
            double step = (double) 1 / (rnd.nextDouble(100000.) + 1.0);
            System.out.println(i + ": Source <" + leftX + "> <" + rightX + "> <" + step + ">");
            System.out.println("Result <" + leftX + "> <" + rightX + "> <" + step + "> <" + Functions.integral(log, leftX, rightX, step) + ">");
        }
    }

    public static void simpleThreads() throws InterruptedException
    {
        Task newTask = new Task(100);
        Thread generatorThread = new Thread(new SimpleGenerator(newTask));
        Thread integratorThread = new Thread(new SimpleIntegrator(newTask));
        generatorThread.start();
        integratorThread.start();
        Thread.sleep(50);
    }

    public static void complicatedThreads() throws InterruptedException
    {
        Task newTask = new Task(100);
        Semaphore semaphore = new Semaphore();
        Thread generator = new Generator(newTask, semaphore);
        generator.start();
        Thread integrator = new Integrator(newTask, semaphore);
        System.out.println("Generator priority = " + generator.getPriority());
        System.out.println("Integrator priority = " + integrator.getPriority());
        integrator.start();
        Thread.sleep(50);
        generator.interrupt();
        integrator.interrupt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        System.out.println("nonThread");
        nonThread();
        System.out.println("\n-------------------------------------------");
        System.out.println("simpleThreads");
        simpleThreads();
        System.out.println("\n-------------------------------------------");
        System.out.println("complicatedThreads");
        complicatedThreads();
    }
}