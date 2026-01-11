package utils;

import airport.SimpleTask;
import airport.TurnaroundVisitorAdapter;

public final class TotalDurationVisitor extends TurnaroundVisitorAdapter {

	private int totalMinutes = 0;
	
	@Override
	public void visitSimpleTask(SimpleTask task) {
		totalMinutes += task.getMinutes();
	}
	
	public int getTotalMinutes() {
		return totalMinutes;
	}

	
	
}
