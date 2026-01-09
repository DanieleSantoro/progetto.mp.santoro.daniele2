package airport;

public final class ParallelSchedulingStrategy implements SchedulingStrategy {

    @Override
    public int scheduleMinutes(TurnaroundTask root) {
        ParallelDurationVisitor v = new ParallelDurationVisitor();
        root.accept(v);
        return v.result();
    }

    /**
     * Visitor che calcola la durata della sotto-struttura visitata:
     * - SimpleTask => minuti
     * - TaskGroup  => max(durata figli), ricorsivo
     */
    private static final class ParallelDurationVisitor implements TurnaroundVisitor {

        private int result = 0;

        int result() {
            return result;
        }

        @Override
        public void visitSimpleTask(SimpleTask task) {
            result = task.getMinutes();
        }

        @Override
        public void visitTaskGroup(TaskGroup group) {
            int max = 0;

            for (TurnaroundTask child : group.getChildren()) {
                child.accept(this);              // dopo questa chiamata, result = durata del child
                max = Math.max(max, result);     // accumulo il massimo
            }

            result = max; // durata del gruppo
        }
    }
}
