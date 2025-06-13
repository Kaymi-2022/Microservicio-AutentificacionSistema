package com.Microservicio_Autenticacion_Autorizacion.persistence.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByUsername (String username);
}
