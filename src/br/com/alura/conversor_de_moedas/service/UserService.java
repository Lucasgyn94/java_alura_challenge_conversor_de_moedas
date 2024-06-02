package br.com.alura.conversor_de_moedas.service;

import br.com.alura.conversor_de_moedas.models.HistoricoConversao;
import br.com.alura.conversor_de_moedas.models.TaxaDeCambio;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserService {

    private ApiService apiService;
    private List<HistoricoConversao> historico;

    public UserService(ApiService apiService) {
        this.apiService = apiService;
        this.historico = new ArrayList<>();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            // Obter moedas suportadas
            Set<String> moedasSuportadas = apiService.getMoedasSuportadas();

            // Exibir moedas suportadas em forma de matriz
            System.out.println("Moedas suportadas:");
            int contador = 0;
            for (String moeda : moedasSuportadas) {
                System.out.printf("%-6s", moeda);
                contador++;
                // Quebra de linha a cada 5 moedas
                if (contador % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println();

            System.out.println("Digite o código da moeda de origem (ou '0' para sair): ");
            String moedaOrigem = scanner.nextLine().toUpperCase();

            if (moedaOrigem.equals("0")) {
                break;
            }

            System.out.println("Digite o código da moeda de destino: ");
            String moedaDestino = scanner.nextLine().toUpperCase();

            if (!apiService.suportaMoeda(moedaOrigem) || !apiService.suportaMoeda(moedaDestino)) {
                System.out.println("Moeda não suportada. Tente novamente.");
                continue;
            }

            System.out.println("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            try {
                TaxaDeCambio taxaDeCambio = apiService.buscarTaxaDeCambio(moedaOrigem);

                double cotacaoOrigem = taxaDeCambio.getCotacao(moedaOrigem);
                double cotacaoDestino = taxaDeCambio.getCotacao(moedaDestino);
                double valorConvertido = (valor / cotacaoOrigem) * cotacaoDestino;

                System.out.printf("Resultado: %.2f %s = %.2f %s%n", valor, moedaOrigem, valorConvertido, moedaDestino);
                System.out.println();
                HistoricoConversao conversao = new HistoricoConversao(moedaOrigem, moedaDestino, valor, valorConvertido);
                historico.add(conversao);
            } catch (Exception e) {
                System.out.println("Erro ao buscar a taxa de câmbio: " + e.getMessage());
            }


        }
        System.out.println("Histórico de conversões:");
        for (HistoricoConversao conversao : historico) {
            System.out.println(conversao);
        }
    }

}

