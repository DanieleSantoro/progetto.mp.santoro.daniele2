package airport;

import java.util.Objects;

public abstract class TurnaroundPlanDecorator implements TurnaroundPlan {

    private final TurnaroundPlan inner;

    public TurnaroundPlanDecorator(TurnaroundPlan inner) {
        this.inner = Objects.requireNonNull(inner);
    }

    protected TurnaroundPlan inner() {
        return inner;
    }

    @Override
    public TurnaroundTask rootTask() {
        return inner.rootTask();
    }

    @Override
    public SchedulingStrategy schedulingStrategy() {
        return inner.schedulingStrategy();
    }

    @Override
    public int schedulingMinutes() {
        return inner.schedulingMinutes();
    }

    @Override
    public String description() {
        return inner.description();
    }
}
