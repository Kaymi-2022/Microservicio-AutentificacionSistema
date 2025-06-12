package com.Microservicio_Autenticacion_Autorizacion.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permiso_id")
    private Integer idPermiso;

    private String codigo;

    private String descripcion;

    @OneToMany(mappedBy = "permiso")
    private List<RolPermiso> rolPermisos;

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<RolPermiso> getRolPermisos() {
        return rolPermisos;
    }

    public void setRolPermisos(List<RolPermiso> rolPermisos) {
        this.rolPermisos = rolPermisos;
    }

    
}
