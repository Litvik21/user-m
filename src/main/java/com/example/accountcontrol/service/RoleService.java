package com.example.accountcontrol.service;

import com.example.accountcontrol.model.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role getRoleByRoleName(Role.RoleName roleName);

    List<Role> findAll();
}
