/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOEmpleadoDestajo;
import DAO.EmpleadoDAL;
import DAO.SNMPExceptions;
import Model.Empleado;
import Model.EmpleadoDestajo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramir
 */
public class BeanEmpDestajo {

    private int id;
    //  private String idEmpleado;
    // private int cantidad;
    private String descripcion;
    private double pagoUnidad;
    private double total;
    private String activo;
    private int idPlanilla;
    private int idTurno;
    public Date pInicio;
    public Date pFinal;
    ArrayList<Empleado> listEmpleadoDestajo;
    public String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public Date getpInicio() {
        return pInicio;
    }

    public void setpInicio(Date pInicio) {
        this.pInicio = pInicio;
    }

    public Date getpFinal() {
        return pFinal;
    }

    public void setpFinal(Date pFinal) {
        this.pFinal = pFinal;
    }

    
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPagoUnidad() {
        return pagoUnidad;
    }

    public void setPagoUnidad(double pagoUnidad) {
        this.pagoUnidad = pagoUnidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public ArrayList<Empleado> getListEmpleadoDestajo(int idTipoPlanilla) {
        try {
            EmpleadoDAL rDB = new EmpleadoDAL();

//        for (Usuario usuario : lista) {
//            if (usuario.getActivo()) {
//
//            }
//        }
            listEmpleadoDestajo = rDB.empleadoByIdTipoPlanilla(idTipoPlanilla);

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEmpleadoDestajo;
    }

    public void insertarEmpDestajo() throws SNMPExceptions, SQLException {
        DAOEmpleadoDestajo daoEmpDestajo = new DAOEmpleadoDestajo();
        BeanPlanilla beanP = new BeanPlanilla();
        beanP.setIdTipo(2);
        beanP.setIdTurno(idTurno);
        beanP.setId(idPlanilla);
        beanP.setpFinal(pFinal);
        beanP.setpInicio(pInicio);
        ArrayList<EmpleadoDestajo> listEmpDestajo = new ArrayList();
        for (Empleado item : listEmpleadoDestajo) {
            double total = 0;
            EmpleadoDestajo empDes = new EmpleadoDestajo();
            empDes.setIdEmpleado(item.getId());
            empDes.setDescripcion(descripcion);
            empDes.setPagoUnidad(pagoUnidad);
            empDes.setCantidad(item.getCantidad());
            empDes.setActivo(item.getActivo());
            total = pagoUnidad * empDes.getCantidad();
            empDes.setTotal(total);
            listEmpDestajo.add(empDes);

        }

        daoEmpDestajo.RegistrarEmpleado(listEmpDestajo);
        beanP.Insertar();
        this.setMensaje("Planilla destajo creada correctamente");
        limpiarCampos();

    }

    public void limpiarCampos() {
        setIdPlanilla(0);
        setPagoUnidad(0);
        setDescripcion("");
        setPagoUnidad(0);
        setpFinal(null);
        setpInicio(null);
        
    }

}
