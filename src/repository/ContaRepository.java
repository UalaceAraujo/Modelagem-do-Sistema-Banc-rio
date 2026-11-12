package repository;

import exception.ContaNaoEncontradaException;
import model.Conta;
import java.util.ArrayList;
import java.util.List;

public class ContaRepository {
    private List<Conta> contas = new ArrayList<>();

    public void salvar(Conta conta) {
        contas.add(conta);
    }

    public Conta buscarPorNumero(String numero) {
        return contas.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst()
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de número " + numero + " não foi encontrada."));
    }

    public Conta buscarPorChavePix(String chavePix) {
        return contas.stream()
                .filter(c -> c.getChavePix().equalsIgnoreCase(chavePix))
                .findFirst()
                .orElseThrow(() -> new ContaNaoEncontradaException("Chave PIX '" + chavePix + "' não foi encontrada."));
    }

    public List<Conta> listarTodas() {
        return contas;
    }
}