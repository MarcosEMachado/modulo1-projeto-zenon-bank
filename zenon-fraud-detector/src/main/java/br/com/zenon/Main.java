package main.java.br.com.zenon;

import main.java.br.com.zenon.repository.TransactionListRepository;
import main.java.br.com.zenon.service.TransactionIngestor;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    final static Path CSV_PATH = Paths.get("zenon-fraud-detector/src/main/resources/data/PS_20174392719_1491204439457_log.csv");

    public static void main(String[] args) {
        var nome = "C12345";
        var repository = new TransactionListRepository(new TransactionIngestor(), CSV_PATH);
        var transaction = repository.buscarNomeCliente(nome).orElse(null);
        if (transaction != null) {
            System.out.println(transaction);
        } else {
            System.out.println("Transação não encontrada para o cliente: " + nome);
        }

        nome = "C1231006815";
        transaction = repository.buscarNomeCliente(nome).orElse(null);
        if (transaction != null) {
            System.out.println(transaction);
        } else {
            System.out.println("Transação não encontrada para o cliente: " + nome);
        }
    }
}
