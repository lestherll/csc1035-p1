package test;

import main.Incident;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncidentTest {

    @Test
    public void testIncidentInstantiation() {
        Incident incident = new Incident("NE1", 12, 2020, 20.0);
        assertEquals("NE1", incident.getPostCode());
        assertEquals(12, incident.getMonth());
        assertEquals(2020, incident.getYear());
        assertEquals(20.0, incident.getValue());
    }

    @Test
    public void testSetters() {
        Incident incident = new Incident();

        incident.setPostCode("TS5");
        assertEquals("TS5", incident.getPostCode());

        incident.setMonth(12);
        assertEquals(12, incident.getMonth());

        incident.setYear(2020);
        assertEquals(2020, incident.getYear());

        incident.setValue(100);
        assertEquals(100, incident.getValue());
    }

}