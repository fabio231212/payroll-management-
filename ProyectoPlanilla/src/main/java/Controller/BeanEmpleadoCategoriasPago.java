/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.EmpleadoCategoriasPagoDAO;
import DAO.SNMPExceptions;
import Model.EmpleadoCategoriasPago;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sebastian
 */
public class BeanEmpleadoCategoriasPago {
    
    private int id;
    private String idEmpleado;
    private String idCategoriasPago;
    private int cantHoras;
    private String Activo;
    private ArrayList<EmpleadoCategoriasPago> lista;
    public String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    public void LimpiarDatos() {
        setIdCategoriasPago("");
        setIdEmpleado("");
        setCantHoras(0);
    }
    
    public ArrayList<EmpleadoCategoriasPago> getLista() {
        try {
            EmpleadoCategoriasPagoDAO rDB = new EmpleadoCategoriasPagoDAO();
            lista = rDB.VerTodos();
            
        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    
    
    public boolean RegistrarOActualizarCat(String idEmp) {

        setIdEmpleado(idEmp);

        try {
            EmpleadoCategoriasPago objEmpCat = new EmpleadoCategoriasPago(idEmpleado, idCategoriasPago, cantHoras);
           
            EmpleadoCategoriasPagoDAO rDB = new EmpleadoCategoriasPagoDAO();

            if (this.idEmpleado.equals("") || this.idCategoriasPago.equals("") || this.cantHoras == 0) {
                setMensaje("Campos Obligatorios");
            } else {

                if (rDB.ObtenerEmpleadoDed(id) == null) {
                    rDB.RegistrarEmpleadoCategoria(objEmpCat);
                    setMensaje("Categoría Guardada Correctamente");
                } else {
                    rDB.ActualizaEmpCat();
                   setMensaje("Categoría Actualizada Correctamente");

                }
                LimpiarDatos();

            }

            return true;
        } catch (Exception e) {
             setMensaje(e.toString());
            return false;
        }

    }
    
    public void setLista(ArrayList<EmpleadoCategoriasPago> lista) {
        this.lista = lista;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public String getIdCategoriasPago() {
        return idCategoriasPago;
    }
    
    public void setIdCategoriasPago(String idCategoriasPago) {
        this.idCategoriasPago = idCategoriasPago;
    }
    
    public int getCantHoras() {
        return cantHoras;
    }
    
    public void setCantHoras(int cantHoras) {
        this.cantHoras = cantHoras;
    }
    
    public String getActivo() {
        return Activo;
    }
    
    public void setActivo(String Activo) {
        this.Activo = Activo;
    }
    
}
