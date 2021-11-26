package com.oracle.construction.service;


import com.oracle.construction.pojo.Report;
import com.oracle.construction.pojo.ReportType;
import com.oracle.construction.service.report.IReportService;
import com.oracle.construction.util.TestingUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.oracle.construction.pojo.ReportType.ALL;
import static com.oracle.construction.pojo.ReportType.AVG_BUILD_TIME_BY_ZONE;
import static com.oracle.construction.pojo.ReportType.DIST_CUST_BY_CONTRACTOR;
import static com.oracle.construction.pojo.ReportType.DIST_CUST_BY_ZONE;
import static com.oracle.construction.pojo.ReportType.LIST_CUST_BY_ZONE;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceTest {

    private IReporting reportingService;


    @Mock
    private IReportService reportDistCustForContractorService;

    @Mock
    private IReportService reportAvgBuildTimeForZoneService;

    @Mock
    private IReportService reportDistCustForZoneService;

    @Mock
    private IReportService reportListCustForZoneService;

    List<IReportService> reportServiceList;

    @Before
    public void setUp() {

        reportServiceList = Arrays.asList(reportDistCustForContractorService,
                reportAvgBuildTimeForZoneService,
                reportDistCustForZoneService,
                reportListCustForZoneService);
        reportingService = new ReportingService(reportServiceList);

    }

    @After
    public void tearDown() {

    }



    @Test
    public void generateReportForAvgBuildTime() {

        when(reportAvgBuildTimeForZoneService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportAvgBuildTimeForZoneService.generateReport(anyList())).thenReturn(new Report("test report",
                                                                                                AVG_BUILD_TIME_BY_ZONE));
        List<Report> reportList = reportingService.generateReport(AVG_BUILD_TIME_BY_ZONE, TestingUtil.loadProjectDetails("OneProjectDetails.csv"));

        checkReportData(AVG_BUILD_TIME_BY_ZONE, reportList);
        checkForGenerateReport(0,1,0,0);
        checkForCanHandler(1,1,1,1);
    }

    @Test
    public void generateReportForDistCustForContractor() {

        when(reportDistCustForContractorService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportDistCustForContractorService.generateReport(anyList())).thenReturn(new Report("test report",
                DIST_CUST_BY_CONTRACTOR));

        List<Report> reportList = reportingService.generateReport(DIST_CUST_BY_CONTRACTOR, TestingUtil.loadProjectDetails("OneProjectDetails.csv"));

        checkReportData(DIST_CUST_BY_CONTRACTOR, reportList);
        checkForGenerateReport(1,0,0,0);
        checkForCanHandler(1,1,1,1);
    }

    @Test
    public void generateReportForDistCustForZone() {

        when(reportDistCustForZoneService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportDistCustForZoneService.generateReport(anyList())).thenReturn(new Report("test report",
                DIST_CUST_BY_ZONE));

        List<Report> reportList = reportingService.generateReport(DIST_CUST_BY_ZONE, TestingUtil.loadProjectDetails("OneProjectDetails.csv"));

        checkReportData(DIST_CUST_BY_ZONE, reportList);
        checkForGenerateReport(0,0,1,0);
        checkForCanHandler(1,1,1,1);
    }

    @Test
    public void generateReportForListDistCustForZone() {

        when(reportListCustForZoneService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportListCustForZoneService.generateReport(anyList())).thenReturn(new Report("test report",
                LIST_CUST_BY_ZONE));

        List<Report> reportList = reportingService.generateReport(LIST_CUST_BY_ZONE, TestingUtil.loadProjectDetails("OneProjectDetails.csv"));

        checkReportData(LIST_CUST_BY_ZONE, reportList);
        checkForGenerateReport(0,0,0,1);
        checkForCanHandler(1,1,1,1);
    }

    @Test
    public void generateReportForAll() {

        when(reportAvgBuildTimeForZoneService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportAvgBuildTimeForZoneService.generateReport(anyList())).thenReturn(new Report("test report",
                AVG_BUILD_TIME_BY_ZONE));

        when(reportDistCustForContractorService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportDistCustForContractorService.generateReport(anyList())).thenReturn(new Report("test report",
                DIST_CUST_BY_CONTRACTOR));


        when(reportDistCustForZoneService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportDistCustForZoneService.generateReport(anyList())).thenReturn(new Report("test report",
                DIST_CUST_BY_ZONE));

        when(reportListCustForZoneService.canHandle(any(ReportType.class))).thenReturn(true);
        when(reportListCustForZoneService.generateReport(anyList())).thenReturn(new Report("test report",
                LIST_CUST_BY_ZONE));

        List<Report> reportList = reportingService.generateReport(ALL, TestingUtil.loadProjectDetails("OneProjectDetails.csv"));

        assertEquals(4 , reportList.size());
        checkForGenerateReport(1,1,1,1);
        checkForCanHandler(1,1,1,1);
    }

    @Test
    public void generateReportForNull() {
        List<Report> reportList = reportingService.generateReport(null, TestingUtil.loadProjectDetails("OneProjectDetails.csv"));
        assertEquals(0 , reportList.size());
        checkForGenerateReport(0,0,0,0);
        checkForCanHandler(0,0,0,0);
    }


    private void checkForCanHandler (int distCustForContractorCount, int avgBuildTimeCount, int distCustForZoneCount, int listCustForZoneCount) {
        verify(reportAvgBuildTimeForZoneService, times(avgBuildTimeCount)).canHandle(any(ReportType.class));
        verify(reportDistCustForContractorService, times(distCustForContractorCount)).canHandle(any(ReportType.class));
        verify(reportDistCustForZoneService, times(distCustForZoneCount)).canHandle(any(ReportType.class));
        verify(reportListCustForZoneService, times(listCustForZoneCount)).canHandle(any(ReportType.class));
    }

    private void checkForGenerateReport (int distCustForContractorCount, int avgBuildTimeCount, int distCustForZoneCount, int listCustForZoneCount) {
        verify(reportDistCustForContractorService, times(distCustForContractorCount)).generateReport(anyList());
        verify(reportAvgBuildTimeForZoneService, times(avgBuildTimeCount)).generateReport(anyList());
        verify(reportDistCustForZoneService, times(distCustForZoneCount)).generateReport(anyList());
        verify(reportListCustForZoneService, times(listCustForZoneCount)).generateReport(anyList());
    }

    private void checkReportData(ReportType reportType, List<Report> reportList) {
        assertEquals(1 , reportList.size());
        assertNotNull(reportList.get(0).getTitle());
        assertEquals(reportType, reportList.get(0).getReportType());
        assertEquals(0, reportList.get(0).getBody().size());
    }
}