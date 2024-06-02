package br.com.alura.conversor_de_moedas.models;

import java.time.LocalDateTime;

public class HistoricoConversao {
    private String moedaOrigem;
    private String moedaDestino;
    private double valorOrigem;
    private double valorConvertido;
    private LocalDateTime dataHora;

    public HistoricoConversao(String moedaOrigem, String moedaDestino, double valorOrigem, double valorConvertido) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valorOrigem = valorOrigem;
        this.valorConvertido = valorConvertido;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Data/Hora: %s | %s %.2f = %s %.2f", dataHora, moedaOrigem, valorOrigem, moedaDestino, valorConvertido);
    }
}
