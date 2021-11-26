package com.oracle.construction.input;

import com.oracle.construction.pojo.Zone;
import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.ReportType;
import com.oracle.construction.repo.IProjectRepo;
import com.oracle.construction.repo.ProjectRepo;
import com.oracle.construction.service.IReporting;
import com.oracle.construction.service.ReportingService;
import com.oracle.construction.service.report.IReportService;
import com.oracle.construction.service.report.ReportAvgBuildTimeForZoneService;
import com.oracle.construction.service.report.ReportDistCustForContractorService;
import com.oracle.construction.service.report.ReportDistCustForZoneService;
import com.oracle.construction.service.report.ReportListCustForZoneService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataReader {

    private IReporting reportingService;
    private IProjectRepo projectRepo;

    public DataReader() {
        projectRepo = new ProjectRepo();
        List<IReportService> reportList = Arrays.asList(new ReportDistCustForContractorService(),
                new ReportAvgBuildTimeForZoneService(),
                new ReportDistCustForZoneService(),
                new ReportListCustForZoneService());
        reportingService = new ReportingService(reportList);
    }

    // read CSV file and create Object for report
    public static void main(String[] args) throws IOException, URISyntaxException {
        new DataReader().processData();
    }

    public void processData() throws URISyntaxException, IOException {
        try (Stream<String> projectsDetails =
                     Files.lines(Paths.get(DataReader.class.getClassLoader()
                             .getResource("projectDetails.csv").toURI()))) {
            projectsDetails.forEach(p -> {
                projectRepo.addProject(constructProject(p.split(",")));
            });
            System.out.println(reportingService.generateReport(ReportType.ALL, projectRepo.getAllProject()));
        }
    }

    public static Project constructProject(String[] details) {
        Project project = new Project();
        project.setCustomerId(isNumeric(details[0]) ? Integer.parseInt(details[0]) : 0);
        project.setContractId(isNumeric(details[1]) ? Integer.parseInt(details[1]) : 0);
        project.setGeoZone(Zone.valueOf(details[2]));
        project.setTeamCode(details[3]);
        project.setProjectCode(details[4]);
        String timeInSec = details[5].replaceAll("[^\\d.]", "");
        project.setBuildDuration(isNumeric(timeInSec) ? Integer.parseInt(timeInSec) : 0);
        return project;
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }
}
