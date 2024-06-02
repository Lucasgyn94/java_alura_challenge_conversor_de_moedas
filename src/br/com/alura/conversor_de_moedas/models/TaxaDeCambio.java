package br.com.alura.conversor_de_moedas.models;

import java.util.HashMap;
import java.util.Map;

public class TaxaDeCambio {
    private Map<String, Double> taxasDeConversao;

    public TaxaDeCambio() {
        this.taxasDeConversao = new HashMap<>();
    }

    public void adicionarCotacao(String moeda, double cotacao) {
        this.taxasDeConversao.put(moeda, cotacao);
    }

    public Double getCotacao(String moeda) {
        return this.taxasDeConversao.getOrDefault(moeda, 0.0);
    }

    public boolean suportaMoeda(String moeda) {
        return this.taxasDeConversao.containsKey(moeda);
    }
}
