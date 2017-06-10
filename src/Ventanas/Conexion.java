/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JIGA1UP
 */
public class Conexion {    
    private static final String SERVER = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "softcake";
    private static final String USERNAME = "softcake";
    private static final String PASSWORD = "12345";    
    private static final String MENSAJEERROR = "Cannot connect the database! ";

    
    public MysqlDataSource getConnection()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName(SERVER);
            dataSource.setDatabaseName(DATABASE);
            dataSource.setUser(USERNAME);
            dataSource.setPassword(PASSWORD);
            try (Connection conn = dataSource.getConnection()){                
                return dataSource;
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,MENSAJEERROR + e);
            }
            return null;
    }
}
