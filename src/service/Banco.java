package service;

import model.Cliente;
import model.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Cliente> clientes; // Lista de clientes cadastrados no banco.

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    /**
     * Adiciona um cliente ao banco.
     *
     * @param cliente Cliente a ser adicionado.
     */
    public void adicionarCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
        } else {
            System.out.println("Erro: Cliente inválido. Não foi possível adicionar.");
        }
    }

    /**
     * Autentica uma conta com base no número e PIN.
     *
     * @param numero Número da conta.
     * @param pin    PIN da conta.
     * @return A conta autenticada ou null se falhar.
     */
    public Conta autenticar(String numero, String pin) {
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                if (conta.getNumero().equals(numero) && conta.getPin().equals(pin)) {
                    return conta; // Conta encontrada e autenticada.
                }
            }
        }
        return null; // Retorna null se a conta não for encontrada ou o PIN for inválido.
    }

    /**
     * Busca uma conta pelo número.
     *
     * @param numeroConta Número da conta a ser buscada.
     * @return A conta correspondente ou null se não for encontrada.
     */
    public Conta buscarConta(String numeroConta) {
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                if (conta.getNumero().equals(numeroConta)) {
                    return conta; // Conta encontrada.
                }
            }
        }
        return null; // Retorna null se a conta não for encontrada.
    }

    /**
     * Realiza uma transferência entre contas.
     *
     * @param contaOrigem        Conta de origem.
     * @param numeroContaDestino Número da conta de destino.
     * @param valor              Valor a ser transferido.
     * @return True se a transferência for bem-sucedida, false caso contrário.
     */
    public boolean transferir(Conta contaOrigem, String numeroContaDestino, double valor) {
        if (contaOrigem == null || numeroContaDestino == null) {
            System.out.println("Erro: Conta de origem ou destino inválida.");
            return false;
        }

        Conta contaDestino = buscarConta(numeroContaDestino);

        if (contaDestino == null) {
            System.out.println("Erro: Conta de destino não encontrada.");
            return false;
        }

        if (contaOrigem.sacar(valor)) {
            contaDestino.depositar(valor);
            System.out.printf("Transferência de R$ %.2f realizada com sucesso de %s para %s.%n", valor, contaOrigem.getNumero(), contaDestino.getNumero());
            return true;
        } else {
            System.out.println("Erro: Saldo insuficiente na conta de origem.");
            return false;
        }
    }

    /**
     * Obtém a lista de clientes cadastrados no banco.
     *
     * @return Lista de clientes.
     */
    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes); // Retorna uma cópia da lista de clientes.
    }
}
