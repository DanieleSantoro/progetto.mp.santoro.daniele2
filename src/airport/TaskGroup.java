package airport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class TaskGroup extends TurnaroundTask {

	private final List<TurnaroundTask> children = new ArrayList<>();

	public TaskGroup(String name) {
		super(Objects.requireNonNull(name));
	}

	public void add(TurnaroundTask task) {
		children.add(Objects.requireNonNull(task));
	}

	public void remove(TurnaroundTask task) {
		children.remove(task);
	}

	public List<TurnaroundTask> getChildren() {
		return Collections.unmodifiableList(children);
	}

	@Override
	public void accept(TurnaroundVisitor visitor) {
		visitor.visitTaskGroup(this);
	}
}
