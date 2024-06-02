package br.com.alura.conversor_de_moedas.service;

import br.com.alura.conversor_de_moedas.models.TaxaDeCambio;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApiService {

    private static final String URL_API = "https://v6.exchangerate-api.com/v6/d3198b456cf4c97bf7ce7014/latest/USD";
    private Map<String, Double> moedasSuportadas;

    public ApiService() {
        this.moedasSuportadas = buscarMoedasSuportadas();
    }

    public TaxaDeCambio buscarTaxaDeCambio(String moeda) throws Exception {
        URI uriTaxaDeCambio = URI.create("https://v6.exchangerate-api.com/v6/d3198b456cf4c97bf7ce7014/latest/" + moeda);

        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(uriTaxaDeCambio)
                .build();

        try {
            HttpResponse<String> resposta = HttpClient
                    .newHttpClient()
                    .send(requisicao, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = JsonParser.parseString(resposta.body()).getAsJsonObject();
            JsonObject taxasDeConversao = jsonResponse.getAsJsonObject("conversion_rates");

            TaxaDeCambio taxaDeCambio = new TaxaDeCambio();
            for (Map.Entry<String, JsonElement> entry : taxasDeConversao.entrySet()) {
                taxaDeCambio.adicionarCotacao(entry.getKey(), entry.getValue().getAsDouble());
            }
            System.out.println();
            return taxaDeCambio;
        } catch (Exception e) {
            throw new Exception("Não consegui converter a moeda selecionada: " + e.getMessage());
        }
    }


    private Map<String, Double> buscarMoedasSuportadas() {
        Map<String, Double> moedas = new HashMap<>();
        try {
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API))
                    .build();
            HttpResponse<String> resposta = HttpClient
                    .newHttpClient()
                    .send(requisicao, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResposta = JsonParser.parseString(resposta.body()).getAsJsonObject();
            JsonObject taxasDeConversao = jsonResposta.getAsJsonObject("conversion_rates");

            for (Map.Entry<String, com.google.gson.JsonElement> entrada : taxasDeConversao.entrySet()) {
                moedas.put(entrada.getKey(), entrada.getValue().getAsDouble());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return moedas;
    }

    public boolean suportaMoeda(String moeda) {
        return moedasSuportadas.containsKey(moeda);
    }

    public Set<String> getMoedasSuportadas() {
        return moedasSuportadas.keySet();
    }
}
