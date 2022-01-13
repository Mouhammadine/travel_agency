package fr.lernejo.travelsite.records;

public record Country(String country, float temperature) {
    public Country(String country, float temperature) {
        this.country = country;
        this.temperature = temperature;
    }
}
