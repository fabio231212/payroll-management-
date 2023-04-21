/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sebastian
 */
public class Usuario {

    private String id;
    private int idTipoUsuario;
    private String nombre;
    private String apellidos;
    private String clave;
    private String activo;

    public Usuario(String id, String clave, String activo) {
        this.id = id;
        this.clave = clave;
        this.activo = activo;
    }

    public Usuario(String id, int idTipoUsuario, String nombre, String apellidos, String clave, String activo) {
        this.id = id;
        this.idTipoUsuario = idTipoUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        this.activo = activo;
    }
    

    public Usuario() {
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
