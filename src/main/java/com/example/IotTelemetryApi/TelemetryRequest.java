package com.example.IotTelemetryApi;

import jakarta.validation.constraints.NotBlank;

public class TelemetryRequest {

    @NotBlank(message = "metric must not be blank")
    private String metric;

    private double value;

    public String getMetric() { return metric; }
    public void setMetric(String metric) { this.metric = metric; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}