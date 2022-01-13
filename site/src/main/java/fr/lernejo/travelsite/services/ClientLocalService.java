package fr.lernejo.travelsite.services;

import fr.lernejo.travelsite.records.Client;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientLocalService implements ClientService {
    private final Map<String, Client> clientMap = new HashMap<>();

    @Override
    public List<Client> findAll() {
        return clientMap.values().stream().toList();
    }

    @Override
    public Client findByUsername(String name) {
        if (!clientMap.containsKey(name)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist");
        }

        return clientMap.get(name);
    }

    @Override
    public void addClient(Client client) {
        if (clientMap.containsKey(client.userName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already used");
        }

        clientMap.put(client.userName(), client);
    }
}
