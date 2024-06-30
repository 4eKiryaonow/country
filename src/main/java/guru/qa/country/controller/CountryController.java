package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    Country getCountry(@PathVariable String code) {
        return countryService.getCountry(code);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PatchMapping("/edit/{code}")
    @ResponseStatus(HttpStatus.OK)
    Country editCountry(@PathVariable String code, @RequestBody Country country) {
        return countryService.editCountry(code, country);
    }
}
