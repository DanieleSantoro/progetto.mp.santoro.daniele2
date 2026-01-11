package airport;

public interface TurnaroundVisitor {
    void visitSimpleTask(SimpleTask task);
    void visitTaskGroup(TaskGroup group);
}
