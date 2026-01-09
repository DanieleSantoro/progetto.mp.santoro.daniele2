package airport;

import org.junit.Test;

import utils.Resource;
import utils.BasicResource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RequiredResourcesVisitorTest {

    @Test
    public void visitorUnionsResourcesRecursively() {
        TaskGroup plan = new TaskGroup("Plan");

        plan.add(new SimpleTask("Fueling", 10, Set.of(new BasicResource("FUEL_TRUCK"))));

        TaskGroup handling = new TaskGroup("Handling");
        handling.add(new SimpleTask("Cleaning", 20, Set.of(new BasicResource("CLEANING_CREW"))));
        plan.add(handling);

        RequiredResourcesVisitor v = new RequiredResourcesVisitor();
        plan.accept(v);

        assertThat(v.getResources())
                .containsExactlyInAnyOrder(
                        new BasicResource("FUEL_TRUCK"),
                        new BasicResource("CLEANING_CREW")
                );
    }
    
    @Test
    public void requiredResourcesVisitorOnEmptyGroupIsEmpty() {
        TaskGroup g = new TaskGroup("Empty");

        RequiredResourcesVisitor v = new RequiredResourcesVisitor();
        g.accept(v);

        assertThat(v.getResources()).isEmpty();
    }

    @Test
    public void visitorDoesNotDuplicateResources() {
        Resource r = new BasicResource("X");

        TaskGroup g = new TaskGroup("G");
        g.add(new SimpleTask("A", 5, Set.of(r)));
        g.add(new SimpleTask("B", 10, Set.of(r)));

        RequiredResourcesVisitor v = new RequiredResourcesVisitor();
        g.accept(v);

        assertThat(v.getResources()).containsExactly(r);
    }

    
}
