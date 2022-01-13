package fr.lernejo.prediction;

import fr.lernejo.prediction.records.Temperatures;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TemperatureController {
    private final TemperatureService service;

    public TemperatureController(TemperatureService service) {
        this.service = service;
    }

    @GetMapping("temperature")
    public Temperatures temperature(@RequestParam(value="country") String country) {
        try {
            return new Temperatures(
                country,
                service.getTemperature(country),
                service.getTemperature(country)
            );
        } catch(UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Unknown country");
        }
    }
}
