package Factory;

import Task.Container;

public interface Factory {
    Container createContainer(Strategy strategy);
}
