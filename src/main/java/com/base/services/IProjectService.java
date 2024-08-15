package com.base.services;

import com.base.entities.ProjectEntity;

import java.util.List;

public interface IProjectService {
    List<ProjectEntity> getAll();
    ProjectEntity getById(Long id);
    ProjectEntity save(ProjectEntity project);
    ProjectEntity update(Long id, ProjectEntity project);
    boolean deleteById(Long id);
}
