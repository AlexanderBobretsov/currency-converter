package ru.bobretsoff.currencyconverterbackend.job;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bobretsoff.currencyconverterbackend.model.Currency;
import ru.bobretsoff.currencyconverterbackend.model.CurrencyConverterHistory;
import ru.bobretsoff.currencyconverterbackend.service.CurrencyConverterHistoryService;
import ru.bobretsoff.currencyconverterbackend.service.CurrencyService;

import java.io.IOException;

@Component
public class Parser {


    /**
     * автоматическая инъекция companyService.
     */
    private final CurrencyService currencyService;
    private final CurrencyConverterHistoryService currencyConverterHistoryService;

    @Autowired
    public Parser(CurrencyService currencyService, CurrencyConverterHistoryService currencyConverterHistoryService) {
        this.currencyService = currencyService;
        this.currencyConverterHistoryService = currencyConverterHistoryService;
    }

    /**
     * выполнение кода по расписанию каждые 10 мин.
     */
    @Scheduled(initialDelay = 50, fixedDelay=Long.MAX_VALUE)
    public void parseCompany() {
        String url = "http://www.cbr.ru/scripts/XML_daily.asp";

        try {
            Document doc = Jsoup.connect(url)
                    .parser(org.jsoup.parser.Parser.xmlParser())
                    .userAgent("Chrome")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            currencyService.delete();

            for (int i = 0; i < doc.getAllElements().select("CharCode").size(); i++) {
                Element chCode = doc.getAllElements().select("CharCode").get(i);
                Element name = doc.getAllElements().select("Name").get(i);
                Element value = doc.getAllElements().select("Value").get(i);

                String vChCode = chCode.ownText();
                String vName = name.ownText();
                String vValue = value.ownText();

                Currency currency = new Currency();
                currency.setCharcode(vChCode);
                currency.setName(vName);
                currency.setValue(vValue);

                currencyService.save(currency);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
