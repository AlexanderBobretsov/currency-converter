package ru.bobretsoff.currencyconverterbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bobretsoff.currencyconverterbackend.job.Parser;
import ru.bobretsoff.currencyconverterbackend.model.Currency;
import ru.bobretsoff.currencyconverterbackend.model.CurrencyConverterHistory;
import ru.bobretsoff.currencyconverterbackend.service.CurrencyConverterHistoryService;
import ru.bobretsoff.currencyconverterbackend.service.CurrencyService;

import java.util.List;

@RequestMapping("/api/v1/currency")
@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class CurrencyController {

    /** инъекция интерфейсов CurrencyService, CurrencyConverterHistoryService.*/
    private final CurrencyService currencyService;
    private final CurrencyConverterHistoryService currencyConverterHistoryService;
    private final Parser parser;

    /** автоматическая инъекция зависимости.*/
    @Autowired
    public CurrencyController(CurrencyService currencyService, CurrencyConverterHistoryService currencyConverterHistoryService, Parser parser) {
        this.currencyService = currencyService;
        this.currencyConverterHistoryService = currencyConverterHistoryService;
        this.parser = parser;
    }


    /** обработчик get-запроса /all. получение информации о текущих валютах и курсах. */
    @GetMapping(path = "/all", produces = "application/json")
    public List<Currency> getAllCurrencies() {
        parser.parseCurrency();
        return currencyService.getAllCurrencies();
    }

    /** обработчик get-запроса /all/history. получение информации об истории операций. */
    @GetMapping(path = "/all/history", produces = "application/json")
    public List<CurrencyConverterHistory> getAllCurrencyConverterHistories() {
        return currencyConverterHistoryService.getAllCurrencyConverterHistories();
    }

    /** обработчик Post-запроса /history. добавление информации в историю операций. */
    @PostMapping(path = "/history", consumes = "application/json", produces = "application/json")
    public CurrencyConverterHistory create(@RequestBody CurrencyConverterHistory currencyConverterHistory) {
       return currencyConverterHistoryService.create(currencyConverterHistory);
    }

    /** обработчик delete-запроса /history. удаление информации из истории операций. */
    @DeleteMapping(path = "/history/{id}")
    public void delete(@PathVariable("id") long id) {
        currencyConverterHistoryService.delete(id);
    }
}
