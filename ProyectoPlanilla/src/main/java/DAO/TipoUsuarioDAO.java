/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.TipoUsuario;
import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Sebastian
 */
public class TipoUsuarioDAO {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public TipoUsuarioDAO() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void RegistrarTipo(TipoUsuario tipo)
            throws SNMPExceptions, SQLException {
        String strSQLUsu = "";

        try {
            //Se obtienen los valores del objeto

            strSQLUsu
                    = "INSERT INTO TipoUsuario(Id, Descripcion) VALUES "
                    + "(" + "'" + tipo.getId() + "'" + ","
                    + "'" + tipo.getDescripcion() + "'" + ")";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLUsu);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public ArrayList<TipoUsuario> VerTodos()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<TipoUsuario> listaTipos = new ArrayList();

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM TipoUsuario";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                TipoUsuario tipo = new TipoUsuario();
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

    public LinkedList<TipoUsuario> VerTodosLinked()
            throws SNMPExceptions, SQLException {
        String Query = "";
        LinkedList<TipoUsuario> listaTipos = new LinkedList();

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM TipoUsuario";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                TipoUsuario tipo = new TipoUsuario();
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

    public TipoUsuario ObtenerTipo(String id)
            throws SNMPExceptions, SQLException {
        String Query = "";
        TipoUsuario tipo = null;

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM TipoUsuario WHERE Id = " + id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                tipo = new TipoUsuario();
                tipo.setId(rsPA.getInt("Id"));
                tipo.setDescripcion(rsPA.getString("Descripcion"));
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return tipo;
    }

    public TipoUsuario ActualizarTipo(TipoUsuario tipo)
            throws SNMPExceptions, SQLException {
        String Query = "";
        TipoUsuario tipoU = null;

        try {
            //Se obtienen los valores del objeto

            Query = "UPDATE TipoUsuario SET ID = '" + tipo.getId() + "',"
                    + "Descripcion = '" + tipo.getDescripcion() + "'"
                    + "Where id = " + tipo.getId();

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return tipoU;
    }

    public void BorrarTipo(TipoUsuario tipo) throws SNMPExceptions,
            SQLException {
        String query = "";
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            query = "DELETE FROM TipoUsuario WHERE Id = " + tipo.getId();
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
