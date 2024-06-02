# Conversor de Moedas

Projeto desenvolvido em Java que permite aos usuários converter entre diferentes moedas utilizando a API de taxas de câmbio fornecida pelo [ExchangeRate-API] presente no link: (https://www.exchangerate-api.com/).

## Funcionalidades

- Conversão entre moedas com base nas taxas de câmbio atualizadas da API.
- Suporte para uma variedade de moedas disponíveis na API.
- Rastreamento e exibição do histórico das últimas conversões realizadas.
- Registros de logs das conversões, incluindo informações sobre as moedas convertidas e o momento da conversão.

## Tecnologias Utilizadas

- Java
- Gson (para manipulação de JSON)
- HttpClient (para realizar requisições HTTP)

## Saída
-> Para o início temos uma lista que busca diretamente da API as moedas suportadas e apresenta ao usuário:
![image](https://github.com/Lucasgyn94/java_alura_challenge_conversor_de_moedas/assets/91031320/fe027fbe-0a5e-428b-85f8-0330891adf7c)


-> Após e solicitado ao usuário as informações de moeda origem, moeda destino e valor e caso esteja tudo correto o resultado da conversão e exibido ao usuário:
![image](https://github.com/Lucasgyn94/java_alura_challenge_conversor_de_moedas/assets/91031320/5b200990-3ddf-4350-a145-ba3d8407f2cc)

-> Caso o usuário digite uma moeda que não está na lista, o mesmo e informado que a moeda não é suportada, para digitar novamente:
![image](https://github.com/Lucasgyn94/java_alura_challenge_conversor_de_moedas/assets/91031320/50fb6cad-e793-4d36-b0bf-c0dbc40a32d3)

-> E por fim, após o cadastro de conversões que deram sucesso, se o usuário digitar para sair, é exibido o histórico do mesmo ao final:
![image](https://github.com/Lucasgyn94/java_alura_challenge_conversor_de_moedas/assets/91031320/f0eb4a85-2bce-4301-9c6b-bd8465ef9089)

