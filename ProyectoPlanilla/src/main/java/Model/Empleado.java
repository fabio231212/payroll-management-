/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Antonio Ramirez
 */
public class Empleado {
    
    private String id;
    private String nombre;
    private String apellidos;
    private double salarioBruto;
    private int horaOrdinarias;
    private String activo;
    private double salarioHora;
    private int idTipoPlanilla;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    


    public int getIdTipoPlanilla() {
        return idTipoPlanilla;
    }

    public void setIdTipoPlanilla(int idTipoPlanilla) {
        this.idTipoPlanilla = idTipoPlanilla;
    }
    

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora;
    }
    public Empleado(String Id, String Nombre, String Apellidos, double SalarioBruto, int HoraOrdinarias, String Activo,double salarioHora,int idTipoPlanilla) {
        this.id = Id;
        this.nombre = Nombre;
        this.apellidos = Apellidos;
        this.salarioBruto = SalarioBruto;
        this.horaOrdinarias = HoraOrdinarias;
        this.activo = Activo;
        this.salarioHora = salarioHora;
        this.idTipoPlanilla = idTipoPlanilla;
    }

    public Empleado() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.apellidos = Apellidos;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double SalarioBruto) {
        this.salarioBruto = SalarioBruto;
    }

    public int getHoraOrdinarias() {
        return horaOrdinarias;
    }

    public void setHoraOrdinarias(int HoraOrdinarias) {
        this.horaOrdinarias = HoraOrdinarias;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String Activo) {
        this.activo = Activo;
    }
    

   
    
}
