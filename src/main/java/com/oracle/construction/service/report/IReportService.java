package com.oracle.construction.service.report;


import com.oracle.construction.pojo.ReportType;
import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.Report;

import java.util.List;

public interface IReportService {

    boolean canHandle(final ReportType reportType);

    Report generateReport(final List<Project> projectList);
}
