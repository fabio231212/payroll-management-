/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ramir
 */
public class DetallePlanilla {
    private int id;
    private int idPlanilla;
    private String idEmpleado;
    private int idCatPago;
    private double salarioNeto;

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

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String IdEmpleado) {
        this.idEmpleado = IdEmpleado;
    }

    public int getIdCatPago() {
        return idCatPago;
    }

    public void setIdCatPago(int idCatPago) {
        this.idCatPago = idCatPago;
    }

    public double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }
    
    
}

