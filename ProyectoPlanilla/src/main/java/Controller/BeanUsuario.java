/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.EmpleadoDAL;
import DAO.SNMPExceptions;
import DAO.TipoUsuarioDAO;
import DAO.UsuarioDAO;
import Model.Empleado;
import Model.TipoUsuario;
import Model.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Sebastian
 */
public class BeanUsuario {

    private String id;
    private int idTipoUsuario;
    private String nombre;
    private String apellidos;
    private String clave;
    private String mensaje;
    private String activo;
    ArrayList<Usuario> listaUsuarios;
    LinkedList<SelectItem> listaTipoU = new LinkedList<>();

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LinkedList<SelectItem> getListaTipoU()
            throws SNMPExceptions, SQLException {
        String descripcion = "";
        int id = 0;

        LinkedList<TipoUsuario> lista = new LinkedList<TipoUsuario>();
        TipoUsuarioDAO tIdDB = new TipoUsuarioDAO();

        lista = tIdDB.VerTodosLinked();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0,
                "Seleccione Tipo de Identificacion"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            TipoUsuario tipoU = (TipoUsuario) iter.next();
            descripcion = tipoU.getDescripcion();
            id = tipoU.getId();
            resultList.add(new SelectItem(id, descripcion));
        }
        return resultList;
    }

    public boolean registrarOActualizarUsuario() {

        try {
           
            if (!clave.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]?)(?=\\S+$).{8,12}$")) {
                setMensaje("Contraseña insegura");
                return false;
            }
            Usuario usu = new Usuario(id, idTipoUsuario, nombre, apellidos, clave, activo);
            UsuarioDAO rDB = new UsuarioDAO();

            if (this.id.equals("") || this.idTipoUsuario == 0 || this.nombre.equals("") || this.apellidos.equals("") || this.clave.equals("")) {
                setMensaje("Campos Obligatorios");
            } else {

                if (rDB.ObtenerUsuario(id) == null) {
                    rDB.RegistrarUsuario(usu);
                    setMensaje("Usuario Registrado Correctamente");
                } else {
                    rDB.ActualizarUsuario(usu);
                    setMensaje("Usuario actualizado Correctamente");

                }
            }
            limpiar();
            return true;
        } catch (Exception e) {
            setMensaje(e.toString());
            
            return false;
        }
    }

    public String validarUsuario() throws SNMPExceptions, SQLException, IOException {
        String ruta = "";
        Usuario usuario = new Usuario(id, clave, activo);
        UsuarioDAO uDB = new UsuarioDAO();

        if (this.id.equals("") || this.clave.equals("")) {

            this.setMensaje("Campos Obligatorios!");

        } else {

            if (uDB.validarUsuario(id, clave)) {

                if (uDB.ObtenerUsuario(id).getActivo().equalsIgnoreCase("Inactivo")) {
                    this.setMensaje("El usuario se encuentra inactivo");
                } else {
                   
                       ruta = "/Principal/principal.xhtml";
                }

            } else {
                // uDB.InsertarTelefono(usuario);  
                this.setMensaje("Inicio de Sesión incorrecto");
            }

        }
           crearSesion(id);
        return ruta;

    }

    public void ObtenerUsuario(String id) {
        try {
            UsuarioDAO rDB = new UsuarioDAO();
            Usuario objUsu = rDB.ObtenerUsuario(id);

            this.setId(objUsu.getId());
            this.setIdTipoUsuario(objUsu.getIdTipoUsuario());
            this.setNombre(objUsu.getNombre());
            this.setApellidos(objUsu.getApellidos());
            this.setClave(objUsu.getClave());
            this.setActivo(objUsu.getActivo());

        } catch (Exception e) {
            setMensaje(e.toString());
        }
    }

    public void borrarUsuario(String id) {

        try {
            UsuarioDAO rDB = new UsuarioDAO();
            Usuario usu = rDB.ObtenerUsuario(id);
            if (usu != null) {

                rDB.BorrarUsuario(usu);

                setMensaje("Usuario Eliminado Correctamente");

            }
          
        } catch (Exception e) {
            setMensaje(e.toString());
        }
    }

    public String nombreTipoUsuario(int id) {

        switch (id) {
            case 1:
                return "Administrador";
            case 2:
                return "Planillero";
            default:
                return "Recursos Humanos";
        }

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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public ArrayList<Usuario> getListaUsuarios() {
//        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            UsuarioDAO rDB = new UsuarioDAO();

//        for (Usuario usuario : lista) {
//            if (usuario.getActivo()) {
//
//            }
//        }
            listaUsuarios = rDB.VerTodos();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> lista) {
        this.listaUsuarios = lista;
    }

    public void limpiar() {
        setActivo("");
        setApellidos("");
        setNombre("");
        setClave("");
        setId("");
        setIdTipoUsuario(0);
        setMensaje("");
    }

    public void crearSesion(String id) throws SNMPExceptions, SQLException, IOException {
        UsuarioDAO rDB = new UsuarioDAO();
        Usuario objUser = rDB.ObtenerUsuario(id);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UsuarioLogin", objUser);
      
    }

    public Usuario consultarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UsuarioLogin");

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Usuario user = (Usuario) session.get("UsuarioLogin");

        if (user != null) {
            return user;

        } else {
            context.invalidateSession();

        }
        return null;

    }
    public void cambiarClave(){
        try {
            if(!clave.equals(apellidos)){
                  this.setMensaje("Claves no coinciden");
                  return;
            }
            if (!clave.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]?)(?=\\S+$).{8,12}$")) {
                setMensaje("Contraseña insegura");
                return;
            }
            UsuarioDAO rDB = new UsuarioDAO();
            Usuario user = consultarSesion();
            user.setClave(clave);
            rDB.cambiarClave(user);
            this.setMensaje("Clave cambiada con éxito");
        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
