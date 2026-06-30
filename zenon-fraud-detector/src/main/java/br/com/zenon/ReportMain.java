package br.com.zenon;

import br.com.zenon.service.TransactionReport;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportMain {

    final static Path CSV_PATH = Paths.get("zenon-fraud-detector/src/main/resources/data/PS_20174392719_1491204439457_log.csv");
    final static TransactionReport transactionReport = new TransactionReport(CSV_PATH);

    public static void main(String[] args) {

        String lang = "en";
        Locale locale = "en".equals(lang) ? Locale.US : Locale.of("pt", "BR");

        ResourceBundle bundle = ResourceBundle.getBundle("report", locale);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        NumberFormat currencyFmt = NumberFormat.getCurrencyInstance(locale);

        var totalLinhas = numberFormat.format(transactionReport.contar());
        var totalFraudes = numberFormat.format(transactionReport.contarFraudes());
        var totalTransferencias = currencyFmt.format(transactionReport.somarValoresTransferencias());

        System.out.println(bundle.getString("total.lines") + ": " + totalLinhas);
        System.out.println(bundle.getString("total.frauds") + ": " + totalFraudes);
        System.out.println(bundle.getString("total.value") + ": " + totalTransferencias);
    }
}
