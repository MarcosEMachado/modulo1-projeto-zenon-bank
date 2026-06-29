package main.java.br.com.zenon.service;

import main.java.br.com.zenon.fraud.Transaction;
import main.java.br.com.zenon.fraud.TransactionType;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> csvToList(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            br.readLine();
            var contErr = 0;
            var list = new ArrayList<Transaction>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (isValid(line)) {
                    var transacao = new Transaction(
                            Integer.parseInt(parts[0]),
                            TransactionType.valueOf(parts[1]),
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
                    list.add(transacao);
                    if (list.size() == 1000)
                        break;
                } else {
                    contErr++;
                }
            }
            System.out.println(contErr);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean isValid(String line) {
        String[] parts = line.split(",");
        boolean valid = Arrays.stream(parts).allMatch(part -> !part.isEmpty());
        boolean typeValid = Arrays.stream(TransactionType.values())
                .anyMatch(type -> type.name().equals(parts[1]));
        if (valid &&
                typeValid &&
                Integer.parseInt(parts[0]) >= 1 &&
                Double.parseDouble(parts[2]) >= 0 &&
                Double.parseDouble(parts[4]) >= 0 &&
                Double.parseDouble(parts[5]) >= 0 &&
                Double.parseDouble(parts[7]) >= 0 &&
                Double.parseDouble(parts[8]) >= 0) {
            return true;
        }
        System.err.println("Linha inválida: " + line);
        return false;
    }
}
