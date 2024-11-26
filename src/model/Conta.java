package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements ContaBancaria {
    private String numero;
    private String pin;
    protected double saldo;
    private List<Transacao> historicoTransacoes;

    public Conta(String numero, String pin) {
        this.numero = numero;
        this.pin = pin;
        this.saldo = 0.0;
        this.historicoTransacoes = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        historicoTransacoes.add(new Transacao("Depósito", valor));
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            historicoTransacoes.add(new Transacao("Saque", valor));
            return true;
        }
        historicoTransacoes.add(new Transacao("Tentativa de saque falhou: Saldo insuficiente", valor));
        return false;
    }

    @Override
    public List<Transacao> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public abstract void aplicarJuros();
}
