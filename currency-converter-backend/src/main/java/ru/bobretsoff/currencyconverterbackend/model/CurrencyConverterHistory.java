package ru.bobretsoff.currencyconverterbackend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CurrencyConverterHistory {
    /** поле для хранения идентификатора. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** поле для хранения charcode валюты1. */
    @Column
    private String currency1Charcode;
    /** поле для хранения charcode валюты2. */
    @Column
    private String currency2Charcode;
    /** поле для хранения курса конвертации. */
    @Column
    private String course;
    /** поле для хранения суммы конвертации. */
    @Column
    private String currency1Sum;
    /** поле для хранения полученной суммы. */
    @Column
    private String currency2Sum;
    /** поле для хранения статуса обмена. */
    @Column
    private String statusExchange;
    /** поле для хранения даты обмена. */
    @Column
    private String dateExchange;
    /** конструктор. */
    public CurrencyConverterHistory() {
        this.id = id;
        this.currency1Charcode = currency1Charcode;
        this.currency2Charcode = currency2Charcode;
        this.course = course;
        this.currency1Sum = currency1Sum;
        this.currency2Sum = currency2Sum;
        this.statusExchange = statusExchange;
        this.dateExchange = dateExchange;
    }
    /** геттеры-сеттеры. */
    public Long getId() {
        return id;
    }
    /** геттеры-сеттеры. */
    public void setId(final Long id) {
        this.id = id;
    }
    /** геттеры-сеттеры. */
    public String getCurrency1Charcode() {
        return currency1Charcode;
    }
    /** геттеры-сеттеры. */
    public void setCurrency1Charcode(final String currency1Charcode) {
        this.currency1Charcode = currency1Charcode;
    }
    /** геттеры-сеттеры. */
    public String getCurrency2Charcode() {
        return currency2Charcode;
    }
    /** геттеры-сеттеры. */
    public void setCurrency2Charcode(final String currency2Charcode) {
        this.currency2Charcode = currency2Charcode;
    }
    /** геттеры-сеттеры. */
    public String getCourse() {
        return course;
    }
    /** геттеры-сеттеры. */
    public void setCourse(final String course) {
        this.course = course;
    }
    /** геттеры-сеттеры. */
    public String getCurrency1Sum() {
        return currency1Sum;
    }
    /** геттеры-сеттеры. */
    public void setCurrency1Sum(final String currency1Sum) {
        this.currency1Sum = currency1Sum;
    }
    /** геттеры-сеттеры. */
    public String getCurrency2Sum() {
        return currency2Sum;
    }
    /** геттеры-сеттеры. */
    public void setCurrency2Sum(final String currency2Sum) {
        this.currency2Sum = currency2Sum;
    }
    /** геттеры-сеттеры. */
    public String getStatusExchange() {
        return statusExchange;
    }
    /** геттеры-сеттеры. */
    public void setStatusExchange(final String statusExchange) {
        this.statusExchange = statusExchange;
    }
    /** геттеры-сеттеры. */
    public String getDateExchange() {
        return dateExchange;
    }
    /** геттеры-сеттеры. */
    public void setDateExchange(final String dateExchange) {
        this.dateExchange = dateExchange;
    }
}
