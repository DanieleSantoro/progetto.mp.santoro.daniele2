package airport;

import org.junit.Test;

import utils.BasicResource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalDurationVisitorTest {

    @Test
    public void totalDurationVisitorSumsAllTasksRecursively() {
        TaskGroup plan = new TaskGroup("Plan");
        TaskGroup handling = new TaskGroup("Handling");

        handling.add(new SimpleTask("Cleaning", 20, Set.of(new BasicResource("CLEANING_CREW"))));
        handling.add(new SimpleTask("Baggage", 15, Set.of(new BasicResource("BAGGAGE_TEAM"))));

        plan.add(handling);
        plan.add(new SimpleTask("Fueling", 10, Set.of(new BasicResource("FUEL_TRUCK"))));

        TotalDurationVisitor v = new TotalDurationVisitor();
        plan.accept(v);

        assertThat(v.getTotalMinutes()).isEqualTo(45);
    }

    @Test
    public void visitorOnEmptyGroupReturnsZero() {
        TaskGroup plan = new TaskGroup("Plan");

        TotalDurationVisitor v = new TotalDurationVisitor();
        plan.accept(v);

        assertThat(v.getTotalMinutes()).isEqualTo(0);
    }
}
