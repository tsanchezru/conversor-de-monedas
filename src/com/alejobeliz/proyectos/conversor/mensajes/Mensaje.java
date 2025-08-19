package com.alejobeliz.proyectos.conversor.mensajes;

/**
 * La clase Mensaje proporciona métodos para imprimir mensajes en la consola
 */
public class Mensaje {

    /**
     * Muestra un mensaje de bienvenida al usuario.
     */
    public void mostrarBienvenida() {
        System.out.println("Bienvenido al conversor de monedas de Alejo\n");
        System.out.println("Este conversor te permite obtener la equivalencia entre diferentes monedas en \n" +
                "tiempo real. Simplemente ingresa el código de la moneda de origen, el código de\n" +
                "la moneda de destino y la cantidad que deseas convertir, y te proporcionaremos el\n" +
                "resultado de la conversión.");
    }


    /**
     * Muestra un menú con las diferentes monedas disponibles para la conversión.
     */
    public void mostrarMenuMonedas() {
        System.out.println("Monedas\n");
        System.out.println("Dolar[USD]");
        System.out.println("Peso Argentino[ARS]");
        System.out.println("Euro[EUR]");
        System.out.println("Peso Colombiano[COP]");
        System.out.println("Real Brasileño[BRL]");
        System.out.println("Peso Mexicano[MXN]");
        System.out.println("Sol Peruano[PEN]");
        System.out.println("Bolívar Soberano Venezolano[VES]");
        System.out.println("Dólar Canadiense[CAD]");
        System.out.println("");
        System.out.println("Escriba \"menu\" para volver al Menu principal");
    }


    /**
     * Muestra el menú principal con las opciones disponibles para el usuario.
     */
    public void mostrarMenuPrincipal() {
        System.out.println("Menú principal\n");
        System.out.println("1.Realizar conversión");
        System.out.println("2.Imprimir historial por pantalla");
        System.out.println("3.Guardar el historial en archivo historial.txt");
        System.out.println("4.Crear un nuevo archivo historial.txt vacío");
        System.out.println("5.Mostrar directorio actual");
        System.out.println("6.Guardar los archivos historial.txt en otro directorio");
        System.out.println("7.Salir");

    }


    /**
     * Solicita al usuario que ingrese el código de la primera moneda.
     */
    public void pedirCodigoPrimeraMoneda() {
        System.out.print("Ingrese el código de la primer moneda: ");
    }



    /**
     * Solicita al usuario que ingrese el código de la segunda moneda para la conversión.
     */
    public void pedirCodigoSegundaMoneda() {
        System.out.print("Ingrese el código de la moneda a la cual desea hacer el cambio: ");
    }


    /**
     * Solicita al usuario que ingrese la cantidad a convertir.
     */
    public void ingresarCantidad() {
        System.out.print("Ingrese la cantidad: ");
    }



    /**
     * Formatea el resultado de la conversión para ser agregado al historial.
     */
    public String resultadoParaHistorial(double cantidad, String monedaBase, double resultadoConversion, String monedaCambio) {
        return cantidad + "[" + monedaBase.toUpperCase() + "] --> " + resultadoConversion + "[" + monedaCambio.toUpperCase() + "]";
    }


    /**
     * Formatea el resultado de la conversión para ser mostrado en pantalla.
     */
    public String resultadoParaPantalla(double cantidad, String monedaBase, double resultadoConversion, String monedaCambio) {
        return cantidad + "[" + monedaBase.toUpperCase() + "]" + (cantidad == 1 ? " equivale a " : " equivalen a ") + resultadoConversion + "[" + monedaCambio.toUpperCase() + "]";
    }


    /**
     * Solicita al usuario que ingrese la ruta del nuevo directorio o que vuelva al menú principal.
     */
    public void pedirNuevoDirectorio() {
        System.out.println("Ingress la ruta del nuevo directorio o escriba \"menu\" para volver al Menu principal: ");
    }


    /**
     * Muestra un mensaje de despedida al usuario.
     */
    public void despedida() {
        System.out.println("Gracias por utilizar el conversor de monedas.");
    }


    /**
     * Imprime un separador en la consola.
     */
    public void imprimirSeparador() {
        System.out.println("------------------------------------------------------------------------------------");
    }


    /**
     * Muestra un mensaje indicando que el historial ha sido guardado con éxito.
     */
    public void historialGuardado() {
        System.out.println("Historial guardado con exito.");
    }


    /**
     * Muestra un mensaje de error cuando no hay conversiones para guardar en el historial.
     */
    public void historialNoGuardado() {
        System.out.println("Error: no hay conversiones para guardar en el historial.");
    }


    /**
     * Muestra un mensaje indicando que se ha creado un nuevo archivo de historial.txt con éxito.
     */
    public void historialTxtCreado() {
        System.out.println("Archivo creado con exito.");
    }


    /**
     * Muestra un mensaje de error cuando se intenta crear un nuevo archivo de historial.txt sin conversiones en el historial actual.
     */
    public void historialTxtNoCreado() {
        System.out.println("Error: guarde al menos una conversión en el historial actual antes de crear otro historial.txt");
    }


    /**
     * Muestra un mensaje indicando los archivos encontrados en el directorio del proyecto.
     */
    public void archivosEncontradosEnDirectorio() {
        System.out.println("Archivos en el directorio del proyecto:\n");
    }


    /**
     * Muestra un mensaje de error cuando no se encuentran archivos historial.txt en el directorio actual.
     */
    public void archivosNoEncontradosEnDirectorio() {
        System.out.println("No se encontraron archivos historial.txt en el directorio actual");
    }


    /**
     * Muestra un mensaje indicando que el directorio ha sido cambiado con éxito.
     */
    public void directorioExistente() {
        System.out.println("Directorio cambiado con exito.");
    }


    /**
     * Muestra un mensaje de error cuando el directorio ingresado no existe.
     */
    public void directorioInexistente() {
        System.out.println("El directorio ingresado no existe.");
    }
}
