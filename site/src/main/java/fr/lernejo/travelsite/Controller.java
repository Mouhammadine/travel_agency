package fr.lernejo.travelsite;

import fr.lernejo.travelsite.records.Client;
import fr.lernejo.travelsite.records.Country;
import fr.lernejo.travelsite.services.ClientService;
import fr.lernejo.travelsite.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    private final ClientService clients;
    private final PredictionService prediction;

    public Controller(ClientService clients, PredictionService prediction) {
        this.clients = clients;
        this.prediction = prediction;
    }

    @PostMapping("inscription")
    public void inscription(@Valid @RequestBody Client client) {
        if (!prediction.countryExists(client.userCountry()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given country doesn't exists");

        clients.addClient(client);
    }

    @GetMapping("travels")
    public Iterable<Country> travels(@RequestParam(value="userName") String userName) {
        Client client = clients.findByUsername(userName);

        try {
            return prediction.getMatchingCountries(client);
        } catch (ResponseStatusException e) {
            return new ArrayList<>();
        }
    }
}
