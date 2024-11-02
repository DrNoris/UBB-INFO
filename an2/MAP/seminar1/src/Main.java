import Factory.*;
import Task.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MessageTask messageTask1 = new MessageTask("Salut!", "Dan", "Maria", LocalDateTime.of(2024, 10, 1, 6, 29), "1", "good job");
        MessageTask messageTask2 = new MessageTask("Bravo!", "Ion", "Ioana", LocalDateTime.of(2024, 10, 1, 7, 29), "2", "good job");
        MessageTask messageTask3 = new MessageTask("Cum mai esti?", "Ion", "Ana", LocalDateTime.of(2024, 10, 1, 8, 29), "3", "good job");
        MessageTask messageTask4 = new MessageTask("La revedere!", "Ion", "Ioana", LocalDateTime.of(2024, 10, 1, 9, 29), "4", "good job");
        MessageTask messageTask5 = new MessageTask("Salut!", "Dan", "Maria", LocalDateTime.of(2024, 10, 1, 10, 29), "5", "good job");
        ArrayList<MessageTask> listOfMesssages = new ArrayList<>();
        listOfMesssages.add(messageTask1);
        listOfMesssages.add(messageTask2);
        listOfMesssages.add(messageTask3);
        listOfMesssages.add(messageTask4);
        listOfMesssages.add(messageTask5);

        for (MessageTask message : listOfMesssages) {
            System.out.println(message.toString());
        }

        int[] numbers = {14, 20, 24, 3, 1, 70};
        SortingTask sort = new SortingTask(new BubbleSort(), numbers);
        sort.execute();


        Factory factory = TaskContainerFactory.getInstance();

        Container stack = factory.createContainer(Strategy.LIFO);
        stack.add(messageTask1);
        stack.add(messageTask2);
        stack.add(messageTask3);

        System.out.println(stack.remove());
        System.out.println(stack.remove());
        System.out.println(stack.remove());
        System.out.println();

        Container q = factory.createContainer(Strategy.FIFO);
        q.add(messageTask1);
        q.add(messageTask2);
        q.add(messageTask3);

        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println();

        ArrayList<MessageTask> messageTasks = new ArrayList<>();
        messageTasks.add(messageTask1);
        messageTasks.add(messageTask2);
        messageTasks.add(messageTask3);
        messageTasks.add(messageTask4);
        messageTasks.add(messageTask5);

        //pct 13
        Scanner scanner = new Scanner(System.in);  // Scanner to read from console
        System.out.println("Enter strategy (FIFO or LIFO):");

        String input = scanner.nextLine().toUpperCase();  // Read user input and convert to uppercase
        Strategy strategy = Strategy.valueOf(input);


        StrategyTaskRunner runner = new StrategyTaskRunner(strategy);
        for (MessageTask mess : messageTasks){
            runner.addTask(mess);
        }
        runner.executeAll();
        System.out.println();
        System.out.println();
        System.out.println();

        for (MessageTask mess : messageTasks){
            runner.addTask(mess);
        }

        PrinterTaskRunner runner1 = new PrinterTaskRunner(runner);
        runner1.executeAll();
        System.out.println();
        System.out.println();
        System.out.println();


        for (MessageTask mess : messageTasks){
            runner.addTask(mess);
        }

        DelayTaskRunner runner2 = new DelayTaskRunner(runner);
        runner2.executeAll();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}