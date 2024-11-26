package model;

public class ContaCorrente extends Conta {
    private static final double TAXA_MANUTENCAO = 5.0;

    public ContaCorrente(String numero, String pin) {
        super(numero, pin);
    }

    @Override
    public void aplicarJuros() {
        if (saldo > 0) {
            saldo -= TAXA_MANUTENCAO;
            getHistoricoTransacoes().add(new Transacao("Taxa de manutenção", TAXA_MANUTENCAO));
        }
    }
}
