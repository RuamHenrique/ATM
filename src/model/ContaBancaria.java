package model;

import java.util.List;

public interface ContaBancaria {
    void depositar(double valor);
    boolean sacar(double valor);
    double consultarSaldo();
    List<Transacao> getHistoricoTransacoes();
}
