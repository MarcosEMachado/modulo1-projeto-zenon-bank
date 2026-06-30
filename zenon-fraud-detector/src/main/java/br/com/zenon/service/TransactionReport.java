package main.java.br.com.zenon.service;

import main.java.br.com.zenon.fraud.Transaction;
import main.java.br.com.zenon.fraud.TransactionType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class TransactionReport {

    private final Path csvPath;

    public TransactionReport(Path csvPath) {
        this.csvPath = csvPath;
    }

    public Stream<Transaction> obterTransacoesStream() {
        try {
            return Files.lines(csvPath)
                    .skip(1)
                    .filter(this::isValid)
                    .map(this::parseTransaction);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler arquivo: " + csvPath, e);
        }
    }

    public long contar() {
        try (Stream<String> lines = Files.lines(csvPath)) {
            return lines.skip(1)
                    .filter(this::isValid)
                    .count();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao contar transações", e);
        }
    }

    public long contarFraudes() {
        try (Stream<Transaction> transacoes = obterTransacoesStream()) {
            return transacoes.filter(Transaction::isFraud)
                    .count();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao contar fraudes", e);
        }
    }

    public double somarValoresTransferencias() {
        try (Stream<Transaction> transacoes = obterTransacoesStream()) {
            return transacoes
                    .mapToDouble(Transaction::amount)
                    .sum();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao somar transferências", e);
        }
    }

    private Transaction parseTransaction(String line) {
        String[] parts = line.split(",");
        return new Transaction(
                Integer.parseInt(parts[0]),
                TransactionType.valueOf(parts[1]),
                Double.parseDouble(parts[2]),
                parts[3],
                Double.parseDouble(parts[4]),
                Double.parseDouble(parts[5]),
                parts[6],
                Double.parseDouble(parts[7]),
                Double.parseDouble(parts[8]),
                parts[9].equals("1"),
                parts[10].equals("1")
        );
    }

    private boolean isValid(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length != 11) return false;
            if (Arrays.stream(parts).anyMatch(String::isEmpty)) return false;

            boolean typeValid = Arrays.stream(TransactionType.values())
                    .anyMatch(type -> type.name().equals(parts[1]));

            return typeValid &&
                    Integer.parseInt(parts[0]) >= 1 &&
                    Double.parseDouble(parts[2]) >= 0 &&
                    Double.parseDouble(parts[4]) >= 0 &&
                    Double.parseDouble(parts[5]) >= 0 &&
                    Double.parseDouble(parts[7]) >= 0 &&
                    Double.parseDouble(parts[8]) >= 0;
        } catch (Exception e) {
            System.err.println("Linha inválida: " + line);
            return false;
        }
    }
}