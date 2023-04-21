/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author Alfredo
 */
public class Planilla {
    private int id, idTipo, idTurno;
    private Date pInicio, pFinal, fPago;
    private ArrayList<DetallePlanilla> listaDetalle = new ArrayList<>();
    
    

    public Planilla() {
    }

    
    public Planilla(int id, int idTipo, int idTurno, Date pInicio, Date pFinal) {
        this.id = id;
        this.idTipo = idTipo;
        this.idTurno = idTurno;
        this.pInicio = pInicio;
        this.pFinal = pFinal;
    }

    public ArrayList<DetallePlanilla> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(ArrayList<DetallePlanilla> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public Planilla(int id, int idTipo, int idTurno, Date pInicio, Date pFinal, Date fPago) {
        this.id = id;
        this.idTipo = idTipo;
        this.idTurno = idTurno;
        this.pInicio = pInicio;
        this.pFinal = pFinal;
        this.fPago = fPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
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

    public Date getfPago() {
        return fPago;
    }

    public void setfPago(Date fPago) {
        this.fPago = fPago;
    }
    
    
}
