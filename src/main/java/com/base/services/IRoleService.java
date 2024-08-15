package com.base.services;

import com.base.entities.RoleEntity;

import java.util.List;

public interface IRoleService {
    List<RoleEntity> getAll();
    RoleEntity getById(Long id);
    RoleEntity save(RoleEntity rol);
    RoleEntity update(Long id, RoleEntity rol);
    boolean deleteById(Long id);
}
