package ru.bobretsoff.currencyconverterbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bobretsoff.currencyconverterbackend.model.CurrencyConverterHistory;

public interface CurrencyConverterHistoryRepository extends
        JpaRepository<CurrencyConverterHistory, Long> {
}
