# Sistema Bancário Orientado a Objetos em Java

Projeto desenvolvido como parte de um desafio prático de Programação Orientada a Objetos (POO) com o objetivo de consolidar conceitos fundamentais de desenvolvimento backend em Java, aplicando boas práticas de arquitetura, tratamento de exceções e persistência em memória.

## Tecnologias Utilizadas

* **Java** (JDK 17+)
* **Paradigma Orientado a Objetos (POO)**
* **Arquitetura em Camadas** (Model, Repository, Service/Main, Exception, Enums)

## Conceitos Aplicados

* **Abstração:** Utilização de classes abstratas (`Conta`) para definir o comportamento genérico das operações financeiras.
* **Herança:** Especialização de contas (`ContaCorrente` com cheque especial e `ContaPoupanca` com módulo de investimentos).
* **Polimorfismo:** Sobrescrita do método de saque (`sacar`) adaptando regras específicas para cada tipo de conta.
* **Encapsulamento:** Proteção do estado interno das classes utilizando modificadores de acesso e validações nas regras de negócio.
* **Tratamento de Exceções Customizadas:** Criação de exceções personalizadas (`SaldoInsuficienteException`, `ContaNaoEncontradaException`) para gerenciar falhas de forma robusta e elegante via blocos `try-catch`.
* **Uso de Records e Enums:** Emprego de `record` para modelagem imutável de transações e `enums` para padronização dos tipos de operações.

## Estrutura do Projeto

```text
src/
├── enums/
│   └── TipoTransacao.java
├── exception/
│   ├── ContaNaoEncontradaException.java
│   └── SaldoInsuficienteException.java
├── main/
│   └── Main.java
├── model/
│   ├── Conta.java
│   ├── ContaCorrente.java
│   ├── ContaPoupanca.java
│   └── Transacao.java
└── repository/
    └── ContaRepository.java