package main.java.br.com.zenon.repository;

import main.java.br.com.zenon.fraud.Transaction;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> buscarNomeCliente(String nome);
}
