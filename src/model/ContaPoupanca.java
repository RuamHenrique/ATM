package model;

public class ContaPoupanca extends Conta {
    private static final double TAXA_RENDIMENTO = 0.01;

    public ContaPoupanca(String numero, String pin) {
        super(numero, pin);
    }

    @Override
    public void aplicarJuros() {
        if (saldo > 0) {
            double rendimento = saldo * TAXA_RENDIMENTO;
            saldo += rendimento;
            getHistoricoTransacoes().add(new Transacao("Rendimento", rendimento));
        }
    }
}
