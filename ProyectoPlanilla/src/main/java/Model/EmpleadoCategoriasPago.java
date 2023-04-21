/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sebastian
 */
public class EmpleadoCategoriasPago {

    private int id;
    private String idEmpleado;
    private String idCategoriasPago;
    private int cantHoras;
    private String Activo;

    public EmpleadoCategoriasPago(String idEmpleado, String idCategoriasPago, int cantHoras) {
        this.idEmpleado = idEmpleado;
        this.idCategoriasPago = idCategoriasPago;
        this.cantHoras = cantHoras;
    }

    public EmpleadoCategoriasPago() {
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
