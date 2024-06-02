package br.com.alura.conversor_de_moedas.principal;

import br.com.alura.conversor_de_moedas.service.ApiService;
import br.com.alura.conversor_de_moedas.service.UserService;

public class Main {
    public static void main(String[] args) {
        ApiService apiService = new ApiService();
        UserService userService = new UserService(apiService);
        userService.iniciar();
    }
}
