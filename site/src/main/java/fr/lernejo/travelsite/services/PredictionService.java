package fr.lernejo.travelsite.services;

import fr.lernejo.travelsite.records.Client;
import fr.lernejo.travelsite.records.Country;

public interface PredictionService {
    boolean countryExists(String country);

    Iterable<Country> getMatchingCountries(Client client);
}
