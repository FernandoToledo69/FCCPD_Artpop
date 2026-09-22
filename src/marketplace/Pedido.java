package marketplace;

import java.util.Objects;
import java.util.UUID;

public class Pedido {

    private final String id;
    private final String cliente;
    private final String produto;
    private final int quantidade;
    private final int checksum;

    public Pedido(String cliente, String produto, int quantidade) {
        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.checksum = calcularChecksum(cliente, produto, quantidade);
    }

    private static int calcularChecksum(String cliente, String produto, int quantidade) {
        return Objects.hash(cliente, produto, quantidade);
    }

    public boolean integro() {
        return checksum == calcularChecksum(cliente, produto, quantidade);
    }

    public String getId() { return id; }
    public String getCliente() { return cliente; }
    public String getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }

    @Override
    public String toString() {
        return "Pedido{id=" + id.substring(0, 8) + "..., cliente=" + cliente +
                ", produto=" + produto + ", quantidade=" + quantidade + "}";
    }
}
