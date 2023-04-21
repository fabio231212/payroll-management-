/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.CategoriasPagoDAO;
import DAO.EmpleadoDAL;
import DAO.SNMPExceptions;
import Model.CategoriasPago;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Sebastian
 */
public class BeanCategoriasPago {

    private String id;
    private String descripcion;
    private double porcentaje;
    private LinkedList<CategoriasPago> listaLinkedCategorias;
    public ArrayList<CategoriasPago> listaCategorias;

    public ArrayList<CategoriasPago> getListaCategorias() {
        try {
            CategoriasPagoDAO rDB = new CategoriasPagoDAO();

            listaCategorias = rDB.VerTodos();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaCategorias;
    }
    
    public LinkedList<CategoriasPago> getListaLinkedCategorias() throws SNMPExceptions, SQLException {
            String id;
            String descripcion;
            double porcentaje;

            CategoriasPagoDAO rDB = new CategoriasPagoDAO();
            LinkedList<CategoriasPago> lista = new LinkedList<CategoriasPago>();

            lista = rDB.VerTodosLinked();
            LinkedList resultList = new LinkedList();
            resultList.add(new SelectItem(0, "Seleccione Categoría: "));

            for (Iterator iter = lista.iterator();
                    iter.hasNext();) {

                CategoriasPago objCategoria = (CategoriasPago) iter.next();
                descripcion = objCategoria.getDescripcion();
                id = objCategoria.getId();
                porcentaje = objCategoria.getPorcentaje();
                resultList.add(new SelectItem(id));
            }


        return resultList;
    }

    public void setListaLinkedCategorias(LinkedList<CategoriasPago> listaLinkedCategorias) {
        this.listaLinkedCategorias = listaLinkedCategorias;
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

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    

    public void setListaCategorias(ArrayList<CategoriasPago> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    public void verPDF() throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/CategoriasPago.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getListaCategorias()));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);

        ServletOutputStream stream = response.getOutputStream();
        stream.write(bytes, 0, bytes.length);
        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void ExportarPDF() throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/CategoriasPago.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getListaCategorias()));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=CPagos.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        FacesContext.getCurrentInstance().responseComplete();

    }
}
