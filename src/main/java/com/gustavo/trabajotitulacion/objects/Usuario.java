/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.objects;

/**
 *
 * @author gustavo
 */
public class Usuario {

    private int id;
    private String usuarioNombre;
    private String clave;
    private String privilegios;

    public Usuario() {
    }

    public Usuario(int id, String usuarioNombre, String clave, String privilegios) {
        this.id = id;
        this.usuarioNombre = usuarioNombre;
        this.clave = clave;
        this.privilegios = privilegios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioNombre=" + usuarioNombre + '}';
    }

}
