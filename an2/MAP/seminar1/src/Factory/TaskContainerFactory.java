package Factory;

import Task.Container;
import Task.QueueContainer;
import Task.StackContainer;

public class TaskContainerFactory implements Factory{
    private static TaskContainerFactory instance = new TaskContainerFactory();

    private TaskContainerFactory() {};

    public static TaskContainerFactory getInstance(){
        return instance;
    }

    @Override
    public Container createContainer(Strategy strategy) {
        Container rez = null;

        if (strategy.equals(Strategy.FIFO))
            rez = new QueueContainer();
        else
            rez = new StackContainer();

        return rez;
    }
}
