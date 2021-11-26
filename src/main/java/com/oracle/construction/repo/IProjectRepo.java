package com.oracle.construction.repo;


import com.oracle.construction.pojo.Project;

import java.util.List;

public interface IProjectRepo {

    boolean addProject(final Project project);

    boolean addAllProject(final List<Project> projectsList);

    List<Project> getAllProject();


}
