package model;

import enums.TipoTransacao;
import java.time.LocalDateTime;

public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    public ContaCorrente(String agencia, String numero, String titular, String chavePix, double limiteChequeEspecial) {
        super(agencia, numero, titular, chavePix);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor inválido.");
            return false;
        }
        if ((this.saldo + this.limiteChequeEspecial) >= valor) {
            this.saldo -= valor;
            this.historico.add(new Transacao(TipoTransacao.SAQUE, valor, "Saque Conta Corrente", LocalDateTime.now()));
            System.out.printf("Saque de R$ %.2f realizado! Saldo atual: R$ %.2f%n", valor, this.saldo);
            return true;
        }
        System.out.println("Erro: Saldo + Cheque Especial insuficientes.");
        return false;
    }
}