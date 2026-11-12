package model;

import enums.TipoTransacao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Transacao(
    TipoTransacao tipo,
    double valor,
    String descricao,
    LocalDateTime dataHora
) {
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %-18s | R$ %8.2f | %s", 
            dataHora.format(fmt), tipo, valor, descricao);
    }
}