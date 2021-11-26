package com.oracle.construction.repo;

import com.oracle.construction.util.TestingUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ProjectRepoTest {

    private IProjectRepo projectRepo;


    @Before
    public void setUp() {
        projectRepo = new ProjectRepo();
    }


    @After
    public void tearDown() {

    }

    @Test
    public void addProject() {
        projectRepo.addProject(TestingUtil.loadProjectDetails("OneProjectDetails.csv").get(0));
        assertEquals(1, projectRepo.getAllProject().size());
    }

    @Test
    public void addProjectNull() {
        projectRepo.addProject(null);
        assertEquals(0, projectRepo.getAllProject().size());
    }

    @Test
    public void addAllProject() {
        projectRepo.addAllProject(TestingUtil.loadProjectDetails("TwoProjectDetails.csv"));
        assertEquals(2, projectRepo.getAllProject().size());
    }

    @Test
    public void addAllProjectNull() {
        projectRepo.addAllProject(null);
        assertEquals(0, projectRepo.getAllProject().size());
    }

    @Test
    public void addAllProjectEmpty() {
        projectRepo.addAllProject(Collections.emptyList());
        assertEquals(0, projectRepo.getAllProject().size());
    }

    @Test
    public void getAllProject() {
        projectRepo.addAllProject(TestingUtil.loadProjectDetails("TwoProjectDetails.csv"));
        assertEquals(2, projectRepo.getAllProject().size());
    }
}