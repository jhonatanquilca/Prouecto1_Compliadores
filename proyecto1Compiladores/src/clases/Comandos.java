/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Comandos {
//    https://sekthdroid.wordpress.com/2013/02/01/navegacion-de-archivos-y-io-en-java/
//    http://censorcosmico.blogspot.com/2012/04/navegar-por-los-directorios.html

    //variables para manejo de carpetas
    String regresar_directorio = "cd..";
    String avanzar_directorio = "cd";
    String crear_directorio = "crDir";
    String eliminar_directorio = "elDir";
    String renombrar_directorio = "rmDir";
    String listar_directorio = "listDir";
    String buscar_directorio = "brDir";
    String directorioActual;
    File carpeta;

    //variables para manejo de archivos
    String crear_archivo = "crArch";
    String eliminar_archivo = "elArch";
    String renombrar_archivo = "rmArch";
    String buscar_archivo = "brArchivo";
    File archivo;

    public String getDirectorioActual() {
        return directorioActual;
    }

    public void setDirectorioActual(String directorioActual) {
        this.directorioActual = directorioActual;
    }

    public Comandos() {
        File miDir = new File(".");
        try {
            setDirectorioActual(miDir.getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean crearCarpeta(String nombre) {
        carpeta = new File(this.getDirectorioActual() + "\\" + nombre);
        if (!carpeta.exists()) {
            try {
                System.out.println("Carpeta " + nombre + " creada con exito;");
                return carpeta.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La carpeta " + nombre + " ya existe");

        }

        return false;
    }

    public boolean renombrarCarpeta(String nombreAntiguio, String nombreNuevo) {
        File carpetaAntigua = new File(this.getDirectorioActual() + "\\" + nombreAntiguio);
        File carpetaNueva = new File(this.getDirectorioActual() + "\\" + nombreNuevo);
        if (carpetaNueva.exists()) {
            System.out.println("La carpeta ya " + nombreNuevo + " existe existe.");
            return false;
        } else if (carpetaAntigua.exists()) {
            try {

                System.out.println("Se cambio el nombre de la carpeta " + nombreAntiguio + " a " + nombreNuevo + ".");
                return carpetaAntigua.renameTo(carpetaNueva);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La carpeta que quiere renombrar no existe.");
        }

        return false;
    }

    public boolean eliminarCarpeta(String nombre) {
        carpeta = new File(this.getDirectorioActual() + "\\" + nombre);
        if (carpeta.exists()) {
            try {

                System.out.println("Se elimino la carpeta " + nombre + ".");
                return carpeta.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La carpeta que quiere eliminar no existe.");
        }

        return false;
    }

    public void listarDirectorio() {
        String direcctorio = getDirectorioActual();
        carpeta = new File(direcctorio);
        String[] directorio = carpeta.list();

        if (carpeta.exists()) {
            if (directorio.length != 0) {
                for (int x = 0; x < directorio.length; x++) {
                    System.out.println(directorio[x]);
//                         File actual = carpeta.getAbsoluteFile();
//                    System.out.println(actual.isDirectory()+"asd");
//                    System.out.print(directorio[x]);
                }
            } else {
                System.out.println("El directorio esta Vacio.");
            }
        } else {
            System.out.println("El direcctorio no exixte.");
        }
    }

    public void buscarDirectorio(String nombre) {
        String direcctorio = getDirectorioActual();
        carpeta = new File(direcctorio);
        String[] directorio = carpeta.list();

        if (carpeta.exists()) {
            if (directorio != null) {
                Boolean busqueda = false;
                for (int x = 0; x < directorio.length; x++) {
                    busqueda = directorio[x].contains(nombre) || directorio[x].toLowerCase().contains(nombre.toLowerCase());
                    if (busqueda) {
                        break;
                    }
                }
                if (busqueda) {
                    System.out.println("Coincidencias:");
                    for (int x = 0; x < directorio.length; x++) {
                        busqueda = directorio[x].contains(nombre);
                        if (busqueda) {
                            System.out.println(directorio[x]);

                        }
                    }
                } else {
                    System.out.println("No hay coincidencias.");

                }

            } else {
                System.out.println("El directorio esta Vacio.");
            }
        } else {
            System.out.println("El direcctorio no exixte.");
        }
    }

    public void regresarUnDirectorio() {
        String direccion = getDirectorioActual();

        if (direccion.lastIndexOf("\\") == -1) {
            setDirectorioActual(getDirectorioActual() + "\\");
        } else {
            setDirectorioActual(direccion.substring(0, direccion.lastIndexOf("\\")) + (direccion.lastIndexOf("\\") == 2 ? "\\" : ""));
        }
    }

    public void avanzarUnDirectorio(String nombre) {
        carpeta = new File(getDirectorioActual() + "\\" + nombre);
        String direccion = getDirectorioActual();
        if (carpeta.exists()) {
            setDirectorioActual(carpeta.getAbsolutePath());
        } else {
            System.out.println("El directorio al que trata de acceder no existe.");
        }

    }

    public void crearArchivo(String nombre) {
        if (!nombre.contains(".txt")) {
            nombre = nombre + ".txt";
        }

        archivo = new File(getDirectorioActual() + "\\" + nombre);
        BufferedWriter bw;
        if (!archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.close();
                System.out.println("Archivo " + nombre + " creado con exito");
            } catch (IOException ex) {
                Logger.getLogger(Comandos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Error!. El Archivo " + nombre + " ya existe.");

        }

    }

    public void renombrarArchivo(String nombreAntiguo, String nombreNuevo) {
        if (!nombreAntiguo.contains(".txt")) {
            nombreAntiguo = nombreAntiguo + ".txt";
        }
        if (!nombreNuevo.contains(".txt")) {
            nombreNuevo = nombreNuevo + ".txt";
        }
        File archivoAntiguo = new File(getDirectorioActual() + "\\" + nombreAntiguo);
        File archivoNuevo = new File(getDirectorioActual() + "\\" + nombreNuevo);
        BufferedWriter bw1;
        BufferedWriter bw;

        if (archivoNuevo.exists()) {
            System.out.println("El archivo " + nombreNuevo + " ya existe.");
        } else if (archivoAntiguo.exists()) {
            try {
                archivoAntiguo.renameTo(archivoNuevo);
                bw = new BufferedWriter(new FileWriter(archivoNuevo));
                bw.close();
                System.out.println("Se cambio el nombre del archivo " + nombreAntiguo + " a " + nombreNuevo + ".");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La carpeta que quiere renombrar no existe.");
        }

    }

    public void editarArchivo(String nombre, String texto) {
        if (!nombre.contains(".txt")) {
            nombre = nombre + ".txt";
        }
        try {
            File archivo = new File(getDirectorioActual() + "\\" + nombre);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(texto);
            bw.close();
            System.out.println("Archivo Editado");
        } catch (IOException ex) {
            Logger.getLogger(Comandos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarArchivo(String nombre) {
        archivo = new File(this.getDirectorioActual() + "\\" + nombre);
        System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            try {

                System.out.println("Se elimino el archivo " + nombre + ".");
                archivo.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo que quiere eliminar no existe.");
        }

    }

    public void buscarArchivo(String nombre) {
        String direcctorio = getDirectorioActual();
        archivo = new File(direcctorio);
        String[] directorio = archivo.list();

        if (archivo.exists()) {
            if (directorio != null) {
                Boolean busqueda = false;
                for (int x = 0; x < directorio.length; x++) {
                    busqueda = directorio[x].contains(nombre) || directorio[x].toLowerCase().contains(nombre.toLowerCase());
                    if (busqueda) {
                        break;
                    }
                }
                if (busqueda) {
                    System.out.println("Coincidencias:");
                    for (int x = 0; x < directorio.length; x++) {
                        busqueda = directorio[x].contains(nombre);
                        if (busqueda) {
                            System.out.println(directorio[x]);

                        }
                    }
                } else {
                    System.out.println("No hay coincidencias.");

                }

            } else {
                System.out.println("El archivo no fue encontrado.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }

    public void ejecutarComado(String comando, String parametro1, String parametro2) {
        if (comando.equals(this.renombrar_directorio)) {
            renombrarCarpeta(parametro1, parametro2);
        } else if (comando.equals(this.renombrar_archivo)) {
            renombrarArchivo(parametro1, parametro2);
        } else {
            System.out.println("\"" + comando + " " + parametro1 + " " + parametro1 + "\" no se reconoce como un comando o no es correcto.");

        }
    }

    public void ejecutarComado(String comando, String nombre) {
        if (comando.equals(this.crear_directorio)) {
            crearCarpeta(nombre);
        } else if (comando.equals(this.crear_archivo)) {
            crearArchivo(nombre);
        } else if (comando.equals(this.eliminar_directorio)) {
            eliminarCarpeta(nombre);
        } else if (comando.equals(this.eliminar_archivo)) {
            eliminarArchivo(nombre);
        } else if (comando.equals(this.buscar_directorio)) {
            buscarDirectorio(nombre);
        } else if (comando.equals(this.buscar_archivo)) {
            buscarArchivo(nombre);
        } else if (comando.equals(this.avanzar_directorio)) {
            avanzarUnDirectorio(nombre);
        } else {
            System.out.println("\"" + comando + " " + nombre + "\" no se reconoce como un comando o no es correcto.");

        }
    }

    public void ejecutarComado(String comando) {
        if (comando.equals(this.listar_directorio)) {
            listarDirectorio();
        } else if (comando.equals(this.avanzar_directorio)) {
            System.out.println(getDirectorioActual());
        } else if (comando.equals(this.regresar_directorio)) {
            regresarUnDirectorio();
        } else {
            System.out.println("\"" + comando + "\" no se reconoce como un comando.");
        }
    }

}
