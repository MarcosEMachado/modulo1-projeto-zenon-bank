package main.java.br.com.zenon.service;

import main.java.br.com.zenon.fraud.Transaction;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> csvToList (Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)){
            String line;
            br.readLine();
            var list = new ArrayList<Transaction>();
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
                list.add(transacao);
                if (list.size() == 1000)
                    break;
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
