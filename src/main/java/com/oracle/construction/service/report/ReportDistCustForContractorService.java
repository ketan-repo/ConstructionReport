package com.oracle.construction.service.report;

import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.Report;
import com.oracle.construction.pojo.ReportType;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;


public class ReportDistCustForContractorService implements IReportService {

    @Override
    public boolean canHandle(final ReportType reportType) {
        return ReportType.DIST_CUST_BY_CONTRACTOR.equals(reportType) || ReportType.ALL.equals(reportType);
    }

    @Override
    public Report generateReport(final List<Project> projectList) {
        Map<Integer, Set<Integer>> result = projectList.stream()
                .collect(groupingBy(Project::getContractId, mapping(Project::getCustomerId, toSet())));
        Report report = new Report("The number of unique customerId for each contractId ", ReportType.DIST_CUST_BY_CONTRACTOR);
        result.entrySet().stream().forEach(e -> report.addBody("ContractId : " + e.getKey() + " Unique contractId No : " + e.getValue().size()));
        return report;
    }
}
