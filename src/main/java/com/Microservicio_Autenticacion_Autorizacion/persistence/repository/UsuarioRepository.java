package com.Microservicio_Autenticacion_Autorizacion.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByUsername (String username);

    @Query(value = """
    SELECT DISTINCT p.codigo
    FROM usuarios u
    JOIN roles r ON u.rol_id = r.rol_id
    JOIN roles_permisos rp ON r.rol_id = rp.rol_id
    JOIN permisos p ON rp.permiso_id = p.permiso_id
    WHERE u.usuario_id = :usuarioId
    """, nativeQuery = true)
    List<String> findPermisosCodigoByUsuarioId(@Param("usuarioId") Integer usuarioId);

    //List<Usuario> findByActivoTrueOrderByIdUsuarioDesc();
    List<Usuario> findByActivoTrueOrderByIdUsuarioDesc();
}
