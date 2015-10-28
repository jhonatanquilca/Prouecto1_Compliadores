/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    String limpiar_pantalla = "cls";

    public String getLimpiar_pantalla() {
        return limpiar_pantalla;
    }

    public void setLimpiar_pantalla(String limpiar_pantalla) {
        this.limpiar_pantalla = limpiar_pantalla;
    }
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
    String crear_archivo = "crAr";
    String eliminar_archivo = "elAr";
    String renombrar_archivo = "rmAr";
    String buscar_archivo = "brAr";
    String editar_contenido_archivo = "editAr";

    public String getEditar_contenido_archivo() {
        return editar_contenido_archivo;
    }

    public void setEditar_contenido_archivo(String editar_contenido_archivo) {
        this.editar_contenido_archivo = editar_contenido_archivo;
    }

    File archivo;

    public String getDirectorioActual() {
//        archivo= new File(directorioActual);
//        archivo.setExecutable(true);
//        archivo.setReadable(true);
//        archivo.setWritable(true);
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

    public String crearCarpeta(String nombre) {
        carpeta = new File(this.getDirectorioActual() + "\\" + nombre);
        if (!carpeta.exists()) {
            try {
                System.out.println("Carpeta " + nombre + " creada con exito");
                carpeta.mkdir();
                return "Carpeta " + nombre + " creada con exito";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            System.out.println("La carpeta " + nombre + " ya existe");
            return "La carpeta " + nombre + " ya existe";

        }

    }

    public String renombrarCarpeta(String nombreAntiguio, String nombreNuevo) {
        File carpetaAntigua = new File(this.getDirectorioActual() + "\\" + nombreAntiguio);
        File carpetaNueva = new File(this.getDirectorioActual() + "\\" + nombreNuevo);
        if (carpetaNueva.exists()) {
            System.out.println("La carpeta ya " + nombreNuevo + " existe existe.");
            return "La carpeta ya " + nombreNuevo + " existe existe.";
        } else if (carpetaAntigua.exists()) {
            try {

                System.out.println("Se cambio el nombre de la carpeta " + nombreAntiguio + " a " + nombreNuevo + ".");
                carpetaAntigua.renameTo(carpetaNueva);
                return "Se cambio el nombre de la carpeta " + nombreAntiguio + " a " + nombreNuevo + ".";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            System.out.println("La carpeta que quiere renombrar no existe.");
            return "La carpeta que quiere renombrar no existe.";
        }

    }

    public String eliminarCarpeta(String nombre) {
        carpeta = new File(this.getDirectorioActual() + "\\" + nombre);
        if (carpeta.exists()) {
            try {

                System.out.println("Se elimino la carpeta " + nombre + ".");
                carpeta.delete();
                return "Se elimino la carpeta " + nombre + ".";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            System.out.println("La carpeta que quiere eliminar no existe.");
            return "La carpeta que quiere eliminar no existe.";
        }

    }

    public String listarDirectorio() {
        String direcctorio = getDirectorioActual();
        carpeta = new File(direcctorio);
        String[] directorio = carpeta.list();

        if (carpeta.exists()) {
            if (directorio.length != 0) {
                String resp = "";
                for (int x = 0; x < directorio.length; x++) {
                    System.out.println(directorio[x]);

//                         File actual = carpeta.getAbsoluteFile();
//                    System.out.println(actual.isDirectory()+"asd");
//                    System.out.print(directorio[x]);
                    resp += directorio[x] + "\n";
                }
                return resp;
            } else {
                System.out.println("El directorio esta Vacio.");
                return "El directorio esta Vacio.";
            }
        } else {
            System.out.println("El direcctorio no exixte.");
            return "El direcctorio no exixte.";
        }
    }

    public String buscarDirectorio(String nombre) {
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
                    String resp = "";
                    System.out.println("Coincidencias:");
                    for (int x = 0; x < directorio.length; x++) {
                        busqueda = directorio[x].contains(nombre);
                        if (busqueda) {
                            System.out.println(directorio[x]);
                            resp += directorio[x] + "\n";
                        }
                    }
                    return resp;
                } else {
                    System.out.println("No hay coincidencias.");
                    return "No hay coincidencias.";

                }

            } else {
                System.out.println("El directorio esta Vacio.");
                return "No hay coincidencias.";

            }
        } else {
            System.out.println("El direcctorio no exixte.");
            return "No hay coincidencias.";
        }
    }

//    public void regresarUnDirectorio() {
    public String regresarUnDirectorio() {
        String direccion = getDirectorioActual();

        if (direccion.lastIndexOf("\\") == -1) {
            setDirectorioActual(getDirectorioActual() + "\\");
//            return getDirectorioActual();
            return "";
        } else {
            setDirectorioActual(direccion.substring(0, direccion.lastIndexOf("\\")) + (direccion.lastIndexOf("\\") == 2 ? "\\" : ""));
            return "";
        }
    }

    public String avanzarUnDirectorio(String nombre) {
        carpeta = new File(getDirectorioActual() + "\\" + nombre);
        String direccion = getDirectorioActual();
        if (carpeta.exists()) {
            setDirectorioActual(carpeta.getAbsolutePath());
//            return getDirectorioActual();
            return "";
        } else {
            System.out.println("El directorio al que trata de acceder no existe.");
            return "El directorio al que trata de acceder no existe.";
        }

    }

    public String crearArchivo(String nombre) {
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
                return "Archivo " + nombre + " creado con exito";
            } catch (FileNotFoundException fe) {
                System.out.println("error al crear el archivo ! " + fe.getMessage());
                return "error al crear el archivo ! " + fe.getMessage();
            } catch (IOException ex) {
                Logger.getLogger(Comandos.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
        } else {
            System.out.println("Error!. El Archivo " + nombre + " ya existe.");
            return "Error!. El Archivo " + nombre + " ya existe.";

        }

    }

    public String renombrarArchivo(String nombreAntiguo, String nombreNuevo) {
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
            return "El archivo " + nombreNuevo + " ya existe.";
        } else if (archivoAntiguo.exists()) {
            try {
                archivoAntiguo.renameTo(archivoNuevo);
                bw = new BufferedWriter(new FileWriter(archivoNuevo));
                bw.close();
                System.out.println("Se cambio el nombre del archivo " + nombreAntiguo + " a " + nombreNuevo + ".");
                return "Se cambio el nombre del archivo " + nombreAntiguo + " a " + nombreNuevo + ".";
            } catch (FileNotFoundException fe) {
                System.out.println("error al renombrar el archivo ! " + fe.getMessage());
                return "error al renombrar el archivo ! " + fe.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            System.out.println("La carpeta que quiere renombrar no existe.");
            return "La carpeta que quiere renombrar no existe.";
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
        } catch (FileNotFoundException fe) {
            System.out.println("error al editar el archivo ! " + fe.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Comandos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String eliminarArchivo(String nombre) {
        archivo = new File(this.getDirectorioActual() + "\\" + nombre);
        System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            try {

                System.out.println("Se elimino el archivo " + nombre + ".");
                archivo.delete();
                return "Se elimino el archivo " + nombre + ".";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            System.out.println("El archivo que quiere eliminar no existe.");
            return "El archivo que quiere eliminar no existe.";
        }

    }

//    public void buscarArchivo(String nombre) {
    public String buscarArchivo(String nombre) {
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
                    String resp = "";
                    System.out.println("Coincidencias:");
                    for (int x = 0; x < directorio.length; x++) {
                        busqueda = directorio[x].contains(nombre);
                        if (busqueda) {
                            System.out.println(directorio[x]);
                            resp += directorio[x] + "\n";
                        }
                    }
                    return resp;
                } else {
                    System.out.println("No hay coincidencias.");
                    return "No hay coincidencias.";
                }

            } else {
                System.out.println("El archivo no fue encontrado.");
                return "El archivo no fue encontrado.";

            }
        } else {
            System.out.println("El archivo no existe.");
            return "No hay coincidencias.";

        }
    }

    private String getContentArchivo(String nombre) {
        archivo = new File(this.getDirectorioActual() + "\\" + nombre);
        System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            try {

                File archivo = null;
                FileReader fr = null;
                BufferedReader br = null;

//                try {
//         // Apertura del fichero y creacion de BufferedReader para poder
//                    // hacer una lectura comoda (disponer del metodo readLine()).
//                    archivo = new File("C:\\archivo.txt");
//                    fr = new FileReader(archivo);
//                    br = new BufferedReader(fr);
//
//                    // Lectura del fichero
//                    String linea;
//                    while ((linea = br.readLine()) != null) {
//                        System.out.println(linea);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//         // En el finally cerramos el fichero, para asegurarnos
//                    // que se cierra tanto si todo va bien como si salta 
//                    // una excepcion.
//                    try {
//                        if (null != fr) {
//                            fr.close();
//                        }
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                }

                System.out.println(archivo.toString());
                BufferedReader bf = new BufferedReader(new FileReader(archivo));
                String sCadena = "";
                while ((sCadena = bf.readLine()) != null) {

                    sCadena += sCadena + "";
                }
                bf.close();

//                if (sCadena.equals("")) {
//                    return "Presione Clt+g para guardar.\n"
//                            + "Presione Clt+X para salir sin guardar.\n"
//                            + "---------------------------";
//                } else {
                return "Presione Clt+g para guardar.\n"
                        + "Presione Clt+X para salir sin guardar.\n"
                        + "---------------------------\n" + sCadena;
//                }

            } catch (NullPointerException e) {
                return "Presione Clt+g para guardar.\n"
                        + "Presione Clt+X para salir sin guardar.\n"
                        + "---------------------------";
            } catch (Exception e) {
                e.printStackTrace();
                return "error!";
            }
        } else {
            System.out.println("El archivo que quiere editar no existe.");
            return "El archivo que quiere editar no existe.";
        }
    }

    public String ejecutarComado(String comando, String parametro1, String parametro2) {
        if (comando.equals(this.renombrar_directorio)) {
            return renombrarCarpeta(parametro1, parametro2);
        } else if (comando.equals(this.renombrar_archivo)) {
            return renombrarArchivo(parametro1, parametro2);
        } else {
            System.out.println("\"" + comando + " " + parametro1 + " " + parametro1 + "\" no se reconoce como un comando o no es correcto.");
            return "\"" + comando + " " + parametro1 + " " + parametro1 + "\" no se reconoce como un comando o no es correcto.";
        }
    }

    public String ejecutarComado(String comando, String nombre) {
        if (comando.equals(this.crear_directorio)) {
            return crearCarpeta(nombre);
        } else if (comando.equals(this.editar_contenido_archivo)) {
            String arContent = getContentArchivo(nombre);
            return arContent;
        } else if (comando.equals(this.crear_archivo)) {
            return crearArchivo(nombre);
        } else if (comando.equals(this.eliminar_directorio)) {
            return eliminarCarpeta(nombre);
        } else if (comando.equals(this.eliminar_archivo)) {
            return eliminarArchivo(nombre);
        } else if (comando.equals(this.buscar_directorio)) {
            return buscarDirectorio(nombre);
        } else if (comando.equals(this.buscar_archivo)) {
            return buscarArchivo(nombre);
        } else if (comando.equals(this.avanzar_directorio)) {
            return avanzarUnDirectorio(nombre);
        } else {
            System.out.println("\"" + comando + " " + nombre + "\" no se reconoce como un comando o no es correcto.");
            return "\"" + comando + " " + nombre + "\" no se reconoce como un comando o no es correcto.";

        }
    }

//    public void ejecutarComado(String comando) {
    public String ejecutarComado(String comando) {
        if (comando.equals(this.listar_directorio)) {
            return listarDirectorio();
        } else if (comando.equals(this.avanzar_directorio)) {
            System.out.println(getDirectorioActual());
//            return getDirectorioActual();
            return "";
        } else if (comando.equals(this.regresar_directorio)) {
            return regresarUnDirectorio();

        } else if (comando.equals(this.limpiar_pantalla)) {
            return this.limpiar_pantalla;

        } else {
            System.out.println();
            return "\"" + comando + "\" no se reconoce como un comando.";
        }
    }

}
