package com.oracle.construction.util;

import com.oracle.construction.pojo.Project;
import com.oracle.construction.pojo.Zone;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestingUtil {

    public static List<Project> loadProjectDetails(String fileName) {
        try {
            try (Stream<String> projectsDetails =
                         Files.lines(Paths.get(TestingUtil.class.getClassLoader()
                                 .getResource(fileName).toURI()))) {
                return projectsDetails.map(TestingUtil::constructProject).collect(toList());
                }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
       return Collections.emptyList();
    }


    private static Project constructProject(String projectDetails) {
        String[] details = projectDetails.split(",");
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
