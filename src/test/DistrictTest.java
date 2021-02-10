package test;

import main.District;
import main.Incident;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistrictTest {

    @Test
    void testGetIncidents() {
        Incident incident = new Incident("NE1", 12, 2020, 250.0);
        Incident incident1 = new Incident("NE2", 1, 2021, 100.0);
        District district = new District("Newcastle", new Incident[] {incident, incident1} );
        assertEquals(2, district.getIncidents().size());
        assertEquals(incident, district.getIncidents().get(0));
        assertEquals(incident1, district.getIncidents().get(1));
    }

    @Test
    void testAddIncident() {
        Incident incident = new Incident("WC1", 2, 2021, 1500.45);
        District district = new District("London");
        district.addIncident(incident);
        assertEquals(1, district.getIncidents().size());
        assertEquals(incident, district.getIncidents().get(0));
    }

    @Test
    void testGetAverageVal() {
    }

    @Test
    void testGetHighestVal() {
    }

    @Test
    void testGetIncidentWithValGreaterThan() {
    }
}