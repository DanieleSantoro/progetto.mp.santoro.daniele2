package airport;

import org.junit.Test;

import utils.BasicResource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TaskGroupTest {

    @Test
    public void addAddsChild() {
        TaskGroup g = new TaskGroup("Handling");
        TurnaroundTask t = new SimpleTask("Fueling", 15, Set.of(new BasicResource("FUEL_TRUCK")));

        g.add(t);

        assertThat(g.getChildren()).hasSize(1);
        assertThat(g.getChildren().get(0)).isSameAs(t);
    }

    @Test
    public void removeRemovesChild() {
        TaskGroup g = new TaskGroup("Handling");
        TurnaroundTask t1 = new SimpleTask("Fueling", 15, Set.of(new BasicResource("FUEL_TRUCK")));
        TurnaroundTask t2 = new SimpleTask("Cleaning", 20, Set.of(new BasicResource("CLEANING_CREW")));

        g.add(t1);
        g.add(t2);
        g.remove(t1);

        assertThat(g.getChildren()).containsExactly(t2);
    }

    @Test
    public void compositeIsRecursive() {
        TaskGroup root = new TaskGroup("Plan");
        TaskGroup sub = new TaskGroup("Handling");
        sub.add(new SimpleTask("Cleaning", 20, Set.of(new BasicResource("CLEANING_CREW"))));

        root.add(sub);
        root.add(new SimpleTask("Fueling", 10, Set.of(new BasicResource("FUEL_TRUCK"))));

        assertThat(root.getChildren()).hasSize(2);
        assertThat(((TaskGroup) root.getChildren().get(0)).getChildren()).hasSize(1); // cast ok in tests
    }

    @Test
    public void childrenListIsUnmodifiable() {
        TaskGroup g = new TaskGroup("Handling");

        assertThatThrownBy(() -> g.getChildren().add(
                new SimpleTask("X", 1, Set.of(new BasicResource("GATE_AGENT")))
        )).isInstanceOf(UnsupportedOperationException.class);
    }
    
    @Test
    public void emptyGroupHasNoChildren() {
        TaskGroup g = new TaskGroup("Empty");
        assertThat(g.getChildren()).isEmpty();
    }

    @Test
    public void groupWithSingleTaskBehavesLikeLeafForVisitors() {
        TaskGroup g = new TaskGroup("G");
        g.add(new SimpleTask("A", 5, Set.of(new BasicResource("X"))));

        TotalDurationVisitor v = new TotalDurationVisitor();
        g.accept(v);

        assertThat(v.getTotalMinutes()).isEqualTo(5);
    }

}
