package main;

import model.ExchangeRate;
import model.ExchangeRateCalculator;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    private final Map<String, String> SUPPORTED_CURRENCIES = ExchangeRate.getSupportedCurrencies();
    private ExchangeRateCalculator exchangeRateCalculator;

    private Scanner scanner;


    public Main() {
        scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        System.out.println("---------------------" + "\n" +
                            "Conversor de monedas" + "\n" +
                            "1. Convertir moneda" + "\n" +
                            "2. Salir" + "\n" +
                           "---------------------");
        int option = 0;

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número");
            scanner.next();
            showMainMenu();
        }

        switch (option) {
            case 1:
                showBaseCurrencyMenu();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Opción invalida");
                showMainMenu();
                break;
        }


    }

    public void showBaseCurrencyMenu() {
        System.out.println("Seleccione la moneda de origen: ");
        String baseCurrency = null;
        int i = 1;
        for (Map.Entry<String, String> entry : SUPPORTED_CURRENCIES.entrySet()) {

            System.out.println(i + ". " + entry.getKey() + ". " + entry.getValue());
            i++;
        }
        int option = 0;
        try {
            option = scanner.nextInt();
            baseCurrency = (String) Array.get(SUPPORTED_CURRENCIES.keySet().toArray(), option - 1);
            showTargetCurrencyMenu(baseCurrency);
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número");
            scanner.next();
            showBaseCurrencyMenu();
        }
    }

    public void showTargetCurrencyMenu(String baseCurrency) {
        System.out.println("Seleccione la moneda de destino: ");
        String targetCurrency = null;
        ArrayList<String> targetCurrencyList = new ArrayList();
        for (Map.Entry<String, String> entry : SUPPORTED_CURRENCIES.entrySet()) {

            if (entry.getKey().equals(baseCurrency)) {
                continue;
            }
            targetCurrencyList.add(entry.getKey());

        }

        for (int i = 0; i < targetCurrencyList.size(); i++) {
            System.out.println((i + 1) + ". " + targetCurrencyList.get(i) + ". " + SUPPORTED_CURRENCIES.get(targetCurrencyList.get(i)));
        }
        int option = 0;
        try {
            option = scanner.nextInt();
            targetCurrency = targetCurrencyList.get(option -1);
            showAmountMenu(baseCurrency, targetCurrency);
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número");
            scanner.next();
            showTargetCurrencyMenu(baseCurrency);
        }

    }

    public void showAmountMenu(String baseCurrency, String targetCurrency) {
        System.out.println("Ingrese la cantidad de " + baseCurrency + " que desea convertir a " + targetCurrency);
        double amount = 0;
        try {
            amount = scanner.nextDouble();
            exchangeRateCalculator = new ExchangeRateCalculator(baseCurrency, targetCurrency, amount);
            double result = exchangeRateCalculator.calculateExchangeRate();
            DecimalFormat df = new DecimalFormat("#.####");
            String formattedResult = df.format(result);
            System.out.println(amount + " " + baseCurrency + " es equivalente a " + formattedResult + " " + targetCurrency);
            showMainMenu();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número");
            scanner.next();
            showAmountMenu(baseCurrency, targetCurrency);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.showMainMenu();
    }








}
