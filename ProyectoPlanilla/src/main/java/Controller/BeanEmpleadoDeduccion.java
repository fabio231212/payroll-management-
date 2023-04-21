/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.EmpleadoDAL;
import DAO.EmpleadoDeduccionDAO;
import DAO.SNMPExceptions;
import Model.Empleado;
import Model.EmpleadoDeduccion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ramir
 */
public class BeanEmpleadoDeduccion {

    private String id;
    private String idEmpleado;
    private String idDeduccion;
    private double porcentaje;
    private ArrayList<EmpleadoDeduccion> lista;

    public ArrayList<EmpleadoDeduccion> getLista() {
            try {
            EmpleadoDeduccionDAO rDB = new EmpleadoDeduccionDAO();
            lista = rDB.VerTodos();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    

    public void setLista(ArrayList<EmpleadoDeduccion> lista) {
        this.lista = lista;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdDeduccion() {
        return idDeduccion;
    }

    public void setIdDeduccion(String idDeduccion) {
        this.idDeduccion = idDeduccion;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    
    public void LimpiarDatos(){
        setId("");
        setIdDeduccion("");
        setIdEmpleado("");
        setPorcentaje(0);
    }

    public boolean RegistrarOActualizarDeduccion(String idEmp, String idDed) {

        setIdDeduccion(idDed);
        setIdEmpleado(idEmp);
        
        try {
            EmpleadoDeduccion objEmpDed = new EmpleadoDeduccion(idEmpleado, idDeduccion, porcentaje);
            EmpleadoDeduccionDAO rDB = new EmpleadoDeduccionDAO();

            if (this.idEmpleado.equals("") || this.idDeduccion.equals("") || this.porcentaje == 0) {
                FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Campos Obligatorios!", null);
                FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);
            } else {

                if (rDB.ObtenerEmpleadoDed(id) == null) {
                    rDB.RegistrarEmpleadoDeduccion(objEmpDed);
                    FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Deducción registrada correctamente", null);
                    FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);
                } else {
                    rDB.ActualizaEmpDed(objEmpDed);
                    FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Deducción actualizada Correctamente", null);
                    FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);

                }
                LimpiarDatos();

            }

            return true;
        } catch (Exception e) {
            FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);
            return false;
        }

    }
}

