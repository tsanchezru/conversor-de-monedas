package com.ejemplo.conversordemonedas.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.ejemplo.conversordemonedas.model.CurrencyConverter;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private ComboBox<String> fromCurrency;
    @FXML
    private ComboBox<String> toCurrency;
    @FXML
    private TextField amountField;

    @SuppressWarnings("unused")
    @FXML
    private Button convertButton;

    @FXML
    private Label resultLabel;

    private final String[] currencyCodes = {
            "USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "COP", "BRL"
    };

    @FXML
    private void initialize() {
        fromCurrency.getItems().addAll(currencyCodes);
        toCurrency.getItems().addAll(currencyCodes);
        fromCurrency.setValue("USD");
        toCurrency.setValue("COP");
    }

    @FXML
    private void handleConvert() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = fromCurrency.getValue();
            String to = toCurrency.getValue();

            double result = CurrencyConverter.convert(from, to, amount);
            resultLabel.setText(String.format("%.2f %s", result, to));
        } catch (Exception e) {
            resultLabel.setText("Error en la conversión");
        }
    }
}
