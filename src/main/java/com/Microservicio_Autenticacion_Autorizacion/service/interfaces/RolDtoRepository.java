package com.Microservicio_Autenticacion_Autorizacion.service.interfaces;

import java.util.List;

import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.RolRequestDTO;

public interface RolDtoRepository {

    List<RolRequestDTO> findAllRoles();
    RolRequestDTO findRoleById(Long id);
    RolRequestDTO saveRole(RolRequestDTO rolRequestDTO);
    RolRequestDTO updateRole(Long id, RolRequestDTO rolRequestDTO);
    void deleteRole(Long id);

}
