/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ramir
 */
public class EmpleadoDeduccion {
    private String id;

    private String idEmpleado;
    private String idDeduccion;
    private double porcentaje;

    public EmpleadoDeduccion(String idEmpleado, String idDeduccion, double porcentaje) {
        this.idEmpleado = idEmpleado;
        this.idDeduccion = idDeduccion;
        this.porcentaje = porcentaje;
    }

    public EmpleadoDeduccion() {
    }

        public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
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
    
    
}
