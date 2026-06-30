package main.java.br.com.zenon.repository;

import main.java.br.com.zenon.fraud.Transaction;
import main.java.br.com.zenon.service.TransactionIngestor;

import java.nio.file.Path;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final TransactionIngestor ingestor;
    private final Path csvPath;

    public TransactionListRepository(TransactionIngestor ingestor, Path csvPath) {
        this.ingestor = ingestor;
        this.csvPath = csvPath;
    }

    @Override
    public Optional<Transaction> buscarNomeCliente(String nome) {
        var listDados = ingestor.csvToList(csvPath);
        return listDados.stream()
                .filter(t -> t.nameOrig().equals(nome))
                .findFirst();
    }
}
