/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author ramir
 */
public class Proceso {
    
      public LinkedList<SelectItem> getListaProceso() {

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem("Seleccione el tipo de proceso "));
        resultList.add(new SelectItem("Automático"));
        resultList.add(new SelectItem("Manual"));

        return resultList;
    }
    
}
