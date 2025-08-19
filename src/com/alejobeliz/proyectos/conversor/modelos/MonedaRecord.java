package com.alejobeliz.proyectos.conversor.modelos;

/**
 * Un record que representa los valores de conversión de varias monedas en relación con una moneda base USD.
 */
public record MonedaRecord(
        double USD, // Valor de conversión con respecto al dólar estadounidense (USD).
        double EUR, // Valor de conversión con respecto al euro (EUR).
        double ARS, // Valor de conversión con respecto al peso argentino (ARS).
        double COP, // Valor de conversión con respecto al peso colombiano (COP).
        double BRL, // Valor de conversión con respecto al real brasileño (BRL).
        double MXN, // Valor de conversión con respecto al peso mexicano (MXN).
        double PEN, // Valor de conversión con respecto al sol peruano (PEN).
        double VES, // Valor de conversión con respecto al bolívar venezolano (VES).
        double CAD  // Valor de conversión con respecto al dólar canadiense (CAD).
) {

}
