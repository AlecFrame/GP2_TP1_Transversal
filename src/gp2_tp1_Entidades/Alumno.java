
package gp2_tp1_Entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Alumno {
    private int idAlumno;
    private int dni;
    private String apellido;
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean estado;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Alumno() {
    }

    public Alumno(int dni, int idAlumno, String apellido, String nombre, LocalDate fechaNac, boolean activo) {
        this.dni = dni;
        this.idAlumno = idAlumno;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNac;
        this.estado = activo;
    }

    public Alumno(int dni, String apellido, String nombre, LocalDate fechaNac, boolean activo) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNac;
        this.estado = activo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "idAlumno, dni, nombre, apellido, fechaNacimiento, estado\n"
                +idAlumno+", "+dni+", "+nombre+", "+apellido+", "+fechaNacimiento.format(formato)+", "+((estado)? "activo":"inactivo");
    }
}
