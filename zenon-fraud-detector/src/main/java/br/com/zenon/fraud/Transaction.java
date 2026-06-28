package main.java.br.com.zenon.fraud;

public record Transaction(int step,
                          String type,
                          double amount,
                          String nameOrig,
                          double oldbalanceOrg,
                          double newbalanceOrig,
                          String nameDest,
                          double oldbalanceDest,
                          double newbalanceDest,
                          boolean isFraud,
                          boolean isFlaggedFraud) {
}
