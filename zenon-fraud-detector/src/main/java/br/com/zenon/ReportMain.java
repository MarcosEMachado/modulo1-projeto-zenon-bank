package main.java.br.com.zenon;

import main.java.br.com.zenon.service.TransactionReport;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportMain {

    final static Path CSV_PATH = Paths.get("zenon-fraud-detector/src/main/resources/data/PS_20174392719_1491204439457_log.csv");
    final static TransactionReport transactionReport = new TransactionReport(CSV_PATH);

    public static void main(String[] args) {
        var totalLinhas = transactionReport.contar();
        var totalFraudes = transactionReport.contarFraudes();
        var totalTransferencias = transactionReport.somarValoresTransferencias();

        System.out.println("Total de linhas: " + totalLinhas);
        System.out.println("Total de fraudes: " + totalFraudes);
        System.out.println("Valor total transacionado: " + String.format("%.2f", totalTransferencias));
    }
}
