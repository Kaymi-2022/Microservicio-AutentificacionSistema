package com.Microservicio_Autenticacion_Autorizacion.persistence.crud;

import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrud extends CrudRepository<Usuario, Integer> {


}
