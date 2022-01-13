package fr.lernejo.travelsite.services;

import fr.lernejo.travelsite.records.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findByUsername(String name);

    void addClient(Client client);
}
