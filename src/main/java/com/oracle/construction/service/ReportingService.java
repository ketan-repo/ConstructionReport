package com.oracle.construction.service;

import com.oracle.construction.pojo.ReportType;
import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.Report;
import com.oracle.construction.service.report.IReportService;

import java.util.ArrayList;

import java.util.List;

public class ReportingService implements IReporting {

    private List<IReportService> reportServiceList;


    public ReportingService(final List<IReportService> reportList) {
        this.reportServiceList = reportList;

    }

    public List<Report> generateReport(final ReportType reportType, final List<Project> projectLis) {
        List<Report> reportDetails = new ArrayList<>();
        reportServiceList.stream().filter(report -> report.canHandle(reportType))
                .forEach(report -> reportDetails.add(report.generateReport(projectLis)));
        return reportDetails;
    }

}
