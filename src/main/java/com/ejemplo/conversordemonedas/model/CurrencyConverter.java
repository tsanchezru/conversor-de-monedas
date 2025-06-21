package com.ejemplo.conversordemonedas.model;


import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class CurrencyConverter {

    // ✅ Cambia esto por tu clave API real
    private static final String API_KEY = "0cbc2231b364bd8c2652ec5d";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6";

    // Reutilizamos una sola instancia de HttpClient
    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Convierte una cantidad de una moneda a otra usando exchangerate-api.com
     *
     * @param from   código de moneda origen (por ejemplo, "USD")
     * @param to     código de moneda destino (por ejemplo, "COP")
     * @param amount cantidad a convertir
     * @return resultado de la conversión
     * @throws IOException si hay error de red o formato
     */
    public static double convert(String from, String to, double amount) throws IOException, InterruptedException {
        // Endpoint de conversión con cantidad incluida
        String urlStr = String.format("%s/%s/pair/%s/%s/%.2f", BASE_URL, API_KEY, from, to, amount);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlStr))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());

        // Verificar si la API devuelve un estado válido
        if (!json.getString("result").equals("success")) {
            throw new IOException("Error en la API: " + json.optString("error-type", "Desconocido"));
        }

        // Extraer el resultado convertido
        return json.getDouble("conversion_result");
    }
}
