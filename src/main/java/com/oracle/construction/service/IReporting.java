package com.oracle.construction.service;


import com.oracle.construction.pojo.ReportType;
import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.Report;

import java.util.List;

public interface IReporting {

    List<Report> generateReport(final ReportType reportType, final List<Project> projectList);
}
