package main.java.br.com.zenon;

import main.java.br.com.zenon.fraud.Transaction;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    final static Path CSV_PATH = Paths.get("zenon-fraud-detector/src/main/resources/data/PS_20174392719_1491204439457_log.csv");

    public static void main(String[] args) {

        try (BufferedReader br = Files.newBufferedReader(CSV_PATH)){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                var transacao = new Transaction(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2]),
                        parts[3],
                        Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]),
                        parts[6],
                        Double.parseDouble(parts[7]),
                        Double.parseDouble(parts[8]),
                        Boolean.parseBoolean(parts[9]),
                        Boolean.parseBoolean(parts[10])
                );
                System.out.println(transacao);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
