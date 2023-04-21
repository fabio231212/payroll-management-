/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.EmpleadoCategoriasPago;
import Model.EmpleadoDeduccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class EmpleadoCategoriasPagoDAO {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public void RegistrarEmpleadoCategoria(EmpleadoCategoriasPago empCat)
            throws SNMPExceptions, SQLException {
        String strSQLEmp = "";

        try {
            //Se obtienen los valores del objeto

            strSQLEmp
                    = "INSERT INTO EmpleadoCategoriasPago(IdEmpleado, IdCategoriasPago, CantHoras, Activo) VALUES "
                    + "(" + "'" + empCat.getIdEmpleado() + "'" + ","
                    + "'" + empCat.getIdCategoriasPago() + "'" + ","
                    + empCat.getCantHoras() + ","
                    + "'1'" + ")";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLEmp);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public EmpleadoDeduccion ObtenerEmpleadoDed(int id) {

        return null;
    }

    public void ActualizaEmpCat() throws SNMPExceptions, SQLException {
        String Query = "";

        try {

            Query = "UPDATE EmpleadoCategoriasPago SET Activo = 0";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public ArrayList<EmpleadoCategoriasPago> VerTodos()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<EmpleadoCategoriasPago> list = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM EMPLEADOCATEGORIASPAGO WHERE ACTIVO = '1'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                EmpleadoCategoriasPago empleadoCategoria = new EmpleadoCategoriasPago();
                empleadoCategoria.setIdEmpleado(rsPA.getString("IdEmpleado"));
                empleadoCategoria.setIdCategoriasPago(rsPA.getString("IdCategoriasPago"));
                empleadoCategoria.setCantHoras(rsPA.getInt("CantHoras"));
                empleadoCategoria.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
                list.add(empleadoCategoria);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return list;
    }

    public ArrayList<EmpleadoCategoriasPago> catPorIdEmp(String idEmpleado) throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<EmpleadoCategoriasPago> list = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "select * from EmpleadoCategoriasPago where IdEmpleado = " + idEmpleado;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                EmpleadoCategoriasPago empleadoCategoria = new EmpleadoCategoriasPago();
                empleadoCategoria.setIdEmpleado(rsPA.getString("IdEmpleado"));
                empleadoCategoria.setIdCategoriasPago(rsPA.getString("IdCategoriasPago"));
                empleadoCategoria.setCantHoras(rsPA.getInt("CantHoras"));
                list.add(empleadoCategoria);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return list;
    }
}
