package ru.bobretsoff.currencyconverterbackend.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Currency {

    /** поле для хранения идентификатора. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** поле для хранения charcode. */
    @Column
    private String charcode;
    /** поле для хранения name. */
    @Column
    private String name;
    /** поле для хранения value. */
    @Column
    private String value;
    /** конструктор. */
    public Currency() {
        this.id = id;
        this.charcode = charcode;
        this.name = name;
        this.value = value;
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
    public String getCharcode() {
        return charcode;
    }
    /** геттеры-сеттеры. */
    public void setCharcode(final String charcode) {
        this.charcode = charcode;
    }
    /** геттеры-сеттеры. */
    public String getName() {
        return name;
    }
    /** геттеры-сеттеры. */
    public void setName(final String name) {
        this.name = name;
    }
    /** геттеры-сеттеры. */
    public String getValue() {
        return value;
    }
    /** геттеры-сеттеры. */
    public void setValue(final String value) {
        this.value = value;
    }
}
