package airport;

import org.junit.Test;

import utils.BasicResource;
import utils.SequentialSchedulingStrategy;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SchedulingStrategyTest {

    @Test
    public void sequentialStrategyIsSumOfAllMinutes() {
        TaskGroup plan = new TaskGroup("Plan");
        plan.add(new SimpleTask("A", 10, Set.of(new BasicResource("X"))));
        plan.add(new SimpleTask("B", 5, Set.of(new BasicResource("Y"))));

        SchedulingStrategy s = new SequentialSchedulingStrategy();

        assertThat(s.scheduleMinutes(plan)).isEqualTo(15);
    }

    @Test
    public void parallelStrategyTakesMaxInsideGroupsRecursively() {
        TaskGroup root = new TaskGroup("Root");

        TaskGroup block = new TaskGroup("ParallelBlock");
        block.add(new SimpleTask("Cleaning", 20, Set.of(new BasicResource("CLEANING_CREW"))));
        block.add(new SimpleTask("Fueling", 10, Set.of(new BasicResource("FUEL_TRUCK"))));

        TaskGroup nested = new TaskGroup("Nested");
        nested.add(new SimpleTask("Deep", 30, Set.of(new BasicResource("Z"))));
        nested.add(new SimpleTask("Shallow", 12, Set.of(new BasicResource("W"))));

        block.add(nested);
        root.add(block);
        root.add(new SimpleTask("Other", 25, Set.of(new BasicResource("Q"))));

        // block duration = max(20,10, max(30,12)=30) = 30
        // root duration  = max(block=30, other=25) = 30
        SchedulingStrategy s = new ParallelSchedulingStrategy();

        assertThat(s.scheduleMinutes(root)).isEqualTo(30);
    }
    
    @Test
    public void sequentialAndParallelProduceDifferentResults() {
        TaskGroup g = new TaskGroup("G");
        g.add(new SimpleTask("A", 10, Set.of(new BasicResource("X"))));
        g.add(new SimpleTask("B", 20, Set.of(new BasicResource("Y"))));

        int seq = new SequentialSchedulingStrategy().scheduleMinutes(g);
        int par = new ParallelSchedulingStrategy().scheduleMinutes(g);

        assertThat(seq).isEqualTo(30);
        assertThat(par).isEqualTo(20);
    }

}
