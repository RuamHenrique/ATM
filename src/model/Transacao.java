package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private LocalDateTime dataHora;
    private String tipo;
    private double valor;

    public Transacao(String tipo, double valor) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %s: R$ %.2f", dataHora.format(formatter), tipo, valor);
    }
}
