package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ExchangeRateResponse {
    private static final String API_KEY = "4ddc28767c68e1931b10efe3";
    private static final String URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private ExchangeRate exchangeRate;

    public ExchangeRateResponse(){

    }

    public Map<String, Double> getExchangeRate(){

        String json = null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            json = response.body();

            exchangeRate = new ExchangeRate(json);


        }catch (Exception e) {
            e.printStackTrace();
        }
        return exchangeRate.getConversionRates();
   }
}