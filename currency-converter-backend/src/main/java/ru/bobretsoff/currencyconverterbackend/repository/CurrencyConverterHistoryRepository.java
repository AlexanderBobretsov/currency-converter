package ru.bobretsoff.currencyconverterbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bobretsoff.currencyconverterbackend.model.CurrencyConverterHistory;
import ru.bobretsoff.currencyconverterbackend.model.HistoryProjection;

import java.util.List;

@Repository
public interface CurrencyConverterHistoryRepository extends
        JpaRepository<CurrencyConverterHistory, Long> {

 //   @Query("select new ru.bobretsoff.currencyconverterbackend.model.HistoryStat (c.currency1Charcode,c.currency2Charcode,c.course as sum(c.course),sum(currency1Sum),sum(c.currency2Sum)) from CurrencyConverterHistory c group by c.currency1Charcode,c.currency2Charcode")
    //    List<HistoryStat> findMyStatistics();


    @Query(value = "select currency1charcode, currency2charcode, SUM (CAST (currency1sum as real) ) as currency1sum, SUM (CAST (currency2sum as REAL) ) as currency2sum, AVG (CAST (course as REAL) ) as course from currency_converter_history group by currency1charcode,currency2charcode", nativeQuery = true)
    List<HistoryProjection> findMyStatistics();
}
