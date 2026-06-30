package br.com.zenon.repository;

import br.com.zenon.fraud.Transaction;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> buscarNomeCliente(String nome);
}
