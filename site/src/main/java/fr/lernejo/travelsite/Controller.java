package fr.lernejo.travelsite;

import fr.lernejo.travelsite.records.Client;
import fr.lernejo.travelsite.records.Country;
import fr.lernejo.travelsite.repository.ClientLocalService;
import fr.lernejo.travelsite.repository.ClientRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    private final ClientRepository repository = new ClientLocalService();

    @PostMapping("inscription")
    public void inscription(@Valid @RequestBody Client client) {
        repository.addClient(client);
    }

    @GetMapping("travels")
    public Iterable<Country> travels(@RequestParam(value="userName") String userName) {

        return Arrays.asList(
            new Country("Caribbean", 32.4f),
            new Country("Australia", 35.1f)
        );
    }
}
