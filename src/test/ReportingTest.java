package test;

import main.District;
import main.Incident;
import main.Reporting;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReportingTest {


    @Test
    void testGetDistricts() {
        Reporting reporting = new Reporting();
        District district = new District("Sunderland");
        reporting.addDistrict(district);
        assertEquals(new District("Sunderland"), reporting.getDistricts().get(0));
    }

    @Test
    void testAddDistrict() {
        Reporting reporting = new Reporting();
        reporting.addDistrict(new District("test0"));
        assertEquals(1, reporting.getDistricts().size());
    }

    @Test
    void testMaxAverageValInDist() {
        Reporting reporting = new Reporting();
        District district0 = new District("Newcastle");
        District district1 = new District("Durham");

        Incident incident = new Incident("NE1 8KL", 12, 2020, 250.0);
        Incident incident1 = new Incident("NE2 2GH", 1, 2021, 1020.5);
        Incident incident2 = new Incident("NE3 4YU", 2, 2021, 800.4);
        district0.addIncident(new Incident[] {incident, incident1, incident2});

        Incident incident3 = new Incident("DH2 7LQ", 12, 2020, 250);
        Incident incident4 = new Incident("DH3 7AJ", 1, 2021, 1220.0);
        Incident incident5 = new Incident("DH1 5SE", 2, 2021, 900.0);
        district1.addIncident(new Incident[] {incident3, incident4, incident5});

        reporting.addDistrict(district0, district1);

        assertEquals(district1, reporting.maxAverageValInDist());
    }

    @Test
    void testMaxIncidentVal() {
        Reporting reporting = new Reporting();

        assertNull(reporting.maxIncidentVal());

        District district0 = new District("Newcastle");
        District district1 = new District("Durham");

        Incident incident = new Incident("NE1 8KL", 12, 2020, 250.0);
        Incident incident1 = new Incident("NE2 2GH", 1, 2021, 1020.5);
        Incident incident2 = new Incident("NE3 4YU", 2, 2021, 800.4);
        district0.addIncident(new Incident[] {incident, incident1, incident2});

        Incident incident3 = new Incident("DH2 7LQ", 12, 2020, 250);
        Incident incident4 = new Incident("DH3 7AJ", 1, 2021, 1220.0);
        Incident incident5 = new Incident("DH1 5SE", 2, 2021, 900.0);
        district1.addIncident(new Incident[] {incident3, incident4, incident5});

        reporting.addDistrict(district0, district1);

        assertEquals(incident4, reporting.maxIncidentVal());

    }
}