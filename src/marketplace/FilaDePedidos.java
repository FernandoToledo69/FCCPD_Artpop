package marketplace;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FilaDePedidos {

    private final BlockingQueue<Pedido> fila = new LinkedBlockingQueue<>(1000);
    
    public void adicionarPedido(Pedido pedido) throws InterruptedException {
        fila.put(pedido);
    }
    public Pedido retirarPedido() throws InterruptedException {
        return fila.take();
    }
    public int tamanhoAtual() {
        return fila.size();
    }
}
