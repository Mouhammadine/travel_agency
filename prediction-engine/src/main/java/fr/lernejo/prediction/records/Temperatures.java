package fr.lernejo.prediction.records;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public record Temperatures(String country, List<Temperature> temperatures) {
    public Temperatures(String country, double temp1, double temp2) {
        this(country, Instant.now(), temp1, temp2);
    }

    public Temperatures(String country, Instant day, double temp1, double temp2) {
        this(country, new ArrayList<>());

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("UTC"));

        Instant yesterday = day.minus(1, ChronoUnit.DAYS);

        this.temperatures.add(new Temperature(dateFormat.format(day), temp1));
        this.temperatures.add(new Temperature(dateFormat.format(yesterday), temp2));
    }
}
