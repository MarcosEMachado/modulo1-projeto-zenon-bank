package main.java.br.com.zenon.repository;

import main.java.br.com.zenon.fraud.Transaction;
import main.java.br.com.zenon.service.TransactionIngestor;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionsMap;

    public TransactionMapRepository(TransactionIngestor ingestor, Path csvPath) {
        this.transactionsMap = new HashMap<>();
        carregarTransacoesNoMapa(ingestor, csvPath);
    }

    private void carregarTransacoesNoMapa(TransactionIngestor ingestor, Path csvPath) {
        var transacoes = ingestor.csvToList(csvPath);
        for (Transaction transacao : transacoes) {
            transactionsMap.put(transacao.nameOrig(), transacao);
        }
    }

    @Override
    public Optional<Transaction> buscarNomeCliente(String nome) {
        return Optional.ofNullable(transactionsMap.get(nome));
    }
}
