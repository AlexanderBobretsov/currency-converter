package ru.bobretsoff.currencyconverterbackend.model;

public interface HistoryProjection {
    String getCurrency1Charcode();
    String getCurrency2Charcode();
    String getCurrency1Sum();
    String getCurrency2Sum();
    String getCourse();

}
