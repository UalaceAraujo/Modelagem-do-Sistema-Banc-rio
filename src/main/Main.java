package main;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import repository.ContaRepository;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContaRepository repository = new ContaRepository();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== SISTEMA BANCÁRIO JAVA (DIO) ===");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferência via PIX");
            System.out.println("5 - Aplicar Investimento (Apenas Poupança)");
            System.out.println("6 - Consultar Extrato");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Tipo (1 - Corrente | 2 - Poupança): ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome do Titular: ");
                        String titular = scanner.nextLine();
                        System.out.print("Número da Conta (ex: 1001): ");
                        String numero = scanner.nextLine();
                        System.out.print("Cadastre uma Chave PIX: ");
                        String pix = scanner.nextLine();

                        if (tipo == 1) {
                            repository.salvar(new ContaCorrente("0001", numero, titular, pix, 500.0));
                            System.out.println("Conta Corrente criada com R$ 500,00 de Cheque Especial!");
                        } else {
                            repository.salvar(new ContaPoupanca("0001", numero, titular, pix));
                            System.out.println("Conta Poupança criada com sucesso!");
                        }
                    }
                    case 2 -> {
                        System.out.print("Número da Conta: ");
                        String num = scanner.nextLine();
                        Conta conta = repository.buscarPorNumero(num);
                        System.out.print("Valor do depósito: ");
                        double valor = scanner.nextDouble();
                        conta.depositar(valor);
                    }
                    case 3 -> {
                        System.out.print("Número da Conta: ");
                        String num = scanner.nextLine();
                        Conta conta = repository.buscarPorNumero(num);
                        System.out.print("Valor do saque: ");
                        double valor = scanner.nextDouble();
                        conta.sacar(valor);
                    }
                    case 4 -> {
                        System.out.print("Número da SUA Conta: ");
                        String numOrigem = scanner.nextLine();
                        System.out.print("Chave PIX de destino: ");
                        String pixDestino = scanner.nextLine();

                        Conta origem = repository.buscarPorNumero(numOrigem);
                        Conta destino = repository.buscarPorChavePix(pixDestino);

                        System.out.print("Valor do PIX: ");
                        double valor = scanner.nextDouble();
                        origem.transferirPix(valor, destino);
                    }
                    case 5 -> {
                        System.out.print("Número da Conta Poupança: ");
                        String num = scanner.nextLine();
                        Conta conta = repository.buscarPorNumero(num);
                        if (conta instanceof ContaPoupanca poupanca) {
                            System.out.print("Valor base para aplicar: ");
                            double valor = scanner.nextDouble();
                            poupanca.aplicarInvestimento(valor, 5.0);
                        } else {
                            System.out.println("Erro: Esta operação está disponível apenas para Conta Poupança.");
                        }
                    }
                    case 6 -> {
                        System.out.print("Número da Conta: ");
                        String num = scanner.nextLine();
                        Conta conta = repository.buscarPorNumero(num);
                        conta.exibirExtrato();
                    }
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n[AVISO DE SISTEMA]: " + e.getMessage());
                scanner.nextLine(); // Limpa o buffer caso tenha entrado letra em campo de número
            }
        }
        scanner.close();
    }
}