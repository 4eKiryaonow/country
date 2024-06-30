package guru.qa.country.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.country.data.CountryEntity;

import java.util.Date;

public record Country(
        @JsonProperty("name")
        String name,
        @JsonProperty("code")
        String code,
        @JsonProperty("dayOfIndependence")
        Date dayOfIndependence) {

    public static Country fromEntity(CountryEntity countryEntity) {
        return new Country(
                countryEntity.getName(),
                countryEntity.getCode(),
                countryEntity.getDate()
        );
    }
}
