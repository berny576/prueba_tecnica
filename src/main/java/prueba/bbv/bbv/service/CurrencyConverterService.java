package prueba.bbv.bbv.service;

import prueba.bbv.bbv.dto.ConversionRequest;
import prueba.bbv.bbv.dto.ConversionResponse;
import prueba.bbv.bbv.exception.CurrencyNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

@Service
public class CurrencyConverterService {

    private static final String API_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=10124780aa73c83cd1e5b667cf8af774";

    public ConversionResponse convertCurrency(ConversionRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);

        if (response == null || !response.containsKey("rates")) {
            throw new RuntimeException("Error al obtener las tasas de conversión.");
        }

        @SuppressWarnings("unchecked")
        Map<String, Double> rates = (Map<String, Double>) response.get("rates");

        Double fromRate = null;
        Double toRate = null;

        try {
            fromRate = getDoubleValue(rates.get(request.getFromCurrency()));    
        } catch (Exception e) {
            throw new CurrencyNotFoundException("Moneda origen no encontrada: " + request.getFromCurrency());
        }

        try {
            toRate = getDoubleValue(rates.get(request.getToCurrency()));
        } catch (Exception e) {
            throw new CurrencyNotFoundException("Moneda destino no encontrada: " + request.getToCurrency());
        }

        Double eurAmount =  request.getAmount() / fromRate;
        Double convertedAmount = eurAmount * toRate;

        return new ConversionResponse(
                LocalDate.now(),
                toRate,
                request.getAmount(),
                convertedAmount
        );
    }

    private Double getDoubleValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        throw new RuntimeException("El valor no es un número válido: " + value);
    }
    
}