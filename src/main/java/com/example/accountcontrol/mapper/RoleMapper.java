package com.example.accountcontrol.mapper;

import com.example.accountcontrol.model.Role;
import com.example.accountcontrol.dto.RoleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponseDto toDto(Role role) {
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(role.getId());
        dto.setName(role.getRoleName().name());
        return dto;
    }
}
