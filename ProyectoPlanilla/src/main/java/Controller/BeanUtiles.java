/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Activo;
import Model.Proceso;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Sebastian
 */
public class BeanUtiles {

    LinkedList<Activo> listaA = new LinkedList<>();
     LinkedList<Proceso> listaProceso= new LinkedList<>();
    String descripcionEstado = "";
    int idEstado = 0;

    public void setListaA(LinkedList<Activo> listaA) {
        this.listaA = listaA;
    }

    public LinkedList<SelectItem> getListaA() {

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem("Seleccione estado: "));
        resultList.add(new SelectItem("Inactivo"));
        resultList.add(new SelectItem("Activo"));

        return resultList;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public LinkedList<SelectItem> getListaProceso() {
        Proceso proceso = new  Proceso();
        return proceso.getListaProceso();
        
    }
    
    

}
