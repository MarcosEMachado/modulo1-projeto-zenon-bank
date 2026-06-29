package main.java.br.com.zenon;

import main.java.br.com.zenon.fraud.Transaction;
import main.java.br.com.zenon.repository.TransactionListRepository;
import main.java.br.com.zenon.repository.TransactionMapRepository;
import main.java.br.com.zenon.repository.TransactionRepository;
import main.java.br.com.zenon.service.TransactionIngestor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class PerformanceTest {

    public static void main(String[] args) {
        Path csvPath = Paths.get("zenon-fraud-detector/src/main/resources/data/PS_20174392719_1491204439457_log.csv");
        TransactionIngestor ingestor = new TransactionIngestor();

        String clienteParaBuscar = "C1868032458";

        System.out.println("=== TESTE DE PERFORMANCE ===");

        // Teste com List
        System.out.println("1. TransactionListRepository (usando List):");
        testarPerformance(new TransactionListRepository(ingestor, csvPath), clienteParaBuscar);

        // Teste com Map
        System.out.println("2. TransactionMapRepository (usando Map):");
        testarPerformance(new TransactionMapRepository(ingestor, csvPath), clienteParaBuscar);
    }

    private static void testarPerformance(TransactionRepository repository, String cliente) {
        long inicio = System.nanoTime();
        Optional<Transaction> resultado = repository.buscarNomeCliente(cliente);
        long fim = System.nanoTime();

        long tempoNanos = fim - inicio;
        long tempoMilis = tempoNanos / 1_000_000;

        System.out.println("   Cliente buscado: " + cliente);
        System.out.println("   Encontrado: " + resultado.isPresent());
        if (resultado.isPresent()) {
            System.out.println("   Nome Origem: " + resultado.get().nameOrig());
        }
        System.out.println("   Tempo: " + tempoMilis + " ms");
    }
}
