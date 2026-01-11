package utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import airport.SimpleTask;
import airport.TurnaroundVisitorAdapter;

public final class RequiredResourcesVisitor extends TurnaroundVisitorAdapter {

	private final Set<Resource> resources = new HashSet<>();

	@Override
	public void visitSimpleTask(SimpleTask task) {
		resources.addAll(task.getRequiredResources());
	}
	
	public Set<Resource> getResources() {
		return Collections.unmodifiableSet(resources);
	}
	
}
