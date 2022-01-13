package fr.lernejo.travelsite.records;

public record Country(String country, double temperature) {
    public Country(String country, double temperature) {
        this.country = country;
        this.temperature = temperature;
    }
}
