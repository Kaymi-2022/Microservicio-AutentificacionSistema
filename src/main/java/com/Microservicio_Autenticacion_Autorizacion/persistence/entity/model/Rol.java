package com.Microservicio_Autenticacion_Autorizacion.persistence.entity.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rol_id")
    private Integer idRol;

    @Column(name = "nombre_rol", nullable = false,unique = true, length = 50)
    private String nombreRol;

    @Column(name = "nivel_permisos", nullable = false)
    @ColumnDefault("1")
    private Integer nivelPermisos;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RolPermiso> rolPermisos;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Integer getNivelPermisos() {
        return nivelPermisos;
    }

    public void setNivelPermisos(Integer nivelPermisos) {
        this.nivelPermisos = nivelPermisos;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<RolPermiso> getRolPermisos() {
        return rolPermisos;
    }

    public void setRolPermisos(List<RolPermiso> rolPermisos) {
        this.rolPermisos = rolPermisos;
    }
}
