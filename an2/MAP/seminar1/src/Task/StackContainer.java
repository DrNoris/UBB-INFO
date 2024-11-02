package Task;

import java.util.ArrayList;

public class StackContainer extends ContainerSuperClass{
    public StackContainer(){
        super();
    }

    @Override
    public Task remove() {
        if(isEmpty())
            return null;
        else {
            return tasks.remove(size() - 1);
        }
    }
}
