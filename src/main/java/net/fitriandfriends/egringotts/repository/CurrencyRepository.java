package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    // Find a currency by its id
    public Currency findByCurrencyID(Long currencyID);

    // Find a currency by its name
    public Currency findByName(String name);

    // Find a currency by its abbreviation
    public Currency findByAbbreviation(String abbreviation);

    // Find all currencies
    public List<Currency> findAll();

    // Other queries

}