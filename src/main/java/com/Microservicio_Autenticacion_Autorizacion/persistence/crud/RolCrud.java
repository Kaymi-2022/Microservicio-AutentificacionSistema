package com.Microservicio_Autenticacion_Autorizacion.persistence.crud;

import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolCrud extends CrudRepository<Rol,Integer> {

}
