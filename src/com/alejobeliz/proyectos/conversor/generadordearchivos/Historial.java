package com.alejobeliz.proyectos.conversor.generadordearchivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La clase Historial se utiliza para mantener un registro de las conversiones realizadas y gestionar los archivos de historial.
 */
public class Historial {
    // Lista que almacena las conversiones realizadas en el historial.
    private List<String> listaConversionesHistorial;
    // Lista que contiene los nombres de los archivos de historial presentes en el directorio.
    private List<String> archivosHistorialEnDirectorio;
    // Nombre del archivo de historial actual.
    private String nombreArchivoHistorialActual;
    // Directorio donde se guardan los archivos de historial.
    private String directorioArchivosHistorial;



    /**
     * Constructor de la clase Historial. Inicializa las listas y configura el nombre del archivo de historial y el directorio.
     */
    public Historial() {
        listaConversionesHistorial = new ArrayList<>();
        archivosHistorialEnDirectorio = new ArrayList<>();
        // Genera el nombre del archivo de historial actual con la fecha actual.
        nombreArchivoHistorialActual = "historial_" + generadorFechaActual() + ".txt";
        // Obtiene el directorio actual del sistema.
        directorioArchivosHistorial = System.getProperty("user.dir");
    }



    /**
     * Agrega una nueva conversión al historial.
     *
     * @param conversion La conversión a agregar al historial.
     */
    public void agregarAListaHistorial(String conversion) {
        // Agrega la conversión junto con la fecha actual a la lista de conversiones.
        listaConversionesHistorial.add(conversion + "\t\t\t\t" + generadorFechaActual());
    }



    /**
     * Genera una cadena de texto con la fecha actual en el formato especificado.
     *
     * @return La fecha actual formateada como una cadena de texto.
     */
    public String generadorFechaActual() {
        Date date = new Date();
        SimpleDateFormat formateadorDeFecha = new SimpleDateFormat("dd-MM-y-(HH-mm-ss)");
        return formateadorDeFecha.format(date);
    }



    /**
     * Guarda el historial actual en un archivo de texto en el directorio especificado.
     *
     * @return true si el historial se guardó correctamente, false si el historial está vacío.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public boolean guardarHistorialTxt() throws IOException {
        if (listaConversionesHistorial.isEmpty()) {
            return false; // No se puede guardar un historial vacío.
        } else {
            // Abre el archivo en modo de escritura (sobrescribiendo el contenido existente).
            FileWriter escritura = new FileWriter(directorioArchivosHistorial + "/" + nombreArchivoHistorialActual);

            // Escribe el contenido de listaConversionesHistorial en el archivo.
            for (String item : listaConversionesHistorial) {
                escritura.write(item + "\n");
            }

            // Cierra el escritor.
            escritura.close();
            return true;
        }
    }



    /**
     * Crea un nuevo historial, asigna el nombre del archivo de historial y limpia la lista de conversiones.
     *
     * @return true si se generó un nuevo historial correctamente, false si el historial actual está vacío.
     * @throws IOException Si ocurre un error durante la escritura del nuevo archivo de historial.
     */
    public boolean generarNuevoHistorial() throws IOException {
        File archivo = new File(directorioArchivosHistorial + "/" + nombreArchivoHistorialActual);
        if (archivo.length() == 0) {
            return false; // No se puede generar un nuevo historial si el historial actual está vacío.
        } else {
            // Genera el nombre del nuevo archivo de historial con la fecha actual.
            String nombreArchivoHistorial = "historial_" + generadorFechaActual() + ".txt";

            // Abre el nuevo archivo en modo de escritura (agregando texto al final del archivo).
            FileWriter escritura = new FileWriter(directorioArchivosHistorial + "/" + nombreArchivoHistorial, true);

            // Asigna el nombre del nuevo archivo de historial y limpia la lista de conversiones.
            this.nombreArchivoHistorialActual = nombreArchivoHistorial;
            this.listaConversionesHistorial.clear();

            // Cierra el escritor.
            escritura.close();
            return true;
        }
    }



    /**
     * Establece el directorio donde se guardarán los archivos de historial.
     *
     * @param nuevoDirectorio El nuevo directorio para los archivos de historial.
     * @return true si se estableció el directorio correctamente, false si el directorio no existe o no es válido.
     */
    public boolean setDirectorioActual(String nuevoDirectorio) {
        File directorio = new File(nuevoDirectorio);
        if (directorio.exists() && directorio.isDirectory()) {
            this.directorioArchivosHistorial = nuevoDirectorio;
            return true;
        } else {
            return false; // El directorio especificado no existe o no es válido.
        }
    }



    /**
     * Obtiene el directorio actual donde se guardan los archivos de historial.
     *
     * @return El directorio actual de los archivos de historial.
     */
    public String getDirectorioActual() {
        return directorioArchivosHistorial;
    }



    /**
     * Agrega los nombres de los archivos de historial presentes en el directorio a la lista de archivos.
     *
     * @return true si se encontraron archivos de historial en el directorio, false si no se encontraron archivos.
     */
    public boolean agregarHistorialesTxtALista() {
        // Obtener la lista de archivos en el directorio de historiales.
        File carpetaProyecto = new File(directorioArchivosHistorial);
        File[] archivos = carpetaProyecto.listFiles();

        if (archivos != null) {
            // Recorrer todos los archivos en el directorio.
            for (File archivo : archivos) {
                // Verificar si el archivo es un archivo regular y comienza con el prefijo "historial_".
                if (archivo.isFile() && archivo.getName().startsWith("historial_") && !archivosHistorialEnDirectorio.contains(archivo.getName())) {
                    // Agregar el nombre del archivo a la lista de archivos de historial.
                    archivosHistorialEnDirectorio.add(archivo.getName());
                }
            }
            return true; // Se encontraron archivos de historial en el directorio.
        }
        return false; // No se encontraron archivos de historial en el directorio o el directorio no existe.
    }



    /**
     * Imprime los nombres de los archivos de historial presentes en el directorio.
     *
     * @return true si hay archivos de historial para imprimir, false si no hay archivos.
     */
    public boolean imprimirHistorialTxt() {
        if (archivosHistorialEnDirectorio.isEmpty()) {
            return false; // No hay archivos de historial para imprimir.
        }
        // Imprimir los nombres de los archivos de historial con un formato enumerado.
        for (int i = 0; i < archivosHistorialEnDirectorio.size(); i++) {
            System.out.println(i + 1 + "-" + archivosHistorialEnDirectorio.get(i));
        }
        return true; // Se imprimieron los nombres de los archivos de historial.
    }



    /**
     * Imprime el historial de conversiones almacenado en la lista.
     */
    public void imprimirHistorial() {
        System.out.println("Historial:");
        if (listaConversionesHistorial.isEmpty()) {
            System.out.println("Historial vacio"); // Imprimir mensaje si el historial está vacío.
            return;
        }
        // Imprimir cada elemento del historial de conversiones.
        for (String item : listaConversionesHistorial) {
            System.out.println(item);
        }
    }

}
