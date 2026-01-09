package airport;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import utils.Resource;

public class SimpleTask extends TurnaroundTask {

	private final int minutes;
	private final Set<Resource> requiredResources;
	
	public SimpleTask(String name, int minutes, Set<Resource> requiredResources) {
		super(Objects.requireNonNull(name));
		this.minutes = minutes;
		this.requiredResources = Collections.unmodifiableSet(new HashSet<>(requiredResources));
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public Set<Resource> getRequiredResources() {
		return requiredResources;
	}
	
	@Override
	public void accept(TurnaroundVisitor visitor) {
		visitor.visitSimpleTask(this);
	}
	
}
