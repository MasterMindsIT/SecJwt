package com.base.services.impl;

import com.base.entities.ProjectEntity;
import com.base.repositories.ProjectRepository;
import com.base.services.IProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@Slf4j
public class ProjectServiceImpl implements IProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        log.info("Iniciando servicio: " + this.getClass().getName());
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectEntity> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectEntity getById(Long id) {
        return projectRepository.findById(id).orElseThrow(()->new NoSuchElementException("Proyecto no encontrado"));
    }

    @Override
    public ProjectEntity save(ProjectEntity project) {
        return projectRepository.save(project);
    }

    @Override
    public ProjectEntity update(Long id, ProjectEntity project) {
        ProjectEntity newProject = getById(id);
        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());
        newProject.setTipo(project.getTipo());
        return projectRepository.save(newProject);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean exist = projectRepository.existsById(id);
        if(exist){
            projectRepository.deleteById(id);
        }
        return exist;
    }
}
