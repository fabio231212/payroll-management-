/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Empleado;
import Model.EmpleadoDeduccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ramir
 */
public class EmpleadoDeduccionDAO {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public void RegistrarEmpleadoDeduccion(EmpleadoDeduccion empDed)
            throws SNMPExceptions, SQLException {
        String strSQLEmp = "";

        try {
            //Se obtienen los valores del objeto

            strSQLEmp
                    = "INSERT INTO EmpleadoDeduccion(IdEmpleado, IdDeduccion, Porcentaje, Activo) VALUES "
                    + "(" + "'" + empDed.getIdEmpleado() + "'" + ","
                    + "'" + empDed.getIdDeduccion() + "'" + ","
                    + empDed.getPorcentaje() + " , 1)";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLEmp);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public EmpleadoDeduccion ObtenerEmpleadoDed(String id) {

        return null;
    }

    public void ActualizaEmpDed(EmpleadoDeduccion objEmpDed) {
    }

    public ArrayList<EmpleadoDeduccion> VerTodos()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<EmpleadoDeduccion> list = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM EMPLEADODEDUCCION WHERE ACTIVO = 1";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                EmpleadoDeduccion empleadoDeduccion = new EmpleadoDeduccion();
                empleadoDeduccion.setIdEmpleado(rsPA.getString("IdEmpleado"));
                empleadoDeduccion.setIdDeduccion(rsPA.getString("IdDeduccion"));
                empleadoDeduccion.setPorcentaje(rsPA.getDouble("Porcentaje"));
                list.add(empleadoDeduccion);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return list;
    }

    public ArrayList<EmpleadoDeduccion> dedPorIdEmp(String idEmpleado) throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<EmpleadoDeduccion> list = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "select * from EmpleadoDeduccion where IdEmpleado = " + idEmpleado + "and Activo = '1'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                EmpleadoDeduccion empleadoDeduccion = new EmpleadoDeduccion();
                empleadoDeduccion.setIdEmpleado(rsPA.getString("IdEmpleado"));
                empleadoDeduccion.setIdDeduccion(rsPA.getString("IdDeduccion"));
                empleadoDeduccion.setPorcentaje(rsPA.getDouble("Porcentaje"));
                list.add(empleadoDeduccion);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return list;
    }

    public void ActualizaEmpDed() throws SNMPExceptions, SQLException {
        String Query = "";

        try {

            Query = "UPDATE EmpleadoDeduccion SET Activo = 0";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
