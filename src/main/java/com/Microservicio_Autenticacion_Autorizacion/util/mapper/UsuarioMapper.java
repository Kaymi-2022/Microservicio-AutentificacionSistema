package com.Microservicio_Autenticacion_Autorizacion.util.mapper;


import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.model.Usuario;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", 
        uses = {RolMapper.class})
@Component
public interface UsuarioMapper {
    // ----- DTOs de Entrada (Request) -----
    @Mapping(target = "activo", constant = "true")
    @Mapping(target = "fechaCreacion", expression = "java(new java.util.Date())")
    @Mapping(target = "rol", source = "rolId", qualifiedByName = "mapRolIdToRol")
    Usuario toEntity(UsuarioRegistroDTO dto);

    @Named("mapRolIdToRol")
    default Rol mapRolIdToRol(Integer rolId) {
        if (rolId == null) {
            return null;
        }
        Rol rol = new Rol();
        
        return rol;
    }
    

    // ----- DTOs de Salida (Response) -----
    @Mapping(source = "idUsuario", target = "usuarioId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "numeroColegiado", target = "numeroColegiado")
    @Mapping(source = "especialidad", target = "especialidad")
    @Mapping(source = "activo", target = "activo", defaultValue = "true")
    @Mapping(source = "rol", target = "rol")
    UsuarioResponseDTO toDto(Usuario usuario);
    // ----- Listas de DTOs -----
    List<UsuarioResponseDTO> toDtoList(List<Usuario> usuarios);

}
