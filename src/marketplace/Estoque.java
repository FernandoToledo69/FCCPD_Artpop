package marketplace;

import java.util.HashMap;
import java.util.Map;

public class Estoque {

    private final Map<String, Integer> quantidades = new HashMap<>();

    /** Cadastra um produto novo com uma quantidade inicial. */
    public synchronized void cadastrarProduto(String nomeProduto, int quantidadeInicial) {
        quantidades.put(nomeProduto, quantidadeInicial);
    }

    public synchronized boolean reservarProduto(String nomeProduto, int quantidadeDesejada) {
        Integer disponivel = quantidades.get(nomeProduto);
        if (disponivel == null || disponivel < quantidadeDesejada) {
            return false; 
        }
        quantidades.put(nomeProduto, disponivel - quantidadeDesejada);
        return true;
    }

    public synchronized int consultarQuantidade(String nomeProduto) {
        return quantidades.getOrDefault(nomeProduto, 0);
    }
}
