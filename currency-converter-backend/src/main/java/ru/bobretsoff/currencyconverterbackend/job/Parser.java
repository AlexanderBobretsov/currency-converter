package ru.bobretsoff.currencyconverterbackend.job;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bobretsoff.currencyconverterbackend.model.Currency;
import ru.bobretsoff.currencyconverterbackend.service.CurrencyService;

import java.io.IOException;

@Component
public class Parser {
    /**
     * автоматическая инъекция CurrencyService, CurrencyConverterHistoryService.
     */
    private final CurrencyService currencyService;
    /**
     * инъекция зависимости.
     */
    @Autowired
    public Parser(final CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * выполнение кода при запуске приложения, а также каждый час.
     */
    @Scheduled(initialDelay = 50, fixedDelay=3600000)
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

            for (int i = 0;
                 i < doc.getAllElements().select("CharCode").size(); i++) {
                Element chCode =
                        doc.getAllElements().select("CharCode").get(i);
                Element name = doc.getAllElements().select("Name").get(i);
                Element value = doc.getAllElements().select("Value").get(i);
                Element valCursDate = doc.body();

                String vChCode = chCode.ownText();
                String vName = name.ownText();
                String vValue = value.ownText();
                String vValCurDate = valCursDate.text();

                System.out.println(vValCurDate);

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
