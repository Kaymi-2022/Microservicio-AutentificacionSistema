package com.Microservicio_Autenticacion_Autorizacion.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles_permisos")
public class RolPermiso {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "rol_permiso_id")
    private Integer idRolPermiso;

    @ManyToOne
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "permiso_id",insertable = false, updatable = false)
    private Permiso permiso;

    public Integer getIdRolPermiso() {
        return idRolPermiso;
    }

    public void setIdRolPermiso(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

}
