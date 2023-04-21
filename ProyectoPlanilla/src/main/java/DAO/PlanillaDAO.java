/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CategoriasPago;
import Model.DTO.DTODetallePlanilla;
import Model.DetallePlanilla;
import Model.Planilla;
import Model.TipoPlanilla;
import Model.TipoUsuario;
import Model.Turno;
import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author Alfredo
 */
public class PlanillaDAO {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public PlanillaDAO() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public LinkedList<TipoPlanilla> tipoPlanillaEmpleados()
            throws SNMPExceptions, SQLException {
        String Query = "";
        LinkedList<TipoPlanilla> listaTipos = new LinkedList();

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM TipoPlanilla";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                TipoPlanilla tipo = new TipoPlanilla();
                tipo.setId(rsPA.getInt("Id"));
                tipo.setDescripcion(rsPA.getString("Descripcion"));

                listaTipos.add(tipo);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaTipos;
    }

    public LinkedList<TipoPlanilla> verTiposPlanilla()
            throws SNMPExceptions, SQLException {
        String Query = "";
        LinkedList<TipoPlanilla> listaTipos = new LinkedList();

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM TipoPlanilla";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                TipoPlanilla tipo = new TipoPlanilla();
                tipo.setId(rsPA.getInt("Id"));
                tipo.setDescripcion(rsPA.getString("Descripcion"));
                if (!tipo.getDescripcion().equals("Destajo")) {
                    listaTipos.add(tipo);
                }
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaTipos;
    }

    public LinkedList<Turno> verTurnos()
            throws SNMPExceptions, SQLException {
        String Query = "";
        LinkedList<Turno> listaTurnos = new LinkedList();

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM Turno";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                Turno t = new Turno();
                t.setId(rsPA.getInt("Id"));
                t.setDescripcion(rsPA.getString("Descripcion"));

                listaTurnos.add(t);

            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaTurnos;
    }

    public void InsertarPlanilla(Planilla p)
            throws SNMPExceptions, SQLException {
        String queryEncPlanilla = "";
        String queryDetPlanilla = "";
        try {
            //Se obtienen los valores del objeto

            queryEncPlanilla
                    = "INSERT INTO EncabezadoPlanilla VALUES ("
                    + p.getId() + ","
                    + p.getIdTipo() + ","
                    + p.getIdTurno() + ","
                    + "'" + new java.sql.Date(p.getpInicio().getTime()) + "'" + ","
                    + "'" + new java.sql.Date(p.getpFinal().getTime()) + "'" + ","
                    + " GETDATE());";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(queryEncPlanilla);

            for (DetallePlanilla item : p.getListaDetalle()) {
                queryDetPlanilla
                        = "INSERT INTO DetallePlanilla VALUES ("
                        + item.getIdPlanilla() + ","
                        + item.getIdEmpleado() + ","
                        + item.getIdCatPago() + ","
                        + item.getSalarioNeto() + ")";
                accesoDatos.ejecutaSQL(queryDetPlanilla);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public Planilla ObtenerPlanilla(int id)
            throws SNMPExceptions, SQLException {
        String Query = "";
        Planilla pla = null;

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM EncabezadoPlanilla WHERE Id = " + id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                pla = new Planilla();
                pla.setId(rsPA.getInt("Id"));
                pla.setIdTipo(rsPA.getInt("IdTipoPlanilla"));
                pla.setIdTurno(rsPA.getInt("IdTurno"));
                pla.setpInicio(rsPA.getDate("PagoInicio"));
                pla.setpFinal(rsPA.getDate("PagoFinal"));
                pla.setfPago(rsPA.getDate("FechaPago"));
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return pla;
    }
    
    public ArrayList<Planilla> VerTodosEncabezados()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<Planilla> listaEP = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM EncabezadoPlanilla";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {
                
                Planilla pla = new Planilla();
                pla.setId(rsPA.getInt("Id"));
                pla.setIdTipo(rsPA.getInt("IdTipoPlanilla"));
                pla.setIdTurno(rsPA.getInt("IdTurno"));
                pla.setpInicio(rsPA.getDate("PagoInicio"));
                pla.setpFinal(rsPA.getDate("PagoFinal"));
                pla.setfPago(rsPA.getDate("FechaPago"));

                listaEP.add(pla);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaEP;
    }
    
     public ArrayList<DetallePlanilla> VerTodosDetalles()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<DetallePlanilla> listaDP = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM DetallePlanilla";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {
                
                DetallePlanilla pla = new DetallePlanilla();
                pla.setId(rsPA.getInt("Id"));
                pla.setIdPlanilla(rsPA.getInt("IdEncabezado"));
                pla.setIdEmpleado(String.valueOf(rsPA.getInt("IdEmpleado")));
                pla.setIdCatPago(rsPA.getInt("IdCategoriaPago"));
                pla.setSalarioNeto(rsPA.getDouble("SalarioNeto"));

                listaDP.add(pla);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaDP;
    }
     
     public ArrayList<DTODetallePlanilla> getPlanillaById(int id) throws SQLException, SNMPExceptions, NamingException, ClassNotFoundException{ {
        String Query = "";
        ArrayList<DTODetallePlanilla> listaDetalle = new ArrayList<>();
             
        try {
            //Se obtienen los valores del objeto

            Query = "select EncabezadoPlanilla.Id,EncabezadoPlanilla.PagoInicio,EncabezadoPlanilla.PagoFinal,Turno.Descripcion[TurnoDescripcion],TipoPlanilla.Descripcion[TipoPlanillaDescripcion],DetallePlanilla.IdEmpleado,\n" +
"DetallePlanilla.SalarioNeto from EncabezadoPlanilla inner join Turno on EncabezadoPlanilla.IdTurno = Turno.Id inner join TipoPlanilla on TipoPlanilla.Id = EncabezadoPlanilla.IdTipoPlanilla\n" +
"inner join DetallePlanilla on DetallePlanilla.IdEncabezado = EncabezadoPlanilla.Id where EncabezadoPlanilla.Id = " + id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {
                DTODetallePlanilla dtoDetalle = new DTODetallePlanilla();
                dtoDetalle.setId(rsPA.getString("Id"));
                dtoDetalle.setDescripcionTurno(rsPA.getString("TurnoDescripcion"));
                dtoDetalle.setDescripcionTipoP(rsPA.getString("TipoPlanillaDescripcion"));
                dtoDetalle.setPagoInicio(rsPA.getString("PagoInicio"));
                dtoDetalle.setPagoFinal(rsPA.getString("PagoFinal"));      
                dtoDetalle.setIdEmpleado(rsPA.getString("IdEmpleado"));
                dtoDetalle.setSalarioNeto(rsPA.getDouble("SalarioNeto"));
                listaDetalle.add(dtoDetalle);
            }
                

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaDetalle;
     }
     }
}

