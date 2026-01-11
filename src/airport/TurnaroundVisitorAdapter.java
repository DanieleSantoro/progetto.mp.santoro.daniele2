package airport;

public abstract class TurnaroundVisitorAdapter implements TurnaroundVisitor {

    @Override
    public void visitSimpleTask(SimpleTask task) {
        // default: do nothing
    }

    @Override
    public void visitTaskGroup(TaskGroup group) {
        for (TurnaroundTask t : group.getChildren()) {
            t.accept(this);
        }
    }
}
