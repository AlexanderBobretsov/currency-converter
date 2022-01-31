package ru.bobretsoff.currencyconverterbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bobretsoff.currencyconverterbackend.model.Currency;
import ru.bobretsoff.currencyconverterbackend.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    /**  автоинъекция интерфейса repository.  */
    private final CurrencyRepository repository;
    /**  автоинъекция зависимости. */
    @Autowired
    public CurrencyServiceImpl(final CurrencyRepository repository) {
        this.repository = repository;
    }
    /**  переопредление метода сохранения данных в таблицу Currency.  */
    @Override
    public void save(final Currency currency) {
        repository.save(currency);
    }
    /**  переопредление метода удаления данных из таблицы Currency.  */
    @Override
    public void delete() {
        repository.deleteAll();
    }
    /**  переопредление метода получения данных из таблицы Currency.  */
    @Override
    public List<Currency> getAllCurrencies() {
        return repository.findAll();
    }
}
