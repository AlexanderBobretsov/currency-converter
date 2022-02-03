package ru.bobretsoff.currencyconverterbackend.job;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bobretsoff.currencyconverterbackend.model.Currency;
import ru.bobretsoff.currencyconverterbackend.repository.CurrencyRepository;
import ru.bobretsoff.currencyconverterbackend.service.CurrencyService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Parser {
    /**
     * автоматическая инъекция CurrencyService.
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
     * выполнение кода при запуске приложения.
     */
    @Scheduled(initialDelay = 50,fixedDelay = Long.MAX_VALUE)
    public void parseCurrency() {
        /** адрес для парсинга. */
        String url = "http://www.cbr.ru/scripts/XML_daily.asp";


        /** получение текущей даты. */
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String vDateNow =  formatForDateNow.format(dateNow);
        //System.out.println(vDateNow);
        /** получение даты из БД. */
        String vDateInDB = currencyService.getAllCurrencies().toString();
        if (vDateInDB.length()>2) {
            vDateInDB = vDateInDB.substring(87,97);
        }
        //vDateNow="04.02.2022";

        //System.out.println("Текущая дата:" + vDateNow + "Дата в БД:" + vDateInDB);

        /** условие - если текущая дата не совпадает с датой в БД, то обновляем с сайта ЦБ РФ. */
        if (vDateInDB.equals(vDateNow)==false) {


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
                    Element valCursDate = doc.getAllElements().first();

                    String vChCode = chCode.ownText();
                    String vName = name.ownText();
                    String vValue = value.ownText();
                    String vValCursDate = valCursDate.toString().substring(valCursDate.toString().indexOf("ValCurs Date=") + 14, valCursDate.toString().indexOf("ValCurs Date=") + 24);


                    Currency currency = new Currency();
                    currency.setCharcode(vChCode);
                    currency.setName(vName);
                    currency.setValue(vValue);
                    currency.setValCursDate(vValCursDate);

                    currencyService.save(currency);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
