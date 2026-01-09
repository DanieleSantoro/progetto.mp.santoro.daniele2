package airport;

public class ParallelDurationVisitor extends TurnaroundVisitorAdapter{

	private int currentDepth = 0;
	private int result = 0;
	
	@Override
	public void visitSimpleTask(SimpleTask task) {
		if (currentDepth == 0) {
			result = task.getMinutes();
		}
	}
	
	@Override
	public void visitTaskGroup(TaskGroup group) {
		int maxInGroup = 0;
		
		for (TurnaroundTask child : group.getChildren()) {
			currentDepth++;
			child.accept(this);
			currentDepth--;
			
			maxInGroup = Math.max(maxInGroup, result);
		}
	
		result = maxInGroup;
	}
	
	public int getResult() {
		return result;
	}
	
}
