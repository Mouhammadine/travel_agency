package fr.lernejo.travelsite.services;

import fr.lernejo.travelsite.PredictionEngineClient;
import fr.lernejo.travelsite.records.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class PredictionSimpleService implements PredictionService {
    private final PredictionEngineClient client;

    public PredictionSimpleService(PredictionEngineClient client) {
        this.client = client;
    }

    private Stream<String> listCountries() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
            assert inputStream != null;

            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return content.lines();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error: can't read country list");
        }
    }

    private ResponseStatusException apiError(String message) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error: problem with prediction API: " + message);
    }

    private Country country(String country) {
        try {
            Temperatures temp = client.getTemperatures(country).execute().body();

            if (temp == null)
                throw apiError("no return");

            OptionalDouble op = temp.temperatures.stream().mapToDouble(t -> t.temperature).average();
            if (op.isEmpty())
                throw apiError("no return");
            else
                return new Country(country, op.getAsDouble());
        } catch (IOException e) {
            throw apiError(e.getMessage());
        }
    }

    @Override
    public boolean countryExists(String country) {
        return listCountries().anyMatch(s -> s.equalsIgnoreCase(country));
    }

    @Override
    public Iterable<Country> getMatchingCountries(Client client) {
        double countryTemp = country(client.userCountry()).temperature();
        Predicate<Country> temperatureOk;

        if (client.weatherExpectation() == WeatherExpectation.COLDER) {
            temperatureOk = t -> t.temperature() <= countryTemp - client.minimumTemperatureDistance();
        } else {
            temperatureOk = t -> t.temperature() >= countryTemp + client.minimumTemperatureDistance();
        }

        return listCountries()
            .filter(s -> !s.equalsIgnoreCase(client.userCountry()))
            .map(this::country)
            .filter(temperatureOk)
            .toList();
    }
}
