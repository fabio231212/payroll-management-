/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Deduccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author ramir
 */
public class DeduccionDAO {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public DeduccionDAO() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void RegistrarDeduccion(Deduccion deduccion)
            throws SNMPExceptions, SQLException {
        String strSQLEmp = "";

        try {
            //Se obtienen los valores del objeto

            strSQLEmp
                    = "INSERT INTO Deduccion VALUES "
                    + "('" + deduccion.getId() + "',"
                    + "'" + deduccion.getDescripcion() + "'," + "'" + deduccion.getProceso() + "')";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLEmp);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public ArrayList<Deduccion> VerTodos()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<Deduccion> listaDeduccion = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM Deduccion";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                Deduccion deduccion = new Deduccion();
                deduccion.setId(rsPA.getString("Id"));
                deduccion.setDescripcion(rsPA.getString("Descripcion"));
                deduccion.setProceso(rsPA.getString("Proceso"));

                listaDeduccion.add(deduccion);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaDeduccion;
    }
    
     public  ArrayList<Deduccion> DeduccionesAutomaticas()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<Deduccion> listaDeduccion = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "select * from Deduccion where Proceso = 'Automático'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                Deduccion deduccion = new Deduccion();
                deduccion.setId(rsPA.getString("Id"));
                deduccion.setDescripcion(rsPA.getString("Descripcion"));
                deduccion.setProceso(rsPA.getString("Proceso"));
                deduccion.setPorcentajeAutomatico(rsPA.getDouble("PorcentajeAutomatico"));

                listaDeduccion.add(deduccion);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaDeduccion;
    }

    public LinkedList<Deduccion> VerTodosLinked()
            throws SNMPExceptions, SQLException {
        String Query = "";
        LinkedList<Deduccion> listaDeduccion = new LinkedList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM Deduccion";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                Deduccion deduccion = new Deduccion();
                deduccion.setId(rsPA.getString("Id"));
                deduccion.setDescripcion(rsPA.getString("Descripcion"));
                deduccion.setProceso(rsPA.getString("Proceso"));

                listaDeduccion.add(deduccion);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaDeduccion;
    }

    public Deduccion ObtenerDeduccion(String Id)
            throws SNMPExceptions, SQLException {
        String Query = "";
        Deduccion deduccion = null;

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM Deduccion WHERE Id = '" + Id + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                deduccion = new Deduccion();
                deduccion.setId(rsPA.getString("Id"));
                deduccion.setDescripcion(rsPA.getString("Descripcion"));
                deduccion.setProceso(rsPA.getString("Proceso"));
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return deduccion;
    }

    public Deduccion ActualizaDeduccion(Deduccion deduccion)
            throws SNMPExceptions, SQLException {
        String Query = "";
        Deduccion ded = null;

        try {
            //Se obtienen los valores del objeto

            Query = "UPDATE Deduccion SET Id = '" + deduccion.getId() + "',"
                    + "Descripcion = '" + deduccion.getDescripcion() + "'," 
                    + "Proceso = '" + deduccion.getProceso() + "' where Id = '" + deduccion.getId() +"'";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return ded;
    }

    public void BorrarDeduccion(Deduccion ded) throws SNMPExceptions,
            SQLException {
        String query = "";
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            query = "DELETE FROM Deduccion WHERE Id = '" + ded.getId() + "'";
            accesoDatos.ejecutaSQL(query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
    }
}
