
package gp2_tp_AccesoAData;

public class Inscripcion {
    private int idInscripcion;
    private double nota;
    private Alumno idAlumno;
    private Materia idMateria;

    public Inscripcion(int idinscripcion, double nota, Alumno alumno, Materia materia) {
        this.idInscripcion = idinscripcion;
        this.nota = nota;
        this.idAlumno = alumno;
        this.idMateria = materia;
    }

    public Inscripcion(Alumno alumno, double nota, Materia materia) {
        this.nota = nota;
        this.idAlumno = alumno;
        this.idMateria = materia;
    }

    public Inscripcion() {
    }

    public Inscripcion(double nota) {
        this.nota = nota;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
