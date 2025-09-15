package com.gabriel.practice.Clients;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService (ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientEntity> getClients () {
        return clientRepository.findAll();
    }

    public ClientEntity getClientsById (Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El cliente con el id: " + id + " no se encontró."));
    }

    public ClientEntity saveClient (ClientEntity client) {
        return clientRepository.save(client);
    }

    public ClientEntity updateClient (Long id, ClientEntity updatedClient) {
        ClientEntity existingClient = clientRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("El cliente con el id: " + id + " no se encontró."));
        existingClient.setName(updatedClient.getName());
        existingClient.setContact(updatedClient.getContact());
        existingClient.setNumber(updatedClient.getNumber());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setDirection(updatedClient.getDirection());

        return clientRepository.save(existingClient);
    }

    public void deleteClient (Long id) {
        ClientEntity client = clientRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("El cliente con el id: " + id + " no se encontró."));
        clientRepository.delete(client);
    }
}
