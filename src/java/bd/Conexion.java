/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lluis_2
 */
public class Conexion {
    Connection connection;
    
    public Conexion()
    {
        try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.180.10:1521:INSLAFERRERI", "fjaume","1234");
               // connection = DriverManager.getConnection("jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI", "PROFEA1","1234");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}

    public Connection getConnection() {
        return connection;
    }
    
    
        public void finalizarConexion() throws SQLException
        {
            connection.close();
        }
  
        public  boolean insertarCliente(Autobus cli) throws SQLException
{
       String sql = "INSERT INTO AUTOBUSLOGIN (MATRICULA, PASSWORD) VALUES (?, ?)";
       PreparedStatement stmt = connection.prepareStatement(sql);
       stmt.setString(1, cli.getMatricula()); //stmt.setString(1, cli.getNombre);
       stmt.setString(2, cli.getPassword());
       int res = stmt.executeUpdate();
       finalizarConexion();
           
 
      return (res == 1);
}

    
 
public List<Autobus> obtenerAutobuses() throws SQLException
        
{
      ResultSet rset;
      List<Autobus> lista = new ArrayList();
      String sql = "SELECT Matricula, Password, fecha FROM AUTOBUSLOGIN";
      PreparedStatement stmt = getConnection().prepareStatement(sql);
      rset = stmt.executeQuery();
      while (rset.next())
      {
          lista.add(new Autobus(rset.getString("Matricula"), rset.getString("Password"), rset.getString("Fecha")));
          
      }
      finalizarConexion();
      return lista;
}
 
    public Autobus obtenerAutobus (int id) throws SQLException
    {
        Autobus cli = null;
        
      ResultSet rset;
      
      String sql = "SELECT Matricula, Password, fecha FROM AUTOBUSLOGIN WHERE Matricula = ?";
      PreparedStatement stmt = getConnection().prepareStatement(sql);
      stmt.setInt(1, id);
      rset = stmt.executeQuery();
      while (rset.next())
      {
          cli = new Autobus(rset.getString("Matricula"), rset.getString("Password"), rset.getString("Fecha"));
          
      }
      finalizarConexion();
      return cli;
        
        
    }
    
    public boolean ActualizarAutobus(Autobus cli) throws SQLException
    {
        boolean result;
          String sql = "UPDATE AUTOBUSLOGIN SET Matricula = ?, Password = ? WHERE Matricula = ?";
       PreparedStatement stmt = connection.prepareStatement(sql);
       stmt.setString(1, cli.getMatricula()); //stmt.setString(1, cli.getNombre);
       stmt.setString(2, cli.getPassword());
       stmt.setString(3, cli.getFecha());
       
       int res = stmt.executeUpdate();
       if (res==0)  
          result = insertarCliente(cli);
       else
           result = true;
          
 
      return (result);
    }
 
     
  public boolean eliminarAutobus(int id) throws SQLException
    {
       
       String sql = "DELETE FROM AUTOBUSLOGIN WHERE Matricula = ?";
       PreparedStatement stmt = connection.prepareStatement(sql);
       stmt.setInt(1, id);
       
       int res = stmt.executeUpdate();
       
      return (res==1);
    }
     

        
    
}
