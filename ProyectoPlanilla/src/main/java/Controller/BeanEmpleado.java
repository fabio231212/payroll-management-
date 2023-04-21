/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.EmpleadoDAL;
import DAO.PlanillaDAO;
import DAO.SNMPExceptions;
import Model.Empleado;
import Model.TipoPlanilla;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
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
 * @author Antonio Ramirez
 */
public class BeanEmpleado {

    private String id;
    private String nombre;
    private String apellidos;
    private double salarioBruto;
    private int horaOrdinarias;
    private String activo;
    private double salarioHora;
    private int idTipoPlanilla;
    ArrayList<Empleado> listEmpleado;
    LinkedList<SelectItem> listaTipoP = new LinkedList<>();
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LinkedList<SelectItem> getListaTipoP() throws SNMPExceptions, SQLException {
        String descripcion = "";
        int id = 0;

        LinkedList<TipoPlanilla> lista = new LinkedList<TipoPlanilla>();
        PlanillaDAO tpdDB = new PlanillaDAO();

        lista = tpdDB.tipoPlanillaEmpleados();

        LinkedList resultList = new LinkedList();

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            TipoPlanilla tipoP = (TipoPlanilla) iter.next();
            descripcion = tipoP.getDescripcion();
            id = tipoP.getId();
            resultList.add(new SelectItem(id, descripcion));
        }
        return resultList;
    }

    public void setListaTipoP(LinkedList<SelectItem> listaTipoP) {
        this.listaTipoP = listaTipoP;
    }

    public int getIdTipoPlanilla() {
        return idTipoPlanilla;
    }

    public void setIdTipoPlanilla(int idTipoPlanilla) {
        this.idTipoPlanilla = idTipoPlanilla;
    }

    public ArrayList<Empleado> getListEmpleado() {
        try {
            EmpleadoDAL rDB = new EmpleadoDAL();
            int idTipoPlanilla;

            listEmpleado = rDB.VerTodos();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEmpleado;
    }

    public void limpiarDatos() {
        setId("");
        setNombre("");
        setApellidos("");
        setSalarioBruto(0);
        setHoraOrdinarias(0);
        setActivo("");
        setMensaje("");
    }

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora;
    }

    public boolean registrarOActualizarEmpleado() {

        try {

            if (this.id.equals("") || this.nombre.equals("") || this.apellidos.equals("") || this.horaOrdinarias == (0)  || this.activo.equals("")) {

                setMensaje("Campos obligatorios");

            } else {
                if (idTipoPlanilla == 2) {
                    setSalarioBruto(0);
                    setSalarioHora(0);
                    setHoraOrdinarias(0);
                }
                salarioBruto = horaOrdinarias * salarioHora;
                Empleado emp = new Empleado(id, nombre, apellidos, salarioBruto, horaOrdinarias, activo, salarioHora, idTipoPlanilla);
                EmpleadoDAL rDB = new EmpleadoDAL();

                if (rDB.ObtenerEmpleado(id) == null) {
                    rDB.RegistrarEmpleado(emp);
                    setMensaje("Usuario registrado correctamente");

                } else {
                    rDB.ActualizarEmpleado(emp);

                    setMensaje("Usuario actualizado correctamente");

                }

            }

            return true;
        } catch (Exception e) {
//            FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Error: " + e.getMessage(), null);
//            FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);
            setMensaje(e.toString());
            return false;
        }

    }

    public void ObtenerEmpleado(String id) {
        try {
            EmpleadoDAL rDB = new EmpleadoDAL();
            Empleado objUsu = rDB.ObtenerEmpleado(id);

            this.setId(objUsu.getId());
            this.setNombre(objUsu.getNombre());
            this.setApellidos(objUsu.getApellidos());
            this.setHoraOrdinarias(objUsu.getHoraOrdinarias());
            this.setSalarioBruto(objUsu.getSalarioBruto());
            this.setActivo(objUsu.getActivo());
            this.setSalarioHora(objUsu.getSalarioHora());

        } catch (Exception e) {
            setMensaje(e.toString());
        }
    }

    public void borrarEmpleado(String idE) {

        try {
            EmpleadoDAL rDB = new EmpleadoDAL();
            Empleado emp = rDB.ObtenerEmpleado(idE);
            if (emp != null) {

                rDB.BorrarEmpleado(emp);

                FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Empleado Eliminado!", null);
                FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);

            }
        } catch (Exception e) {
            setMensaje(e.toString());
        }
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

    public ArrayList<Empleado> getListEmpleadoByIdTipo(int idTipoPlanilla) {
        try {
            EmpleadoDAL rDB = new EmpleadoDAL();

//        for (Usuario usuario : lista) {
//            if (usuario.getActivo()) {
//
//            }
//        }
            listEmpleado = rDB.empleadoByIdTipoPlanilla(idTipoPlanilla);

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEmpleado;
    }

    public void setListEmpleado(ArrayList<Empleado> listEmpleado) {
        this.listEmpleado = listEmpleado;
    }

    private void Activo(String activo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void crearSesion(String id, String tipo) throws SNMPExceptions, SQLException, IOException {
        EmpleadoDAL rDB = new EmpleadoDAL();
        Empleado objEmp = rDB.ObtenerEmpleado(id);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Empleado", objEmp);
        if (tipo.equals("deduccion")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("addDeducciones.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("addCategoriasPago.xhtml");
        }

    }

    public void consultarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Empleado");

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Empleado empleado = (Empleado) session.get("Empleado");

        if (empleado != null) {
            try {
                setId(empleado.getId());
                setNombre(empleado.getNombre());
                setApellidos(empleado.getApellidos());
                setHoraOrdinarias(empleado.getHoraOrdinarias());
                setActivo(empleado.getActivo());
                setSalarioBruto(empleado.getSalarioBruto());

            } catch (ClassCastException e) {

            }
        } else {
            context.invalidateSession();

        }

    }

    public void verPDF() throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Empleados.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getListEmpleado()));

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
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Empleados.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getListEmpleado()));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=empleados.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        FacesContext.getCurrentInstance().responseComplete();

    }

}
