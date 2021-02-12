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


}