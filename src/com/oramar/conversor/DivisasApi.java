package com.oramar.conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class DivisasApi {

    private String cargarApiKey() {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("config.env"))) {
            String linea = br.readLine();
            // Esto busca el signo "=", toma lo que sigue y quita las comillas
            String clave = linea.split("=")[1].replace("\"", "").trim();
            return clave;
        } catch (Exception e) {
            System.err.println("Error leyendo config.env: " + e.getMessage());
            return null;
        }
    }

    public double get(String cantidadDinero, String deDivisa, String aDivisa) {
        String apiKey = cargarApiKey();
        try {
            String urlString = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + deDivisa + "/" + aDivisa + "/" + cantidadDinero;
            URL url = URI.create(urlString).toURL();
            HttpURLConnection request = (HttpURLConnection) url.openConnection();

            if (request.getResponseCode() == 200) {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(request.getInputStream())).getAsJsonObject();
                if (root.has("conversion_result")) {
                    return root.get("conversion_result").getAsDouble();
                }
            }
            request.disconnect();
        } catch (Exception e) {
            System.out.println("Error en API: " + e.getMessage());
        }
        return 0.0;
    }
}