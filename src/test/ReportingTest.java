package test;

import main.District;
import main.Incident;
import main.Reporting;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

        assertEquals(district1, reporting.maxAverageValInDistAt(2021));
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

    @Test
    void testGetIncidentWithValGreaterThan() {
        Reporting reporting = new Reporting();

        District district0 = new District("Newcastle");
        District district1 = new District("Durham");
        District district2 = new District("Sunderland");

        Incident incident = new Incident("NE1 8KL", 12, 2020, 250.0);
        Incident incident1 = new Incident("NE2 2GH", 1, 2021, 1020.5);
        Incident incident2 = new Incident("NE3 4YU", 2, 2021, 800.4);
        district0.addIncident(new Incident[] {incident, incident1, incident2});

        Incident incident3 = new Incident("DH2 7LQ", 12, 2020, 250);
        Incident incident4 = new Incident("DH3 7AJ", 1, 2021, 1220.0);
        Incident incident5 = new Incident("DH1 5SE", 2, 2021, 900.0);
        district1.addIncident(new Incident[] {incident3, incident4, incident5});

        Incident incident6 = new Incident("DH3 7AJ", 1, 2021, 3501.5);
        Incident incident7 = new Incident("DH1 5SE", 2, 2021, 950.0);
        district2.addIncident(new Incident[] {incident6, incident7});



        reporting.addDistrict(district0, district1, district2);

        List<Incident> expected = new ArrayList<>() {{
            add(incident1);
            add(incident4);
            add(incident6);
        }};
        assertEquals(expected, reporting.getIncidentWithValGreaterThan(1000.0));

    }
}