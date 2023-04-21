package Controller;

import DAO.SNMPExceptions;
import DAO.TipoUsuarioDAO;
import Model.TipoUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class BeanTipoU {

    String Id;
    String descripcion;
    ArrayList<TipoUsuario> listaTipos;
    LinkedList<TipoUsuario> listaTiposLinked;
    public String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    

    public ArrayList<TipoUsuario> getListaTipos() {
        try {
            TipoUsuarioDAO rDB = new TipoUsuarioDAO();
            listaTipos = rDB.VerTodos();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaTipos;
    }

        public LinkedList<SelectItem> getListaLinked()
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
    
    public boolean registrarOActualizarTipo() {
        try {
            TipoUsuario tipo = new TipoUsuario(Integer.parseInt(Id), descripcion);
            TipoUsuarioDAO rDB = new TipoUsuarioDAO();

            if (this.Id.equals("") || this.descripcion.equals("")) {
                setMensaje("Campos Obligatorios");

            } else {

                if (rDB.ObtenerTipo(Id) == null) {
                    rDB.RegistrarTipo(tipo);
                   setMensaje("Tipo de Usuario insertado correctamente");
                } else {
                    rDB.ActualizarTipo(tipo);
                    setMensaje("Tipo de Usuario Actualizado Correctamente");
                }
                limpiar();

            }
            return true;
        } catch (Exception e) {
            setMensaje(e.toString());
            return false;
        }
    }

    public void ObtenerTipo(String id) {
        try {
            TipoUsuarioDAO rDB = new TipoUsuarioDAO();
            TipoUsuario objTipo = rDB.ObtenerTipo(id);

            this.setId(String.valueOf(objTipo.getId()));
            this.setDescripcion(objTipo.getDescripcion());

        } catch (Exception e) {
            setMensaje(e.toString());
        }
    }

    public void borrarTipo(String id) {

        try {
            TipoUsuarioDAO rDB = new TipoUsuarioDAO();
            TipoUsuario tipo = rDB.ObtenerTipo(id);
            if (tipo != null) {

                rDB.BorrarTipo(tipo);

                setMensaje("Usuario Eliminado Correctamente");

            }
        } catch (Exception e) {
             setMensaje(e.toString());
        }
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setListaTipos(ArrayList<TipoUsuario> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public void setListaTiposLinked(LinkedList<TipoUsuario> listaTiposLinked) {
        this.listaTiposLinked = listaTiposLinked;
    }
    public void limpiar(){
        setId("");
        setDescripcion("");

    }

}
