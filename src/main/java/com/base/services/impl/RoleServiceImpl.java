package com.base.services.impl;

import com.base.dtos.UserDTO;
import com.base.entities.RoleEntity;
import com.base.entities.UserEntity;
import com.base.repositories.RoleRepository;
import com.base.services.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity getById(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Rol no encontrado"));
    }

    @Override
    public RoleEntity save(RoleEntity rol) {
        return roleRepository.save(rol);
    }

    @Override
    public RoleEntity update(Long id, RoleEntity rol) {
        RoleEntity newRol = getById(id);
        newRol.setName(rol.getName());
        newRol.setPermissionList(rol.getPermissionList());
        return roleRepository.save(newRol);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean exist = roleRepository.existsById(id);
        if(exist){
            roleRepository.deleteById(id);
        }
        return exist;
    }
}
