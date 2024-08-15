package com.base.controllers;

import com.base.entities.RoleEntity;
import com.base.services.IRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/roles")
@PreAuthorize("denyAll()")
public class RoleController  {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_ALL_ROLE')")
    public List<RoleEntity> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ROLE')")
    public RoleEntity getById(@PathVariable("id") Long id) {
        return roleService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public RoleEntity save(@RequestBody RoleEntity rol) {
        return roleService.save(rol);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public RoleEntity update(@PathVariable("id") Long id,@RequestBody  RoleEntity rol) {
        return roleService.update(id,rol);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    public boolean deleteById(@PathVariable("id") Long id) {
        return roleService.deleteById(id);
    }
}
