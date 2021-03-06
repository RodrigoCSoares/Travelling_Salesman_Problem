# Travelling_Salesman_Problem
Esse é um projeto desenvolvido para a matéria "Algoritmos e Estruturas de Dados B"
durante o terceiro semestre (jan/2018 - jul/2018) do curso de Engenharia de Software na PUC-Campinas.

## DESCRIÇÃO:
Uma agência de viagens deseja ajudar seus clientes a conhecer os Estados Unidos e pede sua ajuda. 
Os clientes irão de avião até uma das cidades do mapa. A partir daí, alugarão um carro para conhecer uma lista grande de cidades. No final, devem voltar à cidade de partida, devolver o carro alugado e retornar ao Brasil.
O problema dos clientes é que a lista de cidades que querem visitar é muito grande e existem muitas estradas conectando as cidades. Os clientes querem um percurso que minimize a distância que terão que dirigir, mas não sabem como encontrá-lo. Felizmente, eles já têm a lista de cidades desejadas, as estradas que as conectam e as respectivas distâncias.

## OBSERVAÇÃO:
A resolução deste problema pode ser aplicada em vários outros problemas de logística, como por exemplo, na entrega de produtos a vários clientes, saindo e retornando à empresa de distribuição, com a menor distância, tempo ou custo.

## MAPA:
![alt text](https://image.ibb.co/bwV69S/mapaEUA.jpg)

### PROJETO BÁSICO:
Implemente um programa que tenha como entrada a cidade onde o cliente irá desembarcar e como saída o percurso visitando todas as cidades e retornando à cidade inicial. O programa deverá mostrar claramente todos os trechos percorridos, a distância de cada trecho e a distância total a ser percorrida.
Deve haver um processo cliente, onde será feita a interação com o usuário e um processo servidor, que receberá as requisições (ex.: cidade inicial), processará e retornará a resposta ao cliente.

### OPCIONAIS:
Os clientes podem escolher um subconjunto das cidades presentes no mapa para serem visitadas..

Os clientes podem inserir novas cidades por onde também querem passar, e também novas estradas.

Os clientes podem especificar quantos dias pretendem ficar em cada destino. Usando a data de chegada nos Estados Unidos (inserida pelo usuário), o sistema deverá imprimir as datas de chegada e saída de cada cidade até o embarque de volta ao Brasil.

O sistema deverá apresentar ao usuário os três percursos de menor distância, ordenando-os a partir do menor.

O usuário poderá escolher duas cidades, e o sistema apresentará o menor caminho entre elas.

O sistema buscará a solução bidireccionalmente e encontrará a solução em tempo substancialmente menor que a solução convencional (em “tempo de reação” do usuário).
