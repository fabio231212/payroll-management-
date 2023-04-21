/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class UsuarioDAO {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public UsuarioDAO() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
     public boolean validarUsuario(String id, String clave)
         throws SNMPExceptions, SQLException{
           
        boolean existe = false;
        String select="";
         try{
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda"select * from Usuario where Id =" + id + " and Clave = " + "CONVERT(VARBINARY(50) " + clave + ")"
            select= "select * from Usuario where Id = '"+ id + "' and Clave = CONVERT(VARBINARY(50),'"+ clave + "');";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            if(rsPA.next()){
                
                existe=true;
            }
            
            rsPA.close();
      
            return existe;
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
    }

    public void RegistrarUsuario(Usuario usu)
            throws SNMPExceptions, SQLException {
        String strSQLUsu = "";

        try {
            //Se obtienen los valores del objeto

            strSQLUsu
                    = "INSERT INTO Usuario(Id, IdTipoUsuario, Nombre, Apellidos, Clave, Activo) VALUES "
                    + "(" + "'" + usu.getId() + "'" + ","
                    + "'" + usu.getIdTipoUsuario() + "'" + ","
                    + "'" + usu.getNombre() + "'" + ","
                    + "'" + usu.getApellidos() + "'" + ","
                    + "CONVERT(VARBINARY(50), '" + usu.getClave() + "'),"
                    + "1" + ")";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLUsu);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
     public void cambiarClave(Usuario user)
            throws SNMPExceptions, SQLException {
        String strSQLUsu = "";

        try {
            //Se obtienen los valores del objeto

            strSQLUsu
                    = "update Usuario set Clave="
                    + "CONVERT(VARBINARY(50), '" + user.getClave() + "') where Id = " + user.getId();

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLUsu);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
     

    public ArrayList<Usuario> VerTodos()
            throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<Usuario> listUsuarios = new ArrayList();

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM USUARIO";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rsPA.getString("Id"));
                usuario.setIdTipoUsuario(rsPA.getInt("IdTipoUsuario"));
                usuario.setNombre(rsPA.getString("Nombre"));
                usuario.setApellidos(rsPA.getString("Apellidos"));
                usuario.setClave(rsPA.getString("Clave"));
                usuario.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
                listUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listUsuarios;
    }

    public Usuario ObtenerUsuario(String id)
            throws SNMPExceptions, SQLException {
        String Query = "";
        Usuario usuario = null;

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM USUARIO WHERE Id = " + id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                usuario = new Usuario();
                usuario.setId(rsPA.getString("Id"));
                usuario.setIdTipoUsuario(rsPA.getInt("IdTipoUsuario"));
                usuario.setNombre(rsPA.getString("Nombre"));
                usuario.setApellidos(rsPA.getString("Apellidos"));
                usuario.setClave(rsPA.getString("Clave"));
                usuario.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return usuario;
    }

    public Usuario ActualizarUsuario(Usuario usu)
            throws SNMPExceptions, SQLException {
        String Query = "";
        Usuario usuario = null;

        try {
            //Se obtienen los valores del objeto

            Query = "UPDATE Usuario SET Nombre = '" + usu.getNombre() + "',"
                    + "Apellidos = '" + usu.getApellidos()+ "',"
                    + "Clave = CONVERT(VARBINARY(50),'" + usu.getClave() + "'),"
                    + "IdTipoUsuario = '" + usu.getIdTipoUsuario() + "',"
                    + "Activo = '" + (usu.getActivo().equalsIgnoreCase("activo") ? 1 : 0) + "'"
                    + "Where id = " + usu.getId();

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return usuario;
    }

    public void BorrarUsuario(Usuario usu) throws SNMPExceptions,
            SQLException {
        String query = "";
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            query = "DELETE FROM Usuario WHERE Id = " + usu.getId();
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
