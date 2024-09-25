
package gp2_tp_AccesoAData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL="jdbc:mysql://localhost/";
    private static final String DB ="GP2_TP1_Transversal";
    private static final String USUARIO="root";
    private static String PASSWORD="";
    
    private static Connection connection;
}
