package fr.lernejo.travelsite.records;

import javax.validation.constraints.*;

public record Client (
    @NotNull @Email String userEmail,
    @NotNull @Size(min=2, max=64) String userName,
    @NotNull String userCountry,
    @NotNull WeatherExpectation weatherExpectation,
    @Positive @Max(40) int minimumTemperatureDistance
)
{
}
