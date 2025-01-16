package prueba.bbv.bbv.dto;


import java.time.LocalDate;

public class ConversionResponse {
    private LocalDate date;
    private Double conversionRate;
    private Double originalAmount;
    private Double convertedAmount;

    public ConversionResponse(LocalDate date, Double conversionRate, Double originalAmount, Double convertedAmount) {
        this.date = date;
        this.conversionRate = conversionRate;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(Double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    





}