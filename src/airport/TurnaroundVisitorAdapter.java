package airport;

public abstract class TurnaroundVisitorAdapter implements TurnaroundVisitor {

	@Override
	public void visitSimpleTask(SimpleTask task) {
		// do nothing by default
	}
	
	@Override
	public void visitTaskGroup(TaskGroup group) {
		for (TurnaroundTask t : group.getChildren()) {
			t.accept(this);
		}
	}

}
