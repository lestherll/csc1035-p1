package test;

import main.District;
import main.Incident;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistrictTest {

    @Test
    void testDistrictAndSetName() {
        District district = new District("Durham");
        assertEquals("Durham", district.getName());

        district.setName("City of Durham");
        assertNotEquals("Durham", district.getName());
        assertEquals("City of Durham", district.getName());
    }

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

        List<Incident> incidents = new ArrayList<>();
        incidents.add(new Incident());
        incidents.add(new Incident());

        district.addIncident(incidents);
        assertEquals(3, district.getIncidents().size());

        district.addIncident(new Incident[] {new Incident(), new Incident()});
        assertEquals(5, district.getIncidents().size());

    }

    @Test
    void testGetAverageVal() {
        District district = new District("Newcastle");

        assertEquals(0, district.getAverageVal());

        Incident incident = new Incident("NE1", 12, 2020, 250.0);
        Incident incident1 = new Incident("NE2", 1, 2021, 100.0);
        district.addIncident(incident);
        district.addIncident(incident1);

        double expected = (incident.getValue() + incident1.getValue())/2;

        assertEquals(expected, district.getAverageVal());
    }

    @Test
    void testGetHighestVal() {
        District district = new District("Newcastle");

        assertNull(district.getHighestVal());

        Incident incident = new Incident("NE1", 12, 2020, 250.0);
        Incident incident1 = new Incident("NE2", 1, 2021, 1200.0);
        Incident incident2 = new Incident("NE3", 2, 2021, 900.0);
        district.addIncident(new Incident[] {incident, incident1, incident2});

        assertEquals(incident1, district.getHighestVal());
    }

    @Test
    void testGetIncidentWithValGreaterThan() {
        District district = new District("Newcastle");

        assertEquals(new ArrayList<>(), district.getIncidentWithValGreaterThan(20));

        Incident incident = new Incident("NE2", 1, 2021, 500.5);
        Incident incident1 = new Incident("NE3", 2, 2021, 756);
        Incident incident2 = new Incident("NE5", 1, 2021, 400.0);
        Incident incident3 = new Incident("NE20", 4, 2020, 420.69);

        List<Incident> incidents = new ArrayList<>(){{
            add(incident);
            add(incident1);
            add(incident2);
            add(incident3);
        }};
        district.addIncident(incidents);

        assertEquals(4, district.getIncidentWithValGreaterThan(0).size());
        assertEquals(3, district.getIncidentWithValGreaterThan(401).size());
        assertEquals(0, district.getIncidentWithValGreaterThan(1000).size());

    }
}