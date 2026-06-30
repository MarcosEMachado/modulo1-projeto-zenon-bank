package br.com.zenon.service;

import br.com.zenon.fraud.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    public void analizarFraudesTransacoes(List<Transaction> transacoes) {
        /// quantidade de transações fraudulentas
        var isFraudList = transacoes.stream()
                .filter(Transaction::isFraud)
                .toList();
        System.out.println("Total de Fraudes:: " + isFraudList.size());

        /// Imprima as 3 fraudes de maior valor (amount).
        var listaOrde = isFraudList.stream()
                .sorted((t1, t2) -> Double.compare(t2.amount(), t1.amount()))
                .limit(3)
                .toList();
        System.out.println("Top 3 Fraudes de Maior Valor:");
        listaOrde.forEach(item -> System.out.println(String.format("%.2f", item.amount())));

        /// 5 maiores clientes suspeitos (pelo valor total das fraudes)
        var top5ClientesSuspeitos = isFraudList.stream()
                .collect(Collectors.groupingBy(
                        Transaction::nameOrig,
                        Collectors.summingDouble(Transaction::amount)
                ))
                .entrySet()
                .stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(5)
                .map(entry -> entry.getKey())
                .toList();

        System.out.println("Clientes Suspeitos:");
        top5ClientesSuspeitos.forEach(System.out::println);

        /// Prejuízo Total
        double prejuizoTotal = isFraudList.stream()
                .mapToDouble(Transaction::amount)
                .sum();
        System.out.println("Prejuízo Total: " + String.format("%.2f", prejuizoTotal));

        /// Fraudes por Tipo
        var fraudesPorTipo = isFraudList.stream()
                .collect(Collectors.groupingBy(
                        Transaction::type,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()));

        System.out.println("Fraudes por Tipo:");
        fraudesPorTipo.forEach(entry -> System.out.println(" - " + entry.getKey() + ": " + entry.getValue()));
    }
}
