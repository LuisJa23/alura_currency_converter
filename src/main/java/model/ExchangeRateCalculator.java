package model;

import java.util.Map;

public class ExchangeRateCalculator {
    private ExchangeRateResponse exchangeRateResponse;
    private Map<String, Double> exchangeRate;
    private String baseCurrency;
    private String targetCurrency;
    private double amount;

    public ExchangeRateCalculator(String baseCurrency, String targetCurrency, double amount) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        exchangeRateResponse = new ExchangeRateResponse();
        exchangeRate = exchangeRateResponse.getExchangeRate();

    }

    public double calculateExchangeRate() {
        double baseCurrencyRate = exchangeRate.get(baseCurrency);
        double targetCurrencyRate = exchangeRate.get(targetCurrency);
        return (amount / baseCurrencyRate) * targetCurrencyRate;
    }


}
