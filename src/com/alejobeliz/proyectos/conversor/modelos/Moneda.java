package com.alejobeliz.proyectos.conversor.modelos;

import java.util.HashMap;

/**
 * La clase Moneda representa un conjunto de códigos de moneda y sus valores de conversión respectivos.
 */
public class Moneda {
    HashMap<String, Double> monedas; // Mapa que almacena los códigos de moneda y sus valores de conversión.



    /**
     * Constructor predeterminado que inicializa la instancia de Moneda con un nuevo HashMap vacío.
     */
    public Moneda() {
        monedas = new HashMap<>();
    }



    /**
     * Constructor que inicializa la instancia de Moneda con los valores proporcionados en un objeto MonedaRecord.
     * @param monedaRecord Objeto MonedaRecord que contiene los valores de conversión de las monedas.
     */
    public Moneda(MonedaRecord monedaRecord) {
        monedas = new HashMap<>();
        // Agregar los códigos de moneda y sus valores de conversión al HashMap.
        monedas.put("usd", monedaRecord.USD());
        monedas.put("eur", monedaRecord.EUR());
        monedas.put("ars", monedaRecord.ARS());
        monedas.put("cop", monedaRecord.COP());
        monedas.put("brl", monedaRecord.BRL());
        monedas.put("mxn", monedaRecord.MXN());
        monedas.put("pen", monedaRecord.PEN());
        monedas.put("ves", monedaRecord.VES());
        monedas.put("cad", monedaRecord.CAD());
    }



    /**
     * Obtiene el valor de conversión correspondiente al código de moneda especificado.
     * @param codigo Código de moneda cuyo valor de conversión se desea obtener.
     * @return El valor de conversión asociado al código de moneda especificado.
     * @throws IllegalArgumentException Si el código de moneda especificado no está presente en el conjunto.
     */
    public double obtenerValor(String codigo) {
        if (monedas.containsKey(codigo)) {
            return monedas.get(codigo);
        } else {
            throw new IllegalArgumentException("Error: ingrese un código de moneda válido.");
        }
    }
}