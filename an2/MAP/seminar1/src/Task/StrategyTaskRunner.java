package Task;

import Factory.Strategy;
import Factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner{
    private Container container;

    public StrategyTaskRunner(Strategy strategy){
        TaskContainerFactory factory = TaskContainerFactory.getInstance();
        container = factory.createContainer(strategy);
    }


    @Override
    public void executeOneTask() {
        if (hasTask()) {
            Task removedTask = container.remove();
            removedTask.execute();
        }
        else {
            System.out.println("There are no tasks!");
        }
    }

    @Override
    public void executeAll() {
        while (hasTask())
            executeOneTask();
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }
}
