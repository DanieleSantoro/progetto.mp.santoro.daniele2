package airport;

import utils.ParallelDurationVisitor;

public final class ParallelSchedulingStrategy implements SchedulingStrategy {

    @Override
    public int scheduleMinutes(TurnaroundTask root) {
        ParallelDurationVisitor v = new ParallelDurationVisitor();
        root.accept(v);
        return v.getResult();
    }
}
