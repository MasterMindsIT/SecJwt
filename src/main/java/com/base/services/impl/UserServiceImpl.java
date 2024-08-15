package com.base.services.impl;

import com.base.dtos.UserDTO;
import com.base.entities.RoleEntity;
import com.base.entities.UserEntity;
import com.base.repositories.RoleRepository;
import com.base.repositories.UserRepository;
import com.base.services.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new NoSuchElementException("Elemento no encontrado"));
    }

    @Override
    public UserDTO save(UserDTO userDto) {
        System.out.println("Desde service rgv "+userDto);
        List<String> rolesRequest = userDto.roleRequest().roleListName();
        Set<RoleEntity> roleEntityList = new HashSet<>(roleRepository.findRoleEntitiesByNameIn(rolesRequest));
        if (roleEntityList.isEmpty()) {
            roleEntityList.add(roleRepository.findByName("USER"));
        }
        UserEntity user = UserEntity.builder()
                .username(userDto.username())
                .email(userDto.email())
                .password(passwordEncoder.encode(userDto.password()))
                .isEnabled(userDto.isEnabled())
                .accountNoLocked(userDto.accountNoLocked())
                .credentialNoExpired(userDto.credentialNoExpired())
                .accountNoExpired(userDto.accountNoExpired())
                .roles(roleEntityList)
                .build();
        System.out.println(user.toString());
                userRepository.save(user);
                return userDto;
    }

    @Override
    public UserEntity update(Long id, UserEntity user) {
        UserEntity userEdit = getById(id);
        userEdit.setEmail(userEdit.getEmail());
        userEdit.setPassword(passwordEncoder.encode(user.getPassword()));
        //Los demas campos quedan en true por defecto ed los valores anteriores
        return userRepository.save(userEdit);
    }

    @Override
    public boolean deleteById(Long id)  {
        boolean exist = userRepository.existsById(id);
        if(exist){
            userRepository.deleteById(id);
        }
        return exist;
    }
}
