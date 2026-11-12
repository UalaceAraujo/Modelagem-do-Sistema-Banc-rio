package model;

import enums.TipoTransacao;
import java.time.LocalDateTime;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String agencia, String numero, String titular, String chavePix) {
        super(agencia, numero, titular, chavePix);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor inválido.");
            return false;
        }
        if (this.saldo >= valor) {
            this.saldo -= valor;
            this.historico.add(new Transacao(TipoTransacao.SAQUE, valor, "Saque Poupança", LocalDateTime.now()));
            System.out.printf("Saque de R$ %.2f realizado! Saldo atual: R$ %.2f%n", valor, this.saldo);
            return true;
        }
        System.out.println("Erro: Saldo insuficiente na Poupança.");
        return false;
    }

    public void aplicarInvestimento(double valor, double taxaRendimentoPercentual) {
        if (this.saldo >= valor && valor > 0) {
            double rendimento = valor * (taxaRendimentoPercentual / 100.0);
            this.saldo += rendimento;
            this.historico.add(new Transacao(
                TipoTransacao.INVESTIMENTO, rendimento, 
                String.format("Rendimento de %.1f%% sobre R$ %.2f", taxaRendimentoPercentual, valor), 
                LocalDateTime.now()
            ));
            System.out.printf("Investimento aplicado! Rendimento imediato de R$ %.2f creditado.%n", rendimento);
        } else {
            System.out.println("Erro: Saldo insuficiente para aplicar esse valor.");
        }
    }
}