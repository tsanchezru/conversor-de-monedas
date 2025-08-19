package com.alejobeliz.proyectos.conversor.modelos;

import com.alejobeliz.proyectos.conversor.api.PeticionApi;

import java.io.IOException;


public class Conversor {
    public double convertir(double monedaUno, double monedaDos, double cantidad) {
        double resultado = cantidad * (monedaDos / monedaUno);
        resultado = resultado * 100;
        resultado = Math.floor(resultado);
        resultado = resultado / 100;
        return resultado;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Conversor conversor = new Conversor();
        PeticionApi peticionApi = new PeticionApi();
        MonedaRecord monedaRecord = peticionApi.generarPeticion();
        Moneda moneda = new Moneda(monedaRecord);
        double resultado = conversor.convertir(moneda.obtenerValor("usd"), moneda.obtenerValor("ars"), 1);
        System.out.println(resultado);
    }
}
