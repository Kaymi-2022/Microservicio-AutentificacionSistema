package com.Microservicio_Autenticacion_Autorizacion.persistence.repository;

import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UsuarioRepositoryPaginacion extends ListPagingAndSortingRepository<Usuario,Integer> {

    Page<Usuario> findByActivoTrue(Pageable pageable);
}
