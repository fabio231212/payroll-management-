/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CategoriasPago;
import Model.Deduccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Sebastian
 */
public class CategoriasPagoDAO {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public CategoriasPagoDAO() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public LinkedList<CategoriasPago> VerTodosLinked()
            throws SNMPExceptions, SQLException {
        String Query = "";
        LinkedList<CategoriasPago> listaCategorias = new LinkedList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM CategoriasPago";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                CategoriasPago categoria = new CategoriasPago();
                categoria.setId(rsPA.getString("Id"));
                categoria.setDescripcion(rsPA.getString("Descripcion"));
                categoria.setPorcentaje(rsPA.getDouble("Porcentaje"));

                listaCategorias.add(categoria);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaCategorias;
    }
    
    public ArrayList<CategoriasPago> VerTodos()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<CategoriasPago> listaC = new ArrayList();
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM CategoriasPago";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                CategoriasPago categoria = new CategoriasPago();
                categoria.setId(rsPA.getString("Id"));
                categoria.setDescripcion(rsPA.getString("Descripcion"));
                categoria.setPorcentaje(rsPA.getDouble("Porcentaje"));

                listaC.add(categoria);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listaC;
    }

    public CategoriasPago ObtenerCat(String id)
            throws SNMPExceptions, SQLException {
        String Query = "";
        CategoriasPago cat = null;
        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM CategoriasPago WHERE Id = " + "'" + id + "'";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {
                cat = new CategoriasPago();

                cat.setPorcentaje(rsPA.getDouble("Porcentaje"));
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return cat;
    }
}
