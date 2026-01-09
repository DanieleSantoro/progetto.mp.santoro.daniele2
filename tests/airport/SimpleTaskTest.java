package airport;

import org.junit.Test;

import utils.BasicResource;
import utils.Resource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTaskTest {

    @Test
    public void simpleTaskStoresNameMinutesAndResources() {
        Resource fuel = new BasicResource("FUEL_TRUCK");

        SimpleTask t = new SimpleTask("Fueling", 15, Set.of(fuel));

        assertThat(t.getName()).isEqualTo("Fueling");
        assertThat(t.getMinutes()).isEqualTo(15);
        assertThat(t.getRequiredResources()).containsExactly(fuel);
    }
}
