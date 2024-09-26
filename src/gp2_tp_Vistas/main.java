
package gp2_tp_Vistas;


import gp2_tp1_Entidades.Alumno;
import gp2_tp_AccesoAData.AlumnoData;
import gp2_tp_AccesoAData.Conexion;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws SQLException {
        Conexion conexion = new Conexion("gp2_transversal_tp1","root","");
        Connection con = (Connection) conexion.cargarConexion();
        
        AlumnoData alumno = new AlumnoData(con);
        
        Scanner leerI = new Scanner(System.in);
        Scanner leerS = new Scanner(System.in);
        
        boolean seguir = true;
        boolean numeroValido = false;
        
        String opcion = "";
        String estados="";
        int dni=0;
        int id=0;
        String nombre="";
        String apellido="";
        String cambiar="";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechas="";
        LocalDate fecha=null;
        boolean estado=false;
        
        System.out.println("//// Bienvenido a la base de datos Alumno del TP 1 de Transversal - by Grupo 2 ////\n//");
        while (seguir) {
            System.out.println("//¿Qué operacion deseas realizar en la base de datos?, las opciones están entre parentesis");
            System.out.println("//\n// > (mostrar) Mostrar la base de datos de alumnos");
            System.out.println("// > (guardar) Guardar a un nuevo alumno en la base de datos");
            System.out.println("// > (buscar) Buscar por id a un alumno en la base de datos");
            System.out.println("// > (actualizar) Actualizar o modificar algun dato de un alumno por id");
            System.out.println("// > (eliminar) Eliminar alumno mediante su id");
            System.out.println("// > (finalizar) Finalizar operacion\n//");
            numeroValido = false;
            while (!numeroValido) {
                System.out.print("Escriba la opcion que desee realizar: ");
                opcion = leerS.nextLine();
                if (!opcion.equalsIgnoreCase("mostrar")&!opcion.equalsIgnoreCase("guardar")&
                    !opcion.equalsIgnoreCase("buscar")&!opcion.equalsIgnoreCase("actualizar")&
                    !opcion.equalsIgnoreCase("eliminar")&!opcion.equalsIgnoreCase("finalizar")){
                    System.err.println("No ingreso ninguna opcion. vuela a intentarlo");
                }else
                    numeroValido = true;
            }
            
            switch (opcion.toLowerCase()) {
                case ("mostrar"): {
                    System.out.println("\n/// - Base de datos de Alumno - /////");
                    System.out.println("idAlumno, dni, nombre, apellido, fechaNacimiento, estado");
                    alumno.motrarTablaAlumnos();
                    System.out.println("\n");
                    break;
                }
                case ("guardar"): {
                    System.out.println("\n/// - Guardando un nuevo alumno - /////");
                    numeroValido = false;
                    while (!numeroValido) {
                        try {
                            System.out.print("Ingrese el DNI del alumno: ");
                            dni = leerI.nextInt();
                            numeroValido = true;
                        } catch (InputMismatchException e) {
                            System.err.println("Error: se requiere un número. Intente de nuevo.");
                            leerI.nextLine();
                        }
                    }
                    System.out.print("Ingrese el nombre del alumno: ");
                    nombre = leerS.nextLine();
                    System.out.print("Ingrese el apellido del alumno: ");
                    apellido = leerS.nextLine();
                    numeroValido = false;
                    while (!numeroValido) {
                        try {
                            System.out.print("Ingrese la fecha de nacimiento del alumno con el siguiente patron (05/02/2005): ");
                            fechas = leerS.nextLine();
                            fecha = LocalDate.parse(fechas, formato);
                            numeroValido = true;
                        } catch (DateTimeParseException e) {
                            System.err.println("Error: el formato no es correcto.");
                            leerI.nextLine();
                        }
                    }
                    numeroValido = false;
                    while (!estados.equalsIgnoreCase("activo")&!estados.equalsIgnoreCase("inactivo")) {
                        System.out.print("Ingrese el estado del alumno (activo o inactivo): ");
                        estados = leerS.nextLine();
                        if (estados.equalsIgnoreCase("activo")|estados.equalsIgnoreCase("inactivo")) {
                            estado = (estados.equalsIgnoreCase("activo"));
                        }else
                            System.err.println("Error: debe ingresar activo o inactivo.");
                    }
                    alumno.guardarAlumno(new Alumno(dni,apellido,nombre,fecha,estado));
                    dni=0;nombre="";apellido="";fecha=null;fechas="";estado=false;
                    System.out.println("\n");
                    break;
                }
                case ("buscar"): {
                    System.out.println("\n/// - Buscar alumno - /////");
                    numeroValido = false;
                    while (!numeroValido) {
                        try {
                            System.out.print("Ingrese el ID del alumno: ");
                            id = leerI.nextInt();
                            numeroValido = true;
                        } catch (InputMismatchException e) {
                            System.err.println("Error: se requiere un número. Intente de nuevo.");
                            leerI.nextLine();
                        }
                    }
                    if (alumno.buscarAlumno(id)!=null)
                        System.out.println("\n"+alumno.buscarAlumno(id));
                    else
                        System.err.println("No se ha encontrado al alumno");
                    System.out.println("\n");
                    break;
                }
                case ("actualizar"): {
                    System.out.println("\n/// - Actualizar un alumno - /////");
                    numeroValido = false;
                    while (!numeroValido) {
                        try {
                            System.out.print("Ingrese el ID del alumno: ");
                            id = leerI.nextInt();
                            numeroValido = true;
                        } catch (InputMismatchException e) {
                            System.err.println("Error: se requiere un número. Intente de nuevo.");
                            leerI.nextLine();
                        }
                    }
                    System.out.print("Ingrese los atributos que quiera cambiar aqui un ejemplo con todos los atributos (dni,nombre,apellido,fechaNacimiento,estado)\n > ");
                    cambiar=leerS.nextLine();
                    if (cambiar.contains("dni")) {
                        numeroValido = false;
                        while (!numeroValido) {
                            try {
                                System.out.print("Ingrese el DNI del alumno: ");
                                dni = leerI.nextInt();
                                numeroValido = true;
                            } catch (InputMismatchException e) {
                                System.err.println("Error: se requiere un número. Intente de nuevo.");
                                leerI.nextLine();
                            }
                        }
                    }
                    if (cambiar.contains("nombre")) {
                        System.out.print("Ingrese el nombre del alumno: ");
                        nombre = leerS.nextLine();
                    }
                    if (cambiar.contains("apellido")) {
                        System.out.print("Ingrese el apellido del alumno: ");
                        apellido = leerS.nextLine();
                    }
                    if (cambiar.contains("fechaNacimiento")) {
                        numeroValido = false;
                        while (!numeroValido) {
                            try {
                                System.out.print("Ingrese la fecha de nacimiento del alumno con el siguiente patron (05/02/2005): ");
                                fechas = leerS.nextLine();
                                fecha = LocalDate.parse(fechas, formato);
                                numeroValido = true;
                            } catch (DateTimeParseException e) {
                                System.err.println("Error: el formato no es correcto.");
                                leerI.nextLine();
                            }
                        }
                    }
                    if (cambiar.contains("estado")) {
                        numeroValido = false;
                        while (!estados.equalsIgnoreCase("activo")&!estados.equalsIgnoreCase("inactivo")) {
                            System.out.print("Ingrese el estado del alumno (activo o inactivo): ");
                            estados = leerS.nextLine();
                            if (estados.equalsIgnoreCase("activo")|estados.equalsIgnoreCase("inactivo")) {
                                estado = (estados.equalsIgnoreCase("activo"));
                            }else
                                System.err.println("Error: debe ingresar activo o inactivo.");
                        }
                    }
                    if (cambiar.contains("dni")|cambiar.contains("nombre")|cambiar.contains("apellido")|
                            cambiar.contains("fechaNacimiento")|cambiar.contains("estado")) {
                        System.out.println("\n");
                        alumno.actualizarAlumno(new Alumno(dni,id,apellido,nombre,fecha,estado), cambiar);
                        System.out.println("\n");
                        id=0;dni=0;nombre="";apellido="";fecha=null;fechas="";estado=false;
                    }else 
                        System.err.println("\nNo se ingreso ningun atributo\n");
                    break;
                }
                case ("eliminar"): {
                    System.out.println("\n/// - Eliminar alumno - /////");
                    numeroValido = false;
                    while (!numeroValido) {
                        try {
                            System.out.print("Ingrese el ID del alumno: ");
                            id = leerI.nextInt();
                            numeroValido = true;
                        } catch (InputMismatchException e) {
                            System.err.println("Error: se requiere un número. Intente de nuevo.");
                            leerI.nextLine();
                        }
                    }
                    System.out.print("\n");
                    alumno.eliminarAlumno(id);
                    System.out.print("\n");
                    break;
                }
                case ("finalizar"): {
                    seguir=false;
                }
            }
            
        }
        System.out.println("\n/// Gracias por usar el programa ///");
        
    }  
    
    
}
