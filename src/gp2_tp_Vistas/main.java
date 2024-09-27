
package gp2_tp_Vistas;


import gp2_tp1_Entidades.Alumno;
import gp2_tp_AccesoAData.AlumnoData;
import gp2_tp_AccesoAData.Conexion;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class main {

    public static void main(String[] args) throws SQLException {
        Conexion conexion = new Conexion("gp2_transversal_tp1","root","");
        Connection con = (Connection) conexion.cargarConexion();
        
        AlumnoData alumno = new AlumnoData(con);
        
        // Podria usar un solo leer pero prefiero separarlos en Int y String para no comerme la cabeza al cambiarlo y limpiarlo
        Scanner leerI = new Scanner(System.in);
        Scanner leerS = new Scanner(System.in);
        
        boolean seguir = true;
        boolean numeroValido = false;
        
        // Variables para Controlar lo que se debe ingresar
        String opcion = "";
        Set<String> opcionesValidas = new HashSet<>();
        opcionesValidas.add("1");
        opcionesValidas.add("2");
        opcionesValidas.add("3");
        opcionesValidas.add("4");
        opcionesValidas.add("5");
        opcionesValidas.add("6");
        opcionesValidas.add("7");
        opcionesValidas.add("mostrar");
        opcionesValidas.add("guardar");
        opcionesValidas.add("buscar");
        opcionesValidas.add("actualizar");
        opcionesValidas.add("eliminar");
        opcionesValidas.add("cambiarestado");
        opcionesValidas.add("finalizar");
        // Variables que uso para cuendo se tenga que crear un nuevo alumno o actualizarlo
        String estados="";
        int dni=0;
        int id=0;
        int ids=0;
        String nombre="";
        String apellido="";
        String cambiar="";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechas="";
        LocalDate fecha=null;
        boolean estado=false;
        
        System.out.println("//// Bienvenido a la base de datos Alumno del TP 1 de Transversal - by Grupo 2 ////\n//");
        System.out.println("\n/// - Base de datos de Alumno - /////");
        System.out.println("idAlumno, dni, nombre, apellido, fechaNacimiento, estado");
        alumno.motrarTablaAlumnos();
        System.out.println("\n");
        while (seguir) {
            try {
            Thread.sleep(50); // 50 milisegundos para que poder cargar los prints a continuacion correctamente y no se sobrepongan
            } catch (InterruptedException e) {}
            System.out.println("//¿Qué operacion deseas realizar en la base de datos?, las opciones están entre parentesis");
            System.out.println("//\n// > (1|mostrar) Mostrar la base de datos de alumnos");
            System.out.println("// > (2|guardar) Guardar a un nuevo alumno en la base de datos");
            System.out.println("// > (3|buscar) Buscar por id a un alumno en la base de datos");
            System.out.println("// > (4|actualizar) Actualizar o modificar algun dato de un alumno por id");
            System.out.println("// > (5|eliminar) Eliminar alumno mediante su id");
            System.out.println("// > (6|cambiarestado) Cambiar el estado del alumno de activo a inactivo o diseversa por id");
            System.out.println("// > (7|finalizar) Finalizar operacion\n//");
            numeroValido = false;
            while (!numeroValido) {
                System.out.print("Escriba o ingrese el número de la opcion que desee realizar: ");
                opcion = leerS.nextLine();
                if (!opcionesValidas.contains(opcion)) {
                        System.err.println("No ingreso ninguna opcion. vuela a intentarlo");
                }else
                    numeroValido = true;
            }
            
            switch (opcion.toLowerCase()) {
                case ("mostrar"):
                case ("1"): {
                    System.out.println("\n/// - Base de datos de Alumno - /////");
                    System.out.println("idAlumno, dni, nombre, apellido, fechaNacimiento, estado");
                    alumno.motrarTablaAlumnos();
                    System.out.println("\n");
                    break;
                }
                case ("guardar"): 
                case ("2"): {
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
                case ("buscar"): 
                case ("3"): {
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
                        System.err.println("\nNo se ha encontrado al alumno");
                    System.out.print("\n");
                    break;
                }
                case ("actualizar"): 
                case ("4"): {
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
                    if (alumno.buscarAlumno(id)!=null) {
                        System.out.print("Ingrese los atributos que quiera cambiar aqui un ejemplo con todos los atributos (idAlumno,dni,nombre,apellido,fechaNacimiento,estado)\n > ");
                        cambiar=leerS.nextLine();
                        if (cambiar.contains("idAlumno")) {
                            numeroValido = false;
                            while (!numeroValido) {
                                try {
                                    System.out.print("Ingrese la nuevo idAlumno del alumno: ");
                                    ids = leerI.nextInt();
                                    if (alumno.buscarAlumno(ids)!=null)
                                        System.err.println("Esa id ya existe, utilize otra");
                                    else {
                                        numeroValido = true;
                                    }
                                } catch (InputMismatchException e) {
                                    System.err.println("Error: se requiere un número. Intente de nuevo.");
                                    leerI.nextLine();
                                }
                            }
                        }
                        if (cambiar.contains("dni")) {
                            numeroValido = false;
                            while (!numeroValido) {
                                try {
                                    System.out.print("Ingrese el nuevo DNI del alumno: ");
                                    dni = leerI.nextInt();
                                    numeroValido = true;
                                } catch (InputMismatchException e) {
                                    System.err.println("Error: se requiere un número. Intente de nuevo.");
                                    leerI.nextLine();
                                }
                            }
                        }
                        if (cambiar.contains("nombre")) {
                            System.out.print("Ingrese el nuevo nombre del alumno: ");
                            nombre = leerS.nextLine();
                        }
                        if (cambiar.contains("apellido")) {
                            System.out.print("Ingrese el nuevo apellido del alumno: ");
                            apellido = leerS.nextLine();
                        }
                        if (cambiar.contains("fechaNacimiento")) {
                            numeroValido = false;
                            while (!numeroValido) {
                                try {
                                    System.out.print("Ingrese la nueva fecha de nacimiento del alumno con el siguiente patron (05/02/2005): ");
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
                                System.out.print("Ingrese el nuevo estado del alumno (activo o inactivo): ");
                                estados = leerS.nextLine();
                                if (estados.equalsIgnoreCase("activo")|estados.equalsIgnoreCase("inactivo")) {
                                    estado = (estados.equalsIgnoreCase("activo"));
                                }else
                                    System.err.println("Error: debe ingresar activo o inactivo.");
                            }
                        }
                        if (cambiar.contains("dni")|cambiar.contains("nombre")|cambiar.contains("apellido")|
                                cambiar.contains("fechaNacimiento")|cambiar.contains("estado")|cambiar.contains("idAlumno")) {
                            System.out.print("\n");
                            if (cambiar.contains("idAlumno")) {
                                alumno.actualizarAlumno(new Alumno(dni,id,apellido,nombre,fecha,estado), cambiar,ids);
                            }else
                                alumno.actualizarAlumno(new Alumno(dni,id,apellido,nombre,fecha,estado), cambiar);
                            id=0;dni=0;nombre="";apellido="";fecha=null;fechas="";estado=false;
                        }else 
                            System.err.println("\nNo se ingreso ningun atributo\n");
                    }else
                        System.err.println("\nNo se ha encontrado al alumno");
                    System.out.print("\n");
                    break;
                }
                case ("eliminar"): 
                case ("5"): {
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
                case ("cambiarestado"): 
                case ("6"): {
                    System.out.println("\n/// - Cambiar estado de alumno - /////");
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
                    if (alumno.buscarAlumno(id)!=null) {
                        estado = !(alumno.buscarAlumno(id).isEstado());
                        alumno.actualizarAlumno(new Alumno(0,id,null,null,null,estado), "estado");
                        System.out.println("\n"+alumno.buscarAlumno(id));
                    }else
                        System.err.println("\nNo se ha encontrado al alumno");
                    System.out.print("\n");
                    break;
                }
                case ("finalizar"): 
                case ("7"): {
                    seguir=false;
                }
            }
            
        }
        System.out.println("\n/// Gracias por usar el programa ///");
        
    }  
    
    
}
