package utils;

import airport.SimpleTask;
import airport.TaskGroup;
import airport.TurnaroundTask;
import airport.TurnaroundVisitorAdapter;

public class ParallelDurationVisitor extends TurnaroundVisitorAdapter{

	private int result = 0;
	
	@Override
	public void visitSimpleTask(SimpleTask task) {
			result = task.getMinutes();
	}
	
	@Override
	public void visitTaskGroup(TaskGroup group) {
		int maxInGroup = 0;
		
		for (TurnaroundTask child : group.getChildren()) {
			child.accept(this);			
			maxInGroup = Math.max(maxInGroup, result);
		}
	
		result = maxInGroup;
	}
	
	public int getResult() {
		return result;
	}
	
}
