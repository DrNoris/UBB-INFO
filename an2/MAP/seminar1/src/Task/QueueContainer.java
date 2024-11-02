package Task;

import java.util.ArrayList;

public class QueueContainer extends ContainerSuperClass{
    public QueueContainer(){
        super();
    }

    @Override
    public Task remove() {
        if (tasks.isEmpty()) {
            return null;
        }
        else{
            return tasks.removeFirst();
        }
    }
}
