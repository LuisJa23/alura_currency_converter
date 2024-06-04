package model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

public class ExchangeRate {
    private final Map<String, String>  SUPPORTED_CURRENCIES = Map.of(
            "USD", "Dólar estadounidense",
            "ARS", "Peso argentino",
            "BRL", "Real brasileño",
            "CLP", "Peso chileno",
            "COP", "Peso colombiano",
            "BOB", "Boliviano boliviano"
    );
    private Map<String, Double> conversionRates;


    public ExchangeRate(String json) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        ExchangeRate exchangeRate = gson.fromJson(json, ExchangeRate.class);
        this.conversionRates = exchangeRate.conversionRates;
    }

    public Map<String, Double> getConversionRates() {
        Map<String, Double> conversionRatesValue = new java.util.HashMap<>(Map.of());

        for (Map.Entry<String, Double> entry : conversionRates.entrySet()) {
            if (SUPPORTED_CURRENCIES.containsKey(entry.getKey())) {
                conversionRatesValue.put(entry.getKey(), entry.getValue());
            }
        }
        return conversionRatesValue;
    }
}
