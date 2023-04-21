/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ramir
 */
public class Deduccion {
    private String id;
    private String descripcion;
    private String proceso;
    private double porcentajeAutomatico;

    public double getPorcentajeAutomatico() {
        return porcentajeAutomatico;
    }

    public void setPorcentajeAutomatico(double porcentajeAutomatico) {
        this.porcentajeAutomatico = porcentajeAutomatico;
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

    public Deduccion(String id, String descripcion,String proceso) {
        this.id = id;
        this.descripcion = descripcion;
        this.proceso = proceso;
    }

    public Deduccion() {
    }
    
}
