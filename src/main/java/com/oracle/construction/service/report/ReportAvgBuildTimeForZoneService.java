package com.oracle.construction.service.report;


import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.Report;
import com.oracle.construction.pojo.ReportType;
import com.oracle.construction.pojo.Zone;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;


public class ReportAvgBuildTimeForZoneService implements IReportService {


    @Override
    public boolean canHandle(final ReportType reportType) {
        return ReportType.AVG_BUILD_TIME_BY_ZONE.equals(reportType) || ReportType.ALL.equals(reportType);
    }

    @Override
    public Report generateReport(final List<Project> projectList) {
        Report report = new Report("The average build duration for each geozone", ReportType.AVG_BUILD_TIME_BY_ZONE);
        Map<Zone, Double> result = projectList.stream()
                .collect(groupingBy(Project::getGeoZone, averagingInt(Project::getBuildDuration)));
        result.entrySet().stream().forEach(e -> report.addBody("geozone : " + e.getKey() + " Average Build Durations : " + e.getValue()));
        return report;
    }
}
