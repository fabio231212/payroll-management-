/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DeduccionDAO;
import DAO.SNMPExceptions;
import Model.Deduccion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author ramir
 */
public class BeanDeduccion {

    private String id;
    private String descripcion;
    private String proceso;
    private ArrayList<Deduccion> listaDeduccion;
    private LinkedList<Deduccion> listaLinkedDeduccion;
    String mensaje;
    
    public void limpiar(){
        setId("");
        setDescripcion("");
        setProceso("");
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Deduccion> getListaDeduccion() {
        try {
            DeduccionDAO rDB = new DeduccionDAO();

            listaDeduccion = rDB.VerTodos();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaDeduccion;
    }

    public LinkedList<SelectItem> getListaLinkedDeduccion() throws SNMPExceptions, SQLException {

            String id;
            String descripcion;
            String proceso;

            DeduccionDAO rDB = new DeduccionDAO();
            LinkedList<Deduccion> lista = new LinkedList<Deduccion>();

            lista = rDB.VerTodosLinked();
            LinkedList resultList = new LinkedList();
            resultList.add(new SelectItem(0, "Seleccione Deducción: "));

            for (Iterator iter = lista.iterator();
                    iter.hasNext();) {

                Deduccion objDeduccion = (Deduccion) iter.next();
                descripcion = objDeduccion.getDescripcion();
                id = objDeduccion.getId();
                proceso = objDeduccion.getProceso();
                resultList.add(new SelectItem(id, descripcion, proceso));
            }


        return resultList;
    }

    public void setListaDeduccion(ArrayList<Deduccion> listaDeduccion) {
        this.listaDeduccion = listaDeduccion;
    }

   

    public boolean registrarOActualizarDeduccion() {

        try {
            Deduccion deduccion = new Deduccion(id, descripcion, proceso);
            DeduccionDAO rDB = new DeduccionDAO();

            if (this.id.equals("") || this.descripcion.equals("")) {
                setMensaje("Campos Obligatorios");
            } else {

                if (rDB.ObtenerDeduccion(id) == null) {
                    rDB.RegistrarDeduccion(deduccion);
                      setMensaje("Deducción registrada correctamente");
                } else {
                    rDB.ActualizaDeduccion(deduccion);
                     setMensaje("Deducción Actualizada Correctamente");

                }
                limpiar();

            }

            return true;
        } catch (Exception e) {
             setMensaje(e.toString());
            return false;
        }

    }

    public void ObtenerDeduccion(String id) {
        try {
            DeduccionDAO rDB = new DeduccionDAO();
            Deduccion objUsu = rDB.ObtenerDeduccion(id);

            this.setId(objUsu.getId());
            this.setDescripcion(objUsu.getDescripcion());
            this.setProceso(objUsu.getProceso());

        } catch (Exception e) {
              setMensaje("Campos Obligatorios");
        }
    }

    public void borrarDeduccion(String id) {

        try {
            limpiar();
            DeduccionDAO rDB = new DeduccionDAO();
            Deduccion ded = rDB.ObtenerDeduccion(id);
            if (ded != null) {

                rDB.BorrarDeduccion(ded);

                  setMensaje("Deducción Eliminada Correctamente");

            }
        } catch (Exception e) {
              setMensaje(e.toString());
        }
    }

}
