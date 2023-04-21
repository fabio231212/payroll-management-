/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

/**
 *
 * @author ramir
 */
public class DTODetallePlanilla {
    private String idEmpleado;
    private double salarioNeto;
      private String id;
    private String pagoInicio;
    private String pagoFinal;
    private String descripcionTipoP;
     private String descripcionTurno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPagoInicio() {
        return pagoInicio;
    }

    public void setPagoInicio(String pagoInicio) {
        this.pagoInicio = pagoInicio;
    }

    public String getPagoFinal() {
        return pagoFinal;
    }

    public void setPagoFinal(String pagoFinal) {
        this.pagoFinal = pagoFinal;
    }

    public String getDescripcionTipoP() {
        return descripcionTipoP;
    }

    public void setDescripcionTipoP(String descripcionTipoP) {
        this.descripcionTipoP = descripcionTipoP;
    }

    public String getDescripcionTurno() {
        return descripcionTurno;
    }

    public void setDescripcionTurno(String descripcionTurno) {
        this.descripcionTurno = descripcionTurno;
    }
     

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }
    
    
    
}
