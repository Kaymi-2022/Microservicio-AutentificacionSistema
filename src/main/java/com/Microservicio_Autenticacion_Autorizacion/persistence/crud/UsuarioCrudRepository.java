package com.Microservicio_Autenticacion_Autorizacion.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.model.Usuario;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {


}
