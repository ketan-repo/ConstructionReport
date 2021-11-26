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


public class ReportDistCustForContractorServiceTest {

    private IReportService reportDistCustForContractorService;

    @Before
    public void setUp() throws Exception {
        reportDistCustForContractorService = new ReportDistCustForContractorService();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void canHandle() throws Exception {
        assertTrue(reportDistCustForContractorService.canHandle(DIST_CUST_BY_CONTRACTOR));
        assertTrue(reportDistCustForContractorService.canHandle(ALL));
        assertFalse(reportDistCustForContractorService.canHandle(AVG_BUILD_TIME_BY_ZONE));
    }

    @Test
    public void generateReport() throws Exception {

        Report report = reportDistCustForContractorService.generateReport(TestingUtil
                .loadProjectDetails("FiveProjectDetails.csv"));

        assertNotNull(report.getTitle());
        assertEquals(DIST_CUST_BY_CONTRACTOR, report.getReportType());
        assertEquals(2, report.getBody().size());

        assertTrue(report.getBody().contains("ContractId : 2345 Unique contractId No : 3"));
        assertTrue(report.getBody().contains("ContractId : 2346 Unique contractId No : 2"));
    }

}