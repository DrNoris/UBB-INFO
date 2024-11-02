package Task;

import java.util.ArrayList;

public abstract class ContainerSuperClass implements Container{
    protected ArrayList<Task> tasks;

    public ContainerSuperClass(){
        this.tasks = new ArrayList<Task>();
    }

    abstract public Task remove();

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public void add(Task task) {
        tasks.add(task);
    }
}
