package com.alejobeliz.proyectos.conversor.api;

import com.alejobeliz.proyectos.conversor.modelos.MonedaRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * La clase PeticionApi se encarga de enviar una solicitud HTTP a una API externa
 * para obtener los valores de conversión de varias monedas con respecto al dólar estadounidense (USD).
 */
public class PeticionApi {



    /**
     * Genera una solicitud a la API externa para obtener los valores de conversión de monedas.
     * @return Un objeto MonedaRecord que contiene los valores de conversión de monedas con respecto al USD.
     * @throws IOException Si ocurre un error de entrada/salida durante la solicitud HTTP.
     * @throws InterruptedException Si la ejecución es interrumpida mientras se espera la respuesta de la solicitud HTTP.
     */
    public MonedaRecord generarPeticion() throws IOException, InterruptedException {
        String apiKey = "dc518dff17d7c34af01c2aea";

        // Establecer la URI de la API con la clave de la API proporcionada y USD como moneda base.
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD");

        // Crear un cliente HTTP para enviar la solicitud.
        HttpClient client = HttpClient.newHttpClient();

        // Construir la solicitud HTTP GET.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        // Enviar la solicitud y esperar la respuesta.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Crear un objeto Gson para analizar la respuesta JSON.
        Gson gson = new Gson();

        // Convertir la respuesta JSON en un objeto JsonObject.
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        // Obtener la parte de la respuesta que contiene los ratios de conversión de monedas.
        String ratiosConversion = jsonObject.getAsJsonObject().get("conversion_rates").toString();

        // Convertir los ratios de conversión en un objeto MonedaRecord.
        return gson.fromJson(ratiosConversion, MonedaRecord.class);
    }
}
