
package gp2_tp_AccesoAData;

import gp2_tp1_Entidades.Alumno;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlumnoData {
    private Connection con;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public AlumnoData(Connection con) {
        this.con = con;
    }
    
    public void guardarAlumno(Alumno a) {
        
        try {
            String sql = "insert into alumno(dni, nombre, apellido, fechaNacimiento, estado)"
                    + " values (?, ?, ?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setDate(4, java.sql.Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isEstado());
            
            int filas = ps.executeUpdate();
            if (filas>0) {
                System.out.println("Alumno Registrado con exito");
            }
        }catch(SQLException e) {
            System.err.println("Datos de alumno incompatibles");
        }
    }
    
    public Alumno buscarAlumno(int id) {
        Alumno alumno = null;
        try{
            String sql = "select * from alumno where idAlumno = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()) {
                alumno = new Alumno(resultado.getInt("dni"),resultado.getInt("idAlumno"),resultado.getString("apellido"),
                    resultado.getString("nombre"),resultado.getDate("fechaNacimiento").toLocalDate(),
                    resultado.getBoolean("estado"));
            }
        }catch(SQLException e) {
            System.err.println("Alumno no encontrado "+e);
        }
        return alumno;
    }
    
    public void eliminarAlumno(int id) {
        try{
            String sql = "delete from alumno where idAlumno = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            if (filas>0) {
                System.out.println("Se ha eliminado al alumno correctamente");
            }else
                System.err.println("No se ha encontrado al alumno");
        }catch(SQLException e) {
            System.err.println("Alumno no encontrado "+e);
        }
    }
    
    public void actualizarAlumno(Alumno a,String cambiar) {
        try {
            int filas=0;
            if (cambiar.contains("nombre")) {
                if (a.getNombre()!=null) {
                    String sql = "update alumno set nombre=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, a.getNombre());
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo el nombre del alumno ("+a.getIdAlumno()+") porque es nulo");
            }
            if (cambiar.contains("apellido")) {
                if (a.getApellido()!=null) {
                    String sql = "update alumno set apellido=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, a.getApellido());
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo el apellido del alumno ("+a.getIdAlumno()+") porque es nulo");
            }
            if (cambiar.contains("dni")) {
                if (a.getDni()!=0) {
                    String sql = "update alumno set dni=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setInt(1, a.getDni());
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo el dni del alumno ("+a.getIdAlumno()+") porque es 0");
            }
            if (cambiar.contains("fechaNacimiento")) {
                if (a.getFechaNacimiento()!=null) {
                    String sql = "update alumno set fechaNacimiento=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setDate(1, java.sql.Date.valueOf(a.getFechaNacimiento()));
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo la fecha de nacimiento del alumno ("+a.getIdAlumno()+") porque es nulo");
            }
            if (cambiar.contains("estado")) {
                String sql = "update alumno set estado=? where idAlumno=?";
                
                PreparedStatement st = con.prepareStatement(sql);
                st.setBoolean(1, a.isEstado());
                st.setInt(2, a.getIdAlumno());
                
                filas = st.executeUpdate();
            }
            
            if (filas>0) {
                System.out.println("Alumno ("+a.getIdAlumno()+") actualizado con exito");
            }else
                System.err.println("No se encontra la id del alumno");
        }catch(SQLException e) {
            System.err.println("Datos de alumno incompatibles: "+e);
        }
    }
    
    public void actualizarAlumno(Alumno a,String cambiar, int ID) {
        try {
            int filas=0;
            if (cambiar.contains("nombre")) {
                if (a.getNombre()!=null) {
                    String sql = "update alumno set nombre=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, a.getNombre());
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo el nombre del alumno ("+a.getIdAlumno()+") porque es nulo");
            }
            if (cambiar.contains("apellido")) {
                if (a.getApellido()!=null) {
                    String sql = "update alumno set apellido=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, a.getApellido());
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo el apellido del alumno ("+a.getIdAlumno()+") porque es nulo");
            }
            if (cambiar.contains("dni")) {
                if (a.getDni()!=0) {
                    String sql = "update alumno set dni=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setInt(1, a.getDni());
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo el dni del alumno ("+a.getIdAlumno()+") porque es 0");
            }
            if (cambiar.contains("fechaNacimiento")) {
                if (a.getFechaNacimiento()!=null) {
                    String sql = "update alumno set fechaNacimiento=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setDate(1, java.sql.Date.valueOf(a.getFechaNacimiento()));
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo la fecha de nacimiento del alumno ("+a.getIdAlumno()+") porque es nulo");
            }
            if (cambiar.contains("estado")) {
                String sql = "update alumno set estado=? where idAlumno=?";
                
                PreparedStatement st = con.prepareStatement(sql);
                st.setBoolean(1, a.isEstado());
                st.setInt(2, a.getIdAlumno());
                
                filas = st.executeUpdate();
            }
            if (cambiar.contains("idAlumno")) {
                if (ID>0&buscarAlumno(ID)==null) {
                    String sql = "update alumno set idAlumno=? where idAlumno=?";

                    PreparedStatement st = con.prepareStatement(sql);
                    st.setInt(1, ID);
                    st.setInt(2, a.getIdAlumno());

                    filas = st.executeUpdate();
                }else
                    System.err.println("No se actualizo el idAlumno del alumno ("+a.getIdAlumno()+") porque es incompatible o ya existe");
            }
            if (filas>0) {
                System.out.println("Alumno ("+a.getIdAlumno()+") actualizado con exito");
            }else
                System.err.println("No se encontra la id del alumno");
        }catch(SQLException e) {
            System.err.println("Datos de alumno incompatibles: "+e);
        }
        
    }
    
    public void motrarTablaAlumnos() throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT * FROM alumno");
        while(resultado.next()) {
            LocalDate fecha = resultado.getDate("fechaNacimiento").toLocalDate();
            String estado = (resultado.getBoolean("estado"))? "activo":"inactivo";
            System.out.println(resultado.getString("idAlumno")+", "+resultado.getString("dni")+", "+resultado.getString("nombre")+", "+resultado.getString("apellido")+", "+fecha.format(formato)+", "+estado);
        }
    }
}
