package ru.bobretsoff.currencyconverterbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bobretsoff.currencyconverterbackend.model.CurrencyConverterHistory;
import ru.bobretsoff.currencyconverterbackend.repository.CurrencyConverterHistoryRepository;

import java.util.List;

@Service
public class CurrencyConverterHistoryServiceImpl implements
        CurrencyConverterHistoryService {
    /**  автоинъекция интерфейса repository_history.  */
    private final CurrencyConverterHistoryRepository repositoryHistory;
    /**  автоинъекция зависимости.  */
    @Autowired
    public CurrencyConverterHistoryServiceImpl(
            final CurrencyConverterHistoryRepository repositoryHistory) {
        this.repositoryHistory = repositoryHistory;
    }
    /**  переопредление метода добавления данных в историю.  */
    @Override
    public CurrencyConverterHistory create(
            final CurrencyConverterHistory currencyConverterHistory) {
        return repositoryHistory.save(currencyConverterHistory);
    }
    /**  Переопредление метода получения данных из истории.  */
    @Override
    public List<CurrencyConverterHistory> getAllCurrencyConverterHistories() {
        return repositoryHistory.findAll();
    }
    /**  Переопредление метода удаления данных из истории.  */
    @Override
    public void delete(final long id) {
        repositoryHistory.deleteById(id);
    }

}
