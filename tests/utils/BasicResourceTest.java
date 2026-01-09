package utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicResourceTest {

    @Test
    public void resourcesWithSameNameAreEqual() {
        Resource r1 = new BasicResource("FUEL_TRUCK");
        Resource r2 = new BasicResource("FUEL_TRUCK");

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }

    @Test
    public void resourcesWithDifferentNameAreNotEqual() {
        Resource r1 = new BasicResource("FUEL_TRUCK");
        Resource r2 = new BasicResource("CLEANING_CREW");

        assertThat(r1).isNotEqualTo(r2);
    }
}
