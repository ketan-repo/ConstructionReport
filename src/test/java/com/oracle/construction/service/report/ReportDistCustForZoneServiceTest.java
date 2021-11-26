package com.oracle.construction.service.report;

import com.oracle.construction.pojo.Report;
import com.oracle.construction.util.TestingUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.oracle.construction.pojo.ReportType.ALL;
import static com.oracle.construction.pojo.ReportType.AVG_BUILD_TIME_BY_ZONE;
import static com.oracle.construction.pojo.ReportType.DIST_CUST_BY_ZONE;
import static org.junit.Assert.*;


public class ReportDistCustForZoneServiceTest {

    private IReportService reportDistCustForZoneService;

    @Before
    public void setUp() throws Exception {
        reportDistCustForZoneService = new ReportDistCustForZoneService();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void canHandle() throws Exception {
        assertTrue(reportDistCustForZoneService.canHandle(DIST_CUST_BY_ZONE));
        assertTrue(reportDistCustForZoneService.canHandle(ALL));
        assertFalse(reportDistCustForZoneService.canHandle(AVG_BUILD_TIME_BY_ZONE));
    }

    @Test
    public void generateReport() throws Exception {

        Report report = reportDistCustForZoneService.generateReport(TestingUtil
                .loadProjectDetails("FiveProjectDetails.csv"));

        assertNotNull(report.getTitle());
        assertEquals(DIST_CUST_BY_ZONE, report.getReportType());
        assertEquals(3, report.getBody().size());


        assertTrue(report.getBody().contains("geozone : US_EAST Unique customerId No : 1"));
        assertTrue(report.getBody().contains("geozone : EU_WEST Unique customerId No : 2"));
        assertTrue(report.getBody().contains("geozone : US_WEST Unique customerId No : 2"));
    }

}