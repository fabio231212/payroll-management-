/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Empleado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Antonio Ramirez
 */
public class EmpleadoDAL {
    
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public EmpleadoDAL(){
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public void RegistrarEmpleado(Empleado emp)
      throws SNMPExceptions, SQLException {
        String strSQLEmp = "";


        try {
            //Se obtienen los valores del objeto

            strSQLEmp
                    = "INSERT INTO Empleado(Id, Nombre, Apellidos, SalarioBruto, HorasOrdinarias, Activo,SalarioHora,IdTipoPlanilla) VALUES "
                    + "(" + "'" + emp.getId() + "'" + ","
                    + "'" + emp.getNombre()+ "'" + ","
                    + "'" + emp.getApellidos()+ "'" + ","
                    + "'" + emp.getSalarioBruto()+ "'" + ","
                     + "'" + emp.getHoraOrdinarias() + "'" + ","
                      + "'" + (emp.getActivo().equalsIgnoreCase("activo") ? 1 : 0)+ "'" + "," + emp.getSalarioHora() + ","
                      + emp.getIdTipoPlanilla() +")";
                  


            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLEmp);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
}
    public ArrayList<Empleado> VerTodos()
         throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<Empleado> listEmpleados = new ArrayList();
         try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM EMPLEADO";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                Empleado empleados = new Empleado();
                empleados.setId(rsPA.getString("Id"));
                empleados.setNombre(rsPA.getString("Nombre"));
                empleados.setApellidos(rsPA.getString("Apellidos"));
                empleados.setSalarioBruto(rsPA.getDouble("SalarioBruto"));
                empleados.setHoraOrdinarias(rsPA.getInt("HorasOrdinarias"));
                empleados.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
                empleados.setSalarioHora(rsPA.getDouble("SalarioHora"));
                empleados.setIdTipoPlanilla(rsPA.getInt("IdTipoPlanilla"));
                listEmpleados.add(empleados);
            }
          

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listEmpleados;
    }
    public Empleado ObtenerEmpleado(String Id)
            throws SNMPExceptions, SQLException {
        String Query = "";
        Empleado empleado = null;

        try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM EMPLEADO WHERE Id = " + Id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                empleado = new Empleado();
                empleado.setId(rsPA.getString("Id"));
                empleado.setNombre(rsPA.getString("Nombre"));
                empleado.setApellidos(rsPA.getString("Apellidos"));
                empleado.setSalarioBruto(rsPA.getDouble("SalarioBruto"));
                empleado.setHoraOrdinarias(rsPA.getInt("HorasOrdinarias"));
                empleado.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
                empleado.setSalarioHora(rsPA.getDouble("SalarioHora"));
                empleado.setIdTipoPlanilla(rsPA.getInt("IdTipoPlanilla"));
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return empleado;
    }
    public Empleado ActualizarEmpleado(Empleado emp)
            throws SNMPExceptions, SQLException {
        String Query = "";
        Empleado empleados = null;

        try {
            //Se obtienen los valores del objeto

            Query = "UPDATE Empleado SET Nombre = '" + emp.getNombre() + "',"
                    + "Apellidos = '" + emp.getApellidos()+ "',"
                    + "SalarioBruto= "+ emp.getSalarioBruto()+","
                       + "SalarioHora= "+ emp.getSalarioHora()+","
                    + "HorasOrdinarias = " + emp.getHoraOrdinarias()+ ","
                    + "Activo = '" + (emp.getActivo().equalsIgnoreCase("activo") ? 1 : 0) + "' ,"
                     + "IdTipoPlanilla = " + emp.getIdTipoPlanilla()
                    + " Where id = " + emp.getId();

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return empleados;
    }
     public void BorrarEmpleado(Empleado emp) throws SNMPExceptions,
            SQLException {
        String query = "";
        try {

            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            query = "DELETE FROM Empleado WHERE Id = " + emp.getId();
            accesoDatos.ejecutaSQL(query);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
    }
     
       public ArrayList<Empleado> empleadoByIdTipoPlanilla(int idTipoPlanilla)
         throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<Empleado> listEmpleados = new ArrayList();
         try {
            //Se obtienen los valores del objeto

            Query = "select * from Empleado where IdTipoPlanilla = " + idTipoPlanilla;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                Empleado empleados = new Empleado();
                empleados.setId(rsPA.getString("Id"));
                empleados.setNombre(rsPA.getString("Nombre"));
                empleados.setApellidos(rsPA.getString("Apellidos"));
                empleados.setSalarioBruto(rsPA.getDouble("SalarioBruto"));
                empleados.setHoraOrdinarias(rsPA.getInt("HorasOrdinarias"));
                empleados.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
                empleados.setSalarioHora(rsPA.getDouble("SalarioHora"));
                  empleados.setIdTipoPlanilla(rsPA.getInt("IdTipoPlanilla"));
                listEmpleados.add(empleados);
            }
          

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listEmpleados;
    }
    
}
    


