package com.oracle.construction.service.report;

import com.oracle.construction.pojo.Report;
import com.oracle.construction.util.TestingUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.oracle.construction.pojo.ReportType.ALL;
import static com.oracle.construction.pojo.ReportType.AVG_BUILD_TIME_BY_ZONE;
import static com.oracle.construction.pojo.ReportType.LIST_CUST_BY_ZONE;
import static org.junit.Assert.*;

public class ReportListCustForZoneServiceTest {

    private IReportService reportListCustForZoneService;

    @Before
    public void setUp() throws Exception {

        reportListCustForZoneService = new ReportListCustForZoneService();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void canHandle() throws Exception {
        assertTrue(reportListCustForZoneService.canHandle(LIST_CUST_BY_ZONE));
        assertTrue(reportListCustForZoneService.canHandle(ALL));
        assertFalse(reportListCustForZoneService.canHandle(AVG_BUILD_TIME_BY_ZONE));
    }

    @Test
    public void generateReport() throws Exception {

        Report report = reportListCustForZoneService.generateReport(TestingUtil
                .loadProjectDetails("FiveProjectDetails.csv"));

        assertNotNull(report.getTitle());
        assertEquals(LIST_CUST_BY_ZONE, report.getReportType());
        assertEquals(3, report.getBody().size());

        assertTrue(report.getBody().contains("geozone : US_EAST Unique contractId No : [2343225]"));
        assertTrue(report.getBody().contains("geozone : EU_WEST Unique contractId No : [3244132, 3244332]"));
        assertTrue(report.getBody().contains("geozone : US_WEST Unique contractId No : [1223456, 1233456]"));
    }

}