/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CategoriasPagoDAO;
import DAO.DAOEmpleadoDestajo;
import DAO.DeduccionDAO;
import DAO.EmpleadoCategoriasPagoDAO;
import DAO.EmpleadoDeduccionDAO;
import DAO.PlanillaDAO;
import DAO.SNMPExceptions;
import Model.CategoriasPago;
import Model.DTO.DTODetallePlanilla;
import Model.Deduccion;
import Model.DetallePlanilla;
import Model.Empleado;
import Model.EmpleadoCategoriasPago;
import Model.EmpleadoDeduccion;
import Model.EmpleadoDestajo;
import Model.Planilla;
import Model.TipoPlanilla;
import Model.Turno;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
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
 * @author Alfredo
 */
public class BeanPlanilla {

    private int id;
    private int idTipo;
    private int idTurno;
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    ArrayList<DetallePlanilla> listaDetalle = new ArrayList<>();

    //Esto con cuidado
    public Date pInicio;
    public Date pFinal;
    public Date fPago;
    public String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    

    LinkedList<SelectItem> listaTipoP = new LinkedList<>();
    LinkedList<SelectItem> listaTurno = new LinkedList<>();
    
    public ArrayList<Planilla> listaEPlanilla;

    public ArrayList<Planilla> getListaEPlanilla() {
        try {
            PlanillaDAO rDB = new PlanillaDAO();

            listaEPlanilla = rDB.VerTodosEncabezados();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEPlanilla;
    }
    
    
    //Getters combos
    public LinkedList<SelectItem> getListaTipoP()
            throws SNMPExceptions, SQLException {
        String descripcion = "";
        int id = 0;

        LinkedList<TipoPlanilla> lista = new LinkedList<TipoPlanilla>();
        PlanillaDAO tpdDB = new PlanillaDAO();

        lista = tpdDB.verTiposPlanilla();

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

    public LinkedList<SelectItem> getListaTurno()
            throws SNMPExceptions, SQLException {
        String descripcion = "";
        int id = 0;

        LinkedList<Turno> lista = new LinkedList<Turno>();
        PlanillaDAO tpdDB = new PlanillaDAO();

        lista = tpdDB.verTurnos();

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0,
                "Seleccione Turno"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Turno tur = (Turno) iter.next();
            descripcion = tur.getDescripcion();
            id = tur.getId();
            resultList.add(new SelectItem(id, descripcion));
        }
        return resultList;
    }

    public void procesaPlanilla() throws SNMPExceptions, SQLException {
        listaDetalle = new ArrayList<>();
        EmpleadoDeduccionDAO daoEmpDed = new EmpleadoDeduccionDAO();
        EmpleadoCategoriasPagoDAO daoEmpCat = new EmpleadoCategoriasPagoDAO();
        CategoriasPagoDAO daoCat = new CategoriasPagoDAO();
        DeduccionDAO deduccionDao = new DeduccionDAO();
        ArrayList<Deduccion> listaDedAutomaticas = deduccionDao.DeduccionesAutomaticas();
        for (Empleado item : listaEmpleados) {
            ArrayList<EmpleadoDeduccion> listaEmpDed = daoEmpDed.dedPorIdEmp(item.getId());
            ArrayList<EmpleadoCategoriasPago> listaEmpCat = daoEmpCat.catPorIdEmp(item.getId());
            DetallePlanilla detallePlanilla = new DetallePlanilla();
            detallePlanilla.setIdEmpleado(item.getId());
            detallePlanilla.setIdPlanilla(id);
            double calculoDeducciones = 0;
            double calculoCategoria = 0;
            double salarioBruto = item.getSalarioBruto();
            for (EmpleadoDeduccion itemDedEmp : listaEmpDed) {
                calculoDeducciones += salarioBruto * itemDedEmp.getPorcentaje();
            }
            for (Deduccion itemDedAuto : listaDedAutomaticas) {
                calculoDeducciones += salarioBruto * itemDedAuto.getPorcentajeAutomatico();
            }

            for (EmpleadoCategoriasPago itemCatEmp : listaEmpCat) {
                double porcentajeCat = daoCat.ObtenerCat(itemCatEmp.getIdCategoriasPago()).getPorcentaje();
                calculoCategoria += (item.getSalarioHora() * porcentajeCat) * itemCatEmp.getCantHoras();
            }

            if (item.getSalarioBruto() > 863000 && item.getSalarioBruto() <= 1267000) {
                calculoDeducciones += salarioBruto * 0.10;
            } else if (item.getSalarioBruto() > 1267000 && item.getSalarioBruto() <= 2223000) {
                calculoDeducciones += salarioBruto * 0.15;
            } else if (item.getSalarioBruto() > 2223000 && item.getSalarioBruto() <= 4445000) {
                calculoDeducciones += salarioBruto * 0.20;
            } else if (item.getSalarioBruto() > 4445000) {
                calculoDeducciones += salarioBruto * 0.25;
            }
            double salarioNeto = item.getSalarioBruto() + calculoCategoria - calculoDeducciones;
            detallePlanilla.setSalarioNeto(salarioNeto);
            listaDetalle.add(detallePlanilla);
            daoEmpCat.ActualizaEmpCat();
            daoEmpDed.ActualizaEmpDed();
        }
    }

    public void procesaPlanillaDestajo() throws SNMPExceptions, SQLException {
        BeanEmpDestajo beanEmpDes = new BeanEmpDestajo();
        listaEmpleados = beanEmpDes.getListEmpleadoDestajo(2);
        listaDetalle = new ArrayList<>();
        EmpleadoDeduccionDAO daoEmpDed = new EmpleadoDeduccionDAO();
        EmpleadoCategoriasPagoDAO daoEmpCat = new EmpleadoCategoriasPagoDAO();
        CategoriasPagoDAO daoCat = new CategoriasPagoDAO();
        DeduccionDAO deduccionDao = new DeduccionDAO();
        DAOEmpleadoDestajo EmpDesDao = new DAOEmpleadoDestajo();
        ArrayList<EmpleadoDestajo> listaEmpDestajo = EmpDesDao.VerTodos();
        ArrayList<Deduccion> listaDedAutomaticas = deduccionDao.DeduccionesAutomaticas();
        for (Empleado item : listaEmpleados) {
            ArrayList<EmpleadoDeduccion> listaEmpDed = daoEmpDed.dedPorIdEmp(item.getId());
            ArrayList<EmpleadoCategoriasPago> listaEmpCat = daoEmpCat.catPorIdEmp(item.getId());
            DetallePlanilla detallePlanilla = new DetallePlanilla();
            detallePlanilla.setIdEmpleado(item.getId());
            detallePlanilla.setIdPlanilla(id);
            double calculoDeducciones = 0;
            double calculoCategoria = 0;
            double salarioBruto = 0;
            for (EmpleadoDestajo itemEmpDes : listaEmpDestajo) {
                if (item.getId().equalsIgnoreCase(itemEmpDes.getIdEmpleado())) {
                    salarioBruto = itemEmpDes.getTotal();
                }
            }

            for (EmpleadoDeduccion itemDedEmp : listaEmpDed) {
                calculoDeducciones += salarioBruto * itemDedEmp.getPorcentaje();
            }
            for (Deduccion itemDedAuto : listaDedAutomaticas) {
                calculoDeducciones += salarioBruto * itemDedAuto.getPorcentajeAutomatico();
            }

            for (EmpleadoCategoriasPago itemCatEmp : listaEmpCat) {
                double porcentajeCat = daoCat.ObtenerCat(itemCatEmp.getIdCategoriasPago()).getPorcentaje();
                calculoCategoria += (item.getSalarioHora() * porcentajeCat) * itemCatEmp.getCantHoras();
            }

            if (item.getSalarioBruto() > 863000 && item.getSalarioBruto() <= 1267000) {
                calculoDeducciones += salarioBruto * 0.10;
            } else if (item.getSalarioBruto() > 1267000 && item.getSalarioBruto() <= 2223000) {
                calculoDeducciones += salarioBruto * 0.15;
            } else if (item.getSalarioBruto() > 2223000 && item.getSalarioBruto() <= 4445000) {
                calculoDeducciones += salarioBruto * 0.20;
            } else if (item.getSalarioBruto() > 4445000) {
                calculoDeducciones += salarioBruto * 0.25;
            }
            double salarioNeto = salarioBruto + calculoCategoria - calculoDeducciones;
            detallePlanilla.setSalarioNeto(salarioNeto);
            listaDetalle.add(detallePlanilla);
        }
    }

    public boolean Insertar() {
        try {
            EmpleadoDeduccionDAO daoEmpDed = new EmpleadoDeduccionDAO();
            EmpleadoCategoriasPagoDAO daoEmpCat = new EmpleadoCategoriasPagoDAO();
            Planilla pla = new Planilla(id, idTipo, idTurno, pInicio, pFinal);
            if (idTipo == 2) {
                procesaPlanillaDestajo();
            } else {
                procesaPlanilla();
            }

            pla.setListaDetalle(listaDetalle);
            PlanillaDAO rDB = new PlanillaDAO();

            if (this.idTipo <= 0 || this.idTurno <= 0 || this.pInicio == null || this.pFinal == null) {
                setMensaje("Campos Obligatorios");

            } else {

                if (rDB.ObtenerPlanilla(id) == null) {
                    rDB.InsertarPlanilla(pla);
                    daoEmpCat.ActualizaEmpCat();
                    daoEmpDed.ActualizaEmpDed();
                    setMensaje("Planilla Registrada Correctamente");
                } else {
                   setMensaje("Planilla existente");

                }

            }
            limpiar();

            return true;
        } catch (Exception e) {
           setMensaje(e.toString());
            return false;
        }
    }

    //resto de getters y setters
    public int getId() {
        return id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public Date getpInicio() {
        return pInicio;
    }

    public Date getpFinal() {
        return pFinal;
    }

    public Date getfPago() {
        return fPago;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public void setpInicio(Date pInicio) {
        this.pInicio = pInicio;
    }

    public void setpFinal(Date pFinal) {
        this.pFinal = pFinal;
    }

    public void setfPago(Date fPago) {
        this.fPago = fPago;
    }

    public void setListaTipoP(LinkedList<SelectItem> listaTipoP) {
        this.listaTipoP = listaTipoP;
    }

    public void setListaTurno(LinkedList<SelectItem> listaTurno) {
        this.listaTurno = listaTurno;
    }

    public ArrayList<Empleado> getListaEmpleados(String id) {
        BeanEmpleado beanEmp = new BeanEmpleado();
        listaEmpleados = beanEmp.getListEmpleadoByIdTipo(idTipo);
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public ArrayList<DetallePlanilla> getListaDetalle() {
         try {
            PlanillaDAO rDB = new PlanillaDAO();

            listaDetalle = rDB.VerTodosDetalles();

        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaDetalle;
    }

    public void setListaDetalle(ArrayList<DetallePlanilla> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    

    public void setListaEPlanilla(ArrayList<Planilla> listaEPlanilla) {
        this.listaEPlanilla = listaEPlanilla;
    }
    
    public void verPDF(int id) throws JRException, IOException {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Planilla.jasper"));
            
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JRBeanCollectionDataSource(getPlanillaById(id)));
            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            
            ServletOutputStream stream = response.getOutputStream();
            stream.write(bytes, 0, bytes.length);
            stream.flush();
            stream.close();
            
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ExportarPDF(int id) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Planilla.jasper"));
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getPlanillaById(id)));
        } catch (SQLException ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BeanPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Planilla.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        FacesContext.getCurrentInstance().responseComplete();

    }
    
   

    
    public ArrayList<DTODetallePlanilla>  getPlanillaById(int id) throws SQLException, SNMPExceptions, NamingException, ClassNotFoundException{
        PlanillaDAO planilaDao = new PlanillaDAO();
        return planilaDao.getPlanillaById(id);
    }

    public void limpiar(){
        setId(0);
        setIdTipo(0);
        setIdTurno(0);
        setfPago(null);
        setpInicio(null);
        setpFinal(null);
    }
    
}
