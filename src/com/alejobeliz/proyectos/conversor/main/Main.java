package com.alejobeliz.proyectos.conversor.main;

import com.alejobeliz.proyectos.conversor.api.PeticionApi;
import com.alejobeliz.proyectos.conversor.generadordearchivos.Historial;
import com.alejobeliz.proyectos.conversor.mensajes.Mensaje;
import com.alejobeliz.proyectos.conversor.modelos.Conversor;
import com.alejobeliz.proyectos.conversor.modelos.Moneda;
import com.alejobeliz.proyectos.conversor.modelos.MonedaRecord;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * La clase Main es la entrada principal del programa.
 * Proporciona un menú interactivo para que el usuario realice conversiones de moneda,
 * gestione el historial y realice otras operaciones relacionadas con el conversor.
 */
public class Main {

    /**
     * El método main es el punto de entrada del programa.
     *
     * @throws IOException            Si ocurre un error de entrada o salida.
     * @throws InterruptedException   Si la ejecución del hilo se interrumpe.
     * @throws SSLHandshakeException Si ocurre un error de handshake SSL durante la comunicación con la API.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(Locale.US); // Establecer el Locale adecuado para usar punto como separador decimal
        Scanner scanner = new Scanner(System.in);
        Mensaje mensaje = new Mensaje();
        Historial historial = new Historial();
        try {
            PeticionApi peticionApi = new PeticionApi();

            // Obtener los ratios de conversión de la API
            MonedaRecord monedaRecord = peticionApi.generarPeticion();
            Moneda moneda = new Moneda(monedaRecord);
            Conversor conversor = new Conversor();

            mensaje.imprimirSeparador();
            mensaje.mostrarBienvenida();

            // Ciclo principal para mostrar el menú y procesar las opciones del usuario
            while (true) {
                try {
                    mensaje.imprimirSeparador();
                    mensaje.mostrarMenuPrincipal();
                    mensaje.imprimirSeparador();
                    System.out.print("Elija una opción: ");

                    int opcion;
                    try {
                        opcion = scanner.nextInt();
                        mensaje.imprimirSeparador();
                        scanner.nextLine(); // Limpiar el buffer del scanner
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese un número válido.");
                        scanner.nextLine(); // Limpiar el buffer del scanner
                        continue;
                    }

                    switch (opcion) {
                        case 1:
                            // Realizar conversión de moneda
                            mensaje.mostrarMenuMonedas();
                            mensaje.imprimirSeparador();
                            try {
                                // Obtener los códigos de las monedas y la cantidad a convertir
                                mensaje.pedirCodigoPrimeraMoneda();
                                String monedaBase = scanner.nextLine().toLowerCase();

                                if (monedaBase.equals("menu")) {
                                    break;
                                }
                                mensaje.pedirCodigoSegundaMoneda();
                                String monedaCambio = scanner.nextLine().toLowerCase();

                                if (monedaCambio.equals("menu")) {
                                    break;
                                }

                                double cantidad;
                                do {
                                    try {
                                        mensaje.ingresarCantidad();
                                        cantidad = scanner.nextDouble();
                                        scanner.nextLine();
                                    } catch (InputMismatchException e) {
                                        scanner.nextLine();
                                        System.out.println("Error: Ingrese una cantidad válida");
                                        cantidad = -1;
                                    }
                                } while (cantidad < 0);

                                // Realizar la conversión
                                double ratioConversionMonedaBase = moneda.obtenerValor(monedaBase);
                                double ratioConversionMonedaCambio = moneda.obtenerValor(monedaCambio);
                                double resultadoConversion = conversor.convertir(ratioConversionMonedaBase, ratioConversionMonedaCambio, cantidad);

                                // Agregar la conversión al historial
                                String resultadoParaHistorial = mensaje.resultadoParaHistorial(cantidad, monedaBase, resultadoConversion, monedaCambio);
                                historial.agregarAListaHistorial(resultadoParaHistorial);

                                // Mostrar el resultado al usuario
                                String resultado = mensaje.resultadoParaPantalla(cantidad, monedaBase, resultadoConversion, monedaCambio);
                                System.out.println("\n" + resultado);
                            } catch (IllegalArgumentException e) {
                                mensaje.imprimirSeparador();
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            // Imprimir el historial de conversiones en pantalla
                            historial.imprimirHistorial();
                            break;
                        case 3:
                            // Guardar el historial en un archivo historial.txt
                            if (historial.guardarHistorialTxt()) {
                                mensaje.historialGuardado();
                            } else {
                                mensaje.historialNoGuardado();
                            }
                            break;
                        case 4:
                            // Crear un nuevo archivo historial.txt vacío
                            if (historial.generarNuevoHistorial()) {
                                mensaje.historialTxtCreado();
                            } else {
                                mensaje.historialTxtNoCreado();
                            }
                            break;
                        case 5:
                            // Mostrar el directorio actual
                            System.out.println(historial.getDirectorioActual());
                            break;
                        case 6:
                            // Guardar los archivos historial.txt en otro directorio
                            mensaje.pedirNuevoDirectorio();
                            String nuevoDirectorio = scanner.nextLine();
                            if (nuevoDirectorio.equals("menu")) {
                                break;
                            }
                            mensaje.imprimirSeparador();
                            if (!historial.setDirectorioActual(nuevoDirectorio)) {
                                mensaje.directorioInexistente();
                            } else {
                                mensaje.directorioExistente();
                                if (historial.agregarHistorialesTxtALista()) {
                                    mensaje.archivosEncontradosEnDirectorio();
                                    historial.imprimirHistorialTxt();
                                } else {
                                    mensaje.archivosNoEncontradosEnDirectorio();
                                }
                            }
                            break;
                        case 7:
                            // Salir del programa
                            mensaje.despedida();
                            scanner.close();
                            System.exit(0);
                        default:
                            System.out.println("Error: Ingrese una opción válida.");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SSLHandshakeException e) {
            // Manejar errores de conexión a la API
            System.out.println("Error: Conéctese a internet para poder realizar conversiones");
        }
    }
}
