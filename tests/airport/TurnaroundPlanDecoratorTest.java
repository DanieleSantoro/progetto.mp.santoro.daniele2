package airport;

import org.junit.Test;

import utils.BasicResource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnaroundPlanDecoratorTest {

    @Test
    public void basePlanDelegatesToStrategy() {
        TurnaroundTask root = new SimpleTask("Fueling", 10, Set.of(new BasicResource("FUEL_TRUCK")));
        TurnaroundPlan base = new BaseTurnaroundPlan(root, new SequentialSchedulingStrategy(), "Base");

        assertThat(base.schedulingMinutes()).isEqualTo(10);
        assertThat(base.description()).isEqualTo("Base");
        assertThat(base.rootTask()).isSameAs(root);
    }

    @Test
    public void decoratorsComposeMinutesAndDescription() {
        TurnaroundTask root = new SimpleTask("Fueling", 10, Set.of(new BasicResource("FUEL_TRUCK")));
        TurnaroundPlan base = new BaseTurnaroundPlan(root, new SequentialSchedulingStrategy(), "Base");

        TurnaroundPlan decorated =
                new DeIcingTurnaroundPlan(
                        new ExtraSecurityTurnaroundPlan(
                                new PriorityTurnaroundPlan(base)));

        assertThat(decorated.schedulingMinutes()).isEqualTo(24);
        assertThat(decorated.description())
                .contains("Base")
                
                .contains("Priority")
                .contains("ExtraSecurity")
                .contains("DeIcing");
        assertThat(decorated.rootTask()).isSameAs(root);
    }
        
    @Test
    public void singleDecoratorModifiesBasePlan() {
        TurnaroundTask root = new SimpleTask("A", 10, Set.of(new BasicResource("X")));
        TurnaroundPlan base = new BaseTurnaroundPlan(root, new SequentialSchedulingStrategy(), "Base");

        TurnaroundPlan p = new PriorityTurnaroundPlan(base);

        assertThat(p.schedulingMinutes()).isEqualTo(5);
    }
    
    @Test
    public void decoratorOrderDoesNotMatterForAdditiveDecorators() {
        TurnaroundTask root = new SimpleTask("A", 10, Set.of(new BasicResource("X")));
        TurnaroundPlan base = new BaseTurnaroundPlan(root, new SequentialSchedulingStrategy(), "Base");

        TurnaroundPlan p1 =
            new ExtraSecurityTurnaroundPlan(new PriorityTurnaroundPlan(base));
        TurnaroundPlan p2 =
            new PriorityTurnaroundPlan(new ExtraSecurityTurnaroundPlan(base));

        assertThat(p1.schedulingMinutes()).isEqualTo(p2.schedulingMinutes());
    }



    
    
    
}
