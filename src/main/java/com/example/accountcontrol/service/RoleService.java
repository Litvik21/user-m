package com.example.accountcontrol.service;

import java.util.List;
import com.example.accountcontrol.model.Role;

public interface RoleService {

    Role save(Role role);

    Role getRoleByRoleName(Role.RoleName roleName);

    List<Role> findAll();
}
