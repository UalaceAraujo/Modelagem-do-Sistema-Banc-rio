package model;

import enums.TipoTransacao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private String agencia;
    private String numero;
    private String titular;
    private String chavePix;
    protected double saldo;
    protected List<Transacao> historico;

    public Conta(String agencia, String numero, String titular, String chavePix) {
        this.agencia = agencia;
        this.numero = numero;
        this.titular = titular;
        this.chavePix = chavePix;
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }
        this.saldo += valor;
        this.historico.add(new Transacao(TipoTransacao.DEPOSITO, valor, "Depósito em conta", LocalDateTime.now()));
        System.out.printf("Depósito de R$ %.2f realizado! Saldo atual: R$ %.2f%n", valor, this.saldo);
    }

    // Cada tipo de conta vai implementar sua própria regra de saque (Polimorfismo)
    public abstract boolean sacar(double valor);

    public boolean transferirPix(double valor, Conta contaDestino) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }
        if (this.sacar(valor)) {
            contaDestino.saldo += valor;
            this.historico.add(new Transacao(
                TipoTransacao.TRANSFERENCIA_PIX, valor, "PIX enviado para " + contaDestino.getTitular(), LocalDateTime.now()
            ));
            contaDestino.historico.add(new Transacao(
                TipoTransacao.TRANSFERENCIA_PIX, valor, "PIX recebido de " + this.titular, LocalDateTime.now()
            ));
            System.out.printf("PIX de R$ %.2f enviado com sucesso para %s!%n", valor, contaDestino.getTitular());
            return true;
        }
        return false;
    }

    public void exibirExtrato() {
        System.out.println("\n================ EXTRATO BANCÁRIO ================");
        System.out.println("Titular: " + titular + " | Chave PIX: " + chavePix);
        System.out.println("Agência: " + agencia + " | Conta: " + numero);
        System.out.printf("Saldo Disponível: R$ %.2f%n", saldo);
        System.out.println("--------------------------------------------------");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (Transacao t : historico) {
                System.out.println(t);
            }
        }
        System.out.println("==================================================\n");
    }

    public String getNumero() { return numero; }
    public String getTitular() { return titular; }
    public String getChavePix() { return chavePix; }
    public double getSaldo() { return saldo; }
}