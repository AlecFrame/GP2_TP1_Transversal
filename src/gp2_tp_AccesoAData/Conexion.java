
package gp2_tp_AccesoAData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String URL="jdbc:mysql://localhost/";
    private String DB ="gp2_transversal_tp1";
    private String USUARIO="root";
    private String PASSWORD="";
    
    private static Connection conexion = null;

    public Conexion(String DB, String USUARIO, String PASSWORD) {
        this.DB=DB;
        this.USUARIO=USUARIO;
        this.PASSWORD=PASSWORD;
        URL="jdbc:mysql://localhost/"+DB;
    }

    public Connection cargarConexion() {
        if (conexion==null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion=DriverManager.getConnection(URL,USUARIO,PASSWORD);
            }catch(SQLException|ClassNotFoundException e) {
                System.out.println("No se pudo conectar o cargar los drivers");
            }
        }
        return conexion;
    }
}
