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

}