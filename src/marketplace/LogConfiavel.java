package marketplace;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogConfiavel {

    private final String caminhoArquivo;

    public LogConfiavel(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo, false))) {
            writer.println("===== LOG DE EXECUCAO DO ARTPOP - " + LocalDateTime.now() + " =====");
        } catch (IOException e) {
            System.err.println("Nao foi possivel iniciar o arquivo de log: " + e.getMessage());
        }
    }

    public synchronized void registrar(String mensagem) {
        String linha = "[" + LocalDateTime.now() + "] [" + Thread.currentThread().getName() + "] " + mensagem;
        System.out.println(linha);
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo, true))) {
            writer.println(linha);
        } catch (IOException e) {
            System.err.println("Falha ao escrever no log: " + e.getMessage());
        }
    }
}
