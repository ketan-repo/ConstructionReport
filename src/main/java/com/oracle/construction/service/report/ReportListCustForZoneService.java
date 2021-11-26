package com.oracle.construction.service.report;


import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.Report;
import com.oracle.construction.pojo.ReportType;
import com.oracle.construction.pojo.Zone;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

public class ReportListCustForZoneService implements IReportService {


    @Override
    public boolean canHandle(final ReportType reportType) {
        return ReportType.LIST_CUST_BY_ZONE.equals(reportType) || ReportType.ALL.equals(reportType);
    }

    @Override
    public Report generateReport(final List<Project> projectList) {
        Map<Zone, Set<Integer>> result = projectList.stream()
                .collect(groupingBy(Project::getGeoZone, mapping(Project::getCustomerId, toSet())));
        Report report = new Report("The list of unique customerId for each geozone", ReportType.LIST_CUST_BY_ZONE);
        result.entrySet().stream().forEach(e -> report.addBody("geozone : " + e.getKey() + " Unique contractId No : " + e.getValue()));
        return report;
    }
}
