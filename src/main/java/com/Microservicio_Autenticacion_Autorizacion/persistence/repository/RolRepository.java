package com.Microservicio_Autenticacion_Autorizacion.persistence.repository;

import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol,Integer> {

}
