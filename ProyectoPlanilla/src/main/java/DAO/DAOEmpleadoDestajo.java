/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Empleado;
import Model.EmpleadoDestajo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ramir
 */
public class DAOEmpleadoDestajo {
      private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public DAOEmpleadoDestajo(){
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public void RegistrarEmpleado(ArrayList<EmpleadoDestajo> listEmpDestajo)
      throws SNMPExceptions, SQLException {
        String strSQLEmp = "";


        try {
             ActualizaActivo();
            //Se obtienen los valores del objeto
            for(EmpleadoDestajo empDestajo : listEmpDestajo){
            strSQLEmp
                    = "INSERT INTO EmpleadoDestajo(IdEmpleado,Cantidad,Descripcion,PagoUnidad,Total,Activo) VALUES "
                    + "(" + "'" + empDestajo.getIdEmpleado()+ "'" + ","
                    +  empDestajo.getCantidad()+ ","
                    + "'" + empDestajo.getDescripcion()+ "'" + ","
                    +  empDestajo.getPagoUnidad() + ","
                     + empDestajo.getTotal() + ","
                        + "'" + (empDestajo.getActivo().equalsIgnoreCase("activo") ? 1 : 0) + "'" + ")";
                  


            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQLEmp);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
}
    public ArrayList<EmpleadoDestajo> VerTodos()
         throws SNMPExceptions, SQLException {
        String Query = "";
        ArrayList<EmpleadoDestajo> listEmpDestajo = new ArrayList();
         try {
            //Se obtienen los valores del objeto

            Query = "SELECT * FROM EMPLEADODESTAJO WHERE ACTIVO = '1' ";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                EmpleadoDestajo empDestajo = new EmpleadoDestajo();
                empDestajo.setId(rsPA.getInt("Id"));
                empDestajo.setIdEmpleado(rsPA.getString("IdEmpleado"));
                empDestajo.setCantidad(rsPA.getInt("Cantidad"));
                empDestajo.setDescripcion(rsPA.getString("Descripcion"));
                empDestajo.setPagoUnidad(rsPA.getInt("PagoUnidad"));
                empDestajo.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
                empDestajo.setTotal(rsPA.getDouble("Total"));
                listEmpDestajo.add(empDestajo);
            }
          

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return listEmpDestajo;
    }
    public EmpleadoDestajo ObtenerEmpleadoDestajo(int Id)
            throws SNMPExceptions, SQLException {
        String Query = "";
        EmpleadoDestajo empDestajo = null;

        try {
            //Se obtienen los valores del objeto
        

            Query = "SELECT * FROM EMPLEADODESTAJO WHERE Id = " + Id;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {

                empDestajo = new EmpleadoDestajo();
                empDestajo.setId(rsPA.getInt("Id"));
                empDestajo.setIdEmpleado(rsPA.getString("IdEmpleado"));
                empDestajo.setCantidad(rsPA.getInt("Cantidad"));
                empDestajo.setDescripcion(rsPA.getString("Descripcion"));
                empDestajo.setPagoUnidad(rsPA.getInt("PagoUnidad"));
                empDestajo.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
                empDestajo.setTotal(rsPA.getDouble("Total"));
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return empDestajo;
    }
    
    public void ActualizaActivo() throws SNMPExceptions{
         try {
            //Se obtienen los valores del objeto

            String Query = "Update EmpleadoDestajo set Activo = 0";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
   
     
//       public ArrayList<Empleado> empleadoByIdTipoPlanilla(int idTipoPlanilla)
//         throws SNMPExceptions, SQLException {
//        String Query = "";
//        ArrayList<Empleado> listEmpleados = new ArrayList();
//         try {
//            //Se obtienen los valores del objeto
//
//            Query = "select * from Empleado where IdTipoPlanilla = " + idTipoPlanilla;
//
//            //Se ejecuta la sentencia SQL
//            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);
//
//            while (rsPA.next()) {
//
//                Empleado empleados = new Empleado();
//                empleados.setId(rsPA.getString("Id"));
//                empleados.setNombre(rsPA.getString("Nombre"));
//                empleados.setApellidos(rsPA.getString("Apellidos"));
//                empleados.setSalarioBruto(rsPA.getDouble("SalarioBruto"));
//                empleados.setHoraOrdinarias(rsPA.getInt("HorasOrdinarias"));
//                empleados.setActivo(rsPA.getBoolean("Activo") ? "Activo" : "Inactivo");
//                empleados.setSalarioHora(rsPA.getDouble("SalarioHora"));
//                  empleados.setIdTipoPlanilla(rsPA.getInt("IdTipoPlanilla"));
//                listEmpleados.add(empleados);
//            }
//          
//
//        } catch (SQLException e) {
//            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
//        } catch (Exception e) {
//            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
//        }
//        return listEmpleados;
//    }
}
