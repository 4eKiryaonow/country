package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.ex.CountryNotFoundException;
import guru.qa.country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository
                .findAll()
                .stream()
                .map(e -> new Country(e.getName(), e.getCode(), e.getDate()))
                .toList();
    }

    public Country addCountry(Country country) {
        return Country.fromEntity(countryRepository.save(CountryEntity.fromJson(country)));
    }

    public Country getCountry(String code) {
        CountryEntity countryEntity = countryRepository.findCountryByCode(code)
                .orElseThrow(() -> new CountryNotFoundException(
                        "Can`t find country by given code: " + code
                ));
        return Country.fromEntity(countryEntity);
    }

    @Transactional
    public Country editCountry(String code, Country country) {
        CountryEntity countryEntity = countryRepository.findCountryByCode(code)
                .orElseThrow(() -> new CountryNotFoundException(
                        "Can`t find country by given code: " + code
                ));
        countryEntity.setName(country.name());
        countryEntity.setCode(country.code());
        countryEntity.setDate(country.dayOfIndependence());
        return Country.fromEntity(countryRepository.save(countryEntity));
    }
}
