package com.oracle.construction.service.report;

import com.oracle.construction.pojo.Report;
import com.oracle.construction.util.TestingUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.oracle.construction.pojo.ReportType.ALL;
import static com.oracle.construction.pojo.ReportType.AVG_BUILD_TIME_BY_ZONE;
import static com.oracle.construction.pojo.ReportType.DIST_CUST_BY_CONTRACTOR;
import static org.junit.Assert.*;


public class ReportAvgBuildTimeForZoneServiceTest {
    private IReportService reportAvgBuildTimeForZoneService;
    @Before
    public void setUp() throws Exception {
        reportAvgBuildTimeForZoneService = new ReportAvgBuildTimeForZoneService();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void canHandle() throws Exception {
        assertTrue(reportAvgBuildTimeForZoneService.canHandle(AVG_BUILD_TIME_BY_ZONE));
        assertTrue(reportAvgBuildTimeForZoneService.canHandle(ALL));
        assertFalse(reportAvgBuildTimeForZoneService.canHandle(DIST_CUST_BY_CONTRACTOR));
    }

    @Test
    public void generateReport() throws Exception {
        Report report = reportAvgBuildTimeForZoneService.generateReport(TestingUtil
                .loadProjectDetails("FiveProjectDetails.csv"));
        assertNotNull(report.getTitle());
        assertEquals(AVG_BUILD_TIME_BY_ZONE, report.getReportType());
        assertEquals(3, report.getBody().size());

        assertTrue(report.getBody().contains("geozone : EU_WEST Average Build Durations : 4222.0"));
        assertTrue(report.getBody().contains("geozone : US_EAST Average Build Durations : 3445.0"));
        assertTrue(report.getBody().contains("geozone : US_WEST Average Build Durations : 2216.0"));

    }

}