package utils;

import airport.SchedulingStrategy;
import airport.TurnaroundTask;

public final class SequentialSchedulingStrategy implements SchedulingStrategy {

	@Override
	public int scheduleMinutes(TurnaroundTask root) {
		TotalDurationVisitor v = new TotalDurationVisitor();
		root.accept(v);
		return v.getTotalMinutes();
	}

	
	
}
