package com.oracle.construction.repo;


import com.oracle.construction.pojo.Project;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepo implements IProjectRepo {

    private List<Project> projectList;

    public ProjectRepo() {
        projectList = new ArrayList<>();
    }

    @Override
    public boolean addProject(final Project project) {
        if (Optional.ofNullable(project).isPresent()){
            return projectList.add(project);
        }
        return false;
    }

    @Override
    public boolean addAllProject(final List<Project> projectsList) {
        if (Optional.ofNullable(projectsList).isPresent() && projectsList.size() > 0 ) {
            return this.projectList.addAll(projectsList);
        }
        return false;
    }

    @Override
    public List<Project> getAllProject() {
        return projectList;
    }
}
