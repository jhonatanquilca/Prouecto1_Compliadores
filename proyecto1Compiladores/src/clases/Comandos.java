/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Administrador
 */
public class Comandos {
//    https://sekthdroid.wordpress.com/2013/02/01/navegacion-de-archivos-y-io-en-java/
//    http://censorcosmico.blogspot.com/2012/04/navegar-por-los-directorios.html

    String regresar_directorio = "cd..";
    String avanzar_directorio = "cd";
    String crear_directorio = "crDir";
    String eliminar_directorio = "elDir";
    String renombrar_directorio = "rmDir";
    String listar_directorio = "listDir";
    String buscar_directorio = "buscarDir";
    String directorioActual;
    File carpeta;

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
                    busqueda = directorio[x].contains(nombre)||directorio[x].toLowerCase().contains(nombre.toLowerCase());
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

    public void ejecutarComado(String comando, String parametro1, String parametro2) {
        if (comando.equals(this.renombrar_directorio)) {
            renombrarCarpeta(parametro1, parametro2);
        } else {
            System.out.println("\"" + comando + " " + parametro1 + " " + parametro1 + "\" no se reconoce como un comando o no es correcto.");

        }
    }

    public void ejecutarComado(String comando, String nombre) {
        if (comando.equals(this.crear_directorio)) {
            crearCarpeta(nombre);
        } else if (comando.equals(this.eliminar_directorio)) {
            eliminarCarpeta(nombre);
        } else if (comando.equals(this.buscar_directorio)) {
            buscarDirectorio(nombre);
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
