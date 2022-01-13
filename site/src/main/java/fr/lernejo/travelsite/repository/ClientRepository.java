package fr.lernejo.travelsite.repository;

import fr.lernejo.travelsite.records.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();

    Client findByUsername(String name);

    void addClient(Client client);
}
