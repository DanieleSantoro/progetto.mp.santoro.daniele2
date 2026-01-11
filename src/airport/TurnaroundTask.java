package airport;

import java.util.Objects;

public abstract class TurnaroundTask {

    private final String name;

    public TurnaroundTask(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public abstract void accept(TurnaroundVisitor visitor);
}
