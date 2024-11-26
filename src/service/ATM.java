package service;

import enums.Operacao;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.Scanner;

public class ATM {
    private Banco banco;
    private Scanner scanner;

    public ATM(Banco banco) {
        this.banco = banco;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n--- Bem-vindo ao ATM ---");
            System.out.println("1. Criar Cliente e Conta");
            System.out.println("2. Acessar Conta");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1 -> criarClienteEConta();
                case 2 -> acessarConta();
                case 3 -> System.out.println("Obrigado por usar o ATM!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }

    private void criarClienteEConta() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf);

        System.out.print("Número da Conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        System.out.println("Escolha o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        int tipoConta = scanner.nextInt();

        Conta conta = (tipoConta == 1)
                ? new ContaCorrente(numeroConta, pin)
                : new ContaPoupanca(numeroConta, pin);

        cliente.adicionarConta(conta);
        banco.adicionarCliente(cliente);

        System.out.println("Cliente e conta cadastrados com sucesso!");
    }

    private void acessarConta() {
        System.out.print("Número da Conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        Conta conta = banco.autenticar(numeroConta, pin);
        if (conta != null) {
            exibirMenuConta(conta);
        } else {
            System.out.println("Autenticação falhou! Verifique número da conta ou PIN.");
        }
    }

    private void exibirMenuConta(Conta conta) {
        Operacao operacao;
        do {
            System.out.println("\n--- Menu da Conta ---");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Extrato");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            operacao = Operacao.values()[opcao - 1];

            switch (operacao) {
                case CONSULTAR_SALDO -> System.out.println("Saldo atual: R$ " + conta.consultarSaldo());
                case DEPOSITAR -> {
                    System.out.print("Digite o valor a depositar: R$ ");
                    double valor = scanner.nextDouble();
                    conta.depositar(valor);
                    System.out.println("Depósito realizado com sucesso!");
                }
                case SACAR -> {
                    System.out.print("Digite o valor a sacar: R$ ");
                    double valor = scanner.nextDouble();
                    if (conta.sacar(valor)) {
                        System.out.println("Saque realizado com sucesso!");
                    } else {
                        System.out.println("Saldo insuficiente para o saque!");
                    }
                }
                case TRANSFERIR -> realizarTransferencia(conta);
                case EXTRATO -> {
                    System.out.println("--- Extrato da Conta ---");
                    conta.getHistoricoTransacoes().forEach(System.out::println);
                }
                case SAIR -> System.out.println("Saindo do menu da conta...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (operacao != Operacao.SAIR);
    }

    private void realizarTransferencia(Conta contaOrigem) {
        System.out.print("Número da conta de destino: ");
        String numeroContaDestino = scanner.nextLine();
        System.out.print("Valor a transferir: R$ ");
        double valor = scanner.nextDouble();

        if (banco.transferir(contaOrigem, numeroContaDestino, valor)) {
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Transferência falhou! Verifique saldo ou número da conta de destino.");
        }
    }
}
