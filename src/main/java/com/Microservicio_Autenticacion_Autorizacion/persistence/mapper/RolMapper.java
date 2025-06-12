package com.Microservicio_Autenticacion_Autorizacion.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Rol;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.RolPermiso;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Permiso;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.PermisoResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.RolResponseDTO;

@Mapper(componentModel = "spring")
public interface RolMapper {
    @Mapping(source = "idRol", target = "rolId")
    @Mapping(source = "nombreRol", target = "nombreRol")
    @Mapping(source = "rolPermisos", target = "permisos", qualifiedByName = "mapPermisos")
    RolResponseDTO toDto(Rol rol);

    @Named("mapPermisos")
    default List<PermisoResponseDTO> mapPermisos(List<RolPermiso> rolPermisos) {
        return rolPermisos.stream()
                .map(RolPermiso::getPermiso)
                .map(this::toPermisoDto)
                .toList();
    }
    @Mapping(source = "idPermiso", target = "permisoId")
    @Mapping(source = "codigo", target = "codigo")
    @Mapping(source = "descripcion", target = "descripcion")
    PermisoResponseDTO toPermisoDto(Permiso permiso);
}
