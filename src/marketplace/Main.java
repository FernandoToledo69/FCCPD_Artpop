package marketplace;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        LogConfiavel log = new LogConfiavel("marketplace.log");
        log.registrar("Iniciando Artpop Artesanato...");

        Estoque estoque = new Estoque();
        String[] produtos = {
                "Renda de Tracunhaem", "Ceramica do Alto do Moura", "Rede de Dormir de Caruaru"
        };
        estoque.cadastrarProduto(produtos[0], 5);
        estoque.cadastrarProduto(produtos[1], 4);
        estoque.cadastrarProduto(produtos[2], 6);

        FilaDePedidos filaDePedidos = new FilaDePedidos();

        ProcessadorDePedidos processador = new ProcessadorDePedidos(filaDePedidos, estoque, log);
        Thread threadProcessador = new Thread(processador, "Processador-Pedidos");
        threadProcessador.start();

        int numeroDeClientes = 20;
        ExecutorService clientes = Executors.newFixedThreadPool(8);

        for (int i = 1; i <= numeroDeClientes; i++) {
            final int numeroCliente = i;
            final String produto = produtos[i % produtos.length];
            clientes.submit(() -> {
                Pedido pedido = new Pedido("Cliente-" + numeroCliente, produto, 1);
                try {
                    filaDePedidos.adicionarPedido(pedido);
                    log.registrar("Pedido ENVIADO para a fila: " + pedido);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        clientes.shutdown();
        clientes.awaitTermination(10, TimeUnit.SECONDS);

        while (filaDePedidos.tamanhoAtual() > 0) {
            Thread.sleep(50);
        }
        Thread.sleep(300); 
        processador.parar();
        threadProcessador.interrupt();
        threadProcessador.join();

        System.out.println("\n===== ESTOQUE ATUALIZADO =====");
        int totalVendido = 0;
        for (String produto : produtos) {
            int restante = estoque.consultarQuantidade(produto);
            System.out.println(produto + ": " + restante + " unidade(s) restante(s)");
        }
        System.out.println("\nTotal de clientes simulados: " + numeroDeClientes);
        System.out.println("Confira o arquivo marketplace.log na pasta do projeto");
        System.out.println("para ver a evidencia completa de que nenhum pedido foi perdido");
        System.out.println("e o estoque nunca ficou negativo, mesmo com varios clientes");
        System.out.println("disputando o mesmo produto ao mesmo tempo.");
    }
}
