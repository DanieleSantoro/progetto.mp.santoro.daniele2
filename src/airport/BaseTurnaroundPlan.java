package airport;

import java.util.Objects;

public final class BaseTurnaroundPlan implements TurnaroundPlan {

    private final TurnaroundTask root;
    private final SchedulingStrategy strategy;
    private final String description;

    public BaseTurnaroundPlan(TurnaroundTask root, SchedulingStrategy strategy, String description) {
        this.root = Objects.requireNonNull(root);
        this.strategy = Objects.requireNonNull(strategy);
        this.description = Objects.requireNonNull(description);
    }

    @Override
    public TurnaroundTask rootTask() {
        return root;
    }

    @Override
    public SchedulingStrategy schedulingStrategy() {
        return strategy;
    }

    @Override
    public int schedulingMinutes() {
        return strategy.scheduleMinutes(root);
    }

    @Override
    public String description() {
        return description;
    }
}
