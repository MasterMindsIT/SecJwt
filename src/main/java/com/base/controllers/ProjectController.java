package com.base.controllers;

import com.base.entities.ProjectEntity;
import com.base.services.IProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@PreAuthorize("denyAll()")
public class ProjectController{
    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_ALL_PROJECT')")
    public List<ProjectEntity> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PROJECT')")
    public ProjectEntity getById(@PathVariable("id") Long id) {
        return projectService.getById(id);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_PROJECT')")
    public ProjectEntity save(@RequestBody ProjectEntity project) {
        return projectService.save(project);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PROJECT')")
    public ProjectEntity update(@PathVariable("id") Long id,@RequestBody ProjectEntity project) {
        return projectService.update(id, project);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PROJECT')")
    public boolean deleteById(@PathVariable("id") Long id) {
        return projectService.deleteById(id);
    }
}
