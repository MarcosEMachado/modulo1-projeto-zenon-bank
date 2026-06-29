package main.java.br.com.zenon;

import main.java.br.com.zenon.service.TransactionIngestor;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    final static Path CSV_PATH = Paths.get("zenon-fraud-detector/src/main/resources/data/PS_20174392719_1491204439457_log.csv");
    final static Path CSV_PATH_TEST = Paths.get("zenon-fraud-detector/src/main/resources/data/paysim_with_bad_data.csv");
    public static void main(String[] args) {

        var ingestor = new TransactionIngestor();
        var lista = ingestor.csvToList(CSV_PATH_TEST);
        lista.stream().toList().forEach(System.out::println);
    }
}
