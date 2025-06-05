package com.Microservicio_Autenticacion_Autorizacion.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.model.Rol;

public interface RolCrudRepository extends CrudRepository<Rol,Integer> {

}
