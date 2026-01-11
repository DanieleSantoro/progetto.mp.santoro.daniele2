package airport;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import utils.BasicResource;
import utils.SequentialSchedulingStrategy;

public class BaseTurnaroundPlanTest {

	@Test
	public void basePlanDelegatesSchedulingToStrategy() {
		TurnaroundTask root = new SimpleTask(
				"Fueling",
				10,
				Set.of(new BasicResource("FUEL_TRUCK")));
		
		SchedulingStrategy strategy = new SequentialSchedulingStrategy();
		TurnaroundPlan plan = new BaseTurnaroundPlan(root, strategy, "Base");
		
		assertThat(plan.rootTask()).isSameAs(root);
        assertThat(plan.schedulingStrategy()).isSameAs(strategy);
        assertThat(plan.schedulingMinutes()).isEqualTo(10);
        assertThat(plan.description()).isEqualTo("Base");
	}

}
