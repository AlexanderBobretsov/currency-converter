package ru.bobretsoff.currencyconverterbackend.service;

import org.springframework.stereotype.Service;
import ru.bobretsoff.currencyconverterbackend.model.CurrencyConverterHistory;

import java.util.List;

@Service
public interface CurrencyConverterHistoryService {
    /** добавление операции в историю операций CurrencyConverterHistory. */
    CurrencyConverterHistory create(
            CurrencyConverterHistory currencyConverterHistory);
    /** удаление операции из истории операций CurrencyConverterHistory. */
    void delete(long id);
    /** получение данных из истории операции CurrencyConverterHistory. */
    List<CurrencyConverterHistory> getAllCurrencyConverterHistories();
}
