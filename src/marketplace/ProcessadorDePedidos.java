package marketplace;


public class ProcessadorDePedidos implements Runnable {

    private final FilaDePedidos fila;
    private final Estoque estoque;
    private final LogConfiavel log;
    private volatile boolean ativo = true;

    public ProcessadorDePedidos(FilaDePedidos fila, Estoque estoque, LogConfiavel log) {
        this.fila = fila;
        this.estoque = estoque;
        this.log = log;
    }

    public void parar() {
        ativo = false;
    }

    @Override
    public void run() {
        while (ativo) {
            try {
                Pedido pedido = fila.retirarPedido();

                if (!pedido.integro()) {
                    log.registrar("PEDIDO CORROMPIDO e descartado: " + pedido);
                    continue;
                }

                boolean sucesso = estoque.reservarProduto(pedido.getProduto(), pedido.getQuantidade());
                if (sucesso) {
                    log.registrar("Pedido CONFIRMADO: " + pedido +
                            " | estoque restante de '" + pedido.getProduto() + "': " +
                            estoque.consultarQuantidade(pedido.getProduto()));
                } else {
                    log.registrar("Pedido RECUSADO (sem estoque suficiente): " + pedido);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                ativo = false;
            } catch (Exception e) {
                log.registrar("ERRO ao processar pedido: " + e.getMessage());
            }
        }
        log.registrar("Processador de pedidos finalizado.");
    }
}
