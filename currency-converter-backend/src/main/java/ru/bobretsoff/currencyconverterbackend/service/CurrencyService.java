package ru.bobretsoff.currencyconverterbackend.service;

import org.springframework.stereotype.Service;
import ru.bobretsoff.currencyconverterbackend.model.Currency;

import java.util.List;

@Service
public interface CurrencyService {

    /** сохрание результат парсинга в таблицу Currency. */
    void save(Currency currency);
    /** получение списка компаний. */
    List<Currency> getAllCurrencies();
    /** удаление всех записей из таблицы Currency. */
    void delete();

}
