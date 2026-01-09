package airport;

import org.junit.Test;
import utils.BasicResource;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class ParallelSchedulingStrategyTest {

	@Test
	public void parallelStrategyReturnsMaxOfChildrenInGroup() {
		TaskGroup group = new TaskGroup("AnyName");
		group.add(new SimpleTask("Fast", 10, Set.of(new BasicResource("X"))));
		group.add(new SimpleTask("Slow", 25, Set.of(new BasicResource("Y"))));
		group.add(new SimpleTask("Medium", 15, Set.of(new BasicResource("Z"))));

		SchedulingStrategy s = new ParallelSchedulingStrategy();

		assertThat(s.scheduleMinutes(group)).isEqualTo(25);
	}

	@Test
	public void parallelStrategyIsRecursiveOnNestedGroups() {
		TaskGroup root = new TaskGroup("Root");

		TaskGroup nested = new TaskGroup("Nested");
		nested.add(new SimpleTask("Deep1", 30, Set.of(new BasicResource("A"))));
		nested.add(new SimpleTask("Deep2", 12, Set.of(new BasicResource("B"))));

		root.add(nested);
		root.add(new SimpleTask("TopLevel", 20, Set.of(new BasicResource("C"))));

		SchedulingStrategy s = new ParallelSchedulingStrategy();

		assertThat(s.scheduleMinutes(root)).isEqualTo(30);
	}

	@Test
	public void parallelStrategyOnSingleTaskReturnsThatTaskMinutes() {
		TurnaroundTask t = new SimpleTask("Solo", 17, Set.of(new BasicResource("R")));

		SchedulingStrategy s = new ParallelSchedulingStrategy();

		assertThat(s.scheduleMinutes(t)).isEqualTo(17);
	}

	@Test
	public void parallelStrategyOnEmptyGroupReturnsZero() {
		TaskGroup empty = new TaskGroup("Empty");

		SchedulingStrategy s = new ParallelSchedulingStrategy();

		assertThat(s.scheduleMinutes(empty)).isEqualTo(0);
	}
}