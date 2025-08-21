package com.gabriel.practice.Clients;

import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController (ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientEntity> getClients() {
        
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public ClientEntity getClientById(@PathVariable Long id) {
        
        return clientService.getClientsById(id);
    }

    @PostMapping("/{id}")
    public ClientEntity createClient(@RequestBody ClientEntity client) {

        return clientService.saveClient(client);
    }
    
    @PutMapping("/{id}")
    public ClientEntity updateClient(@PathVariable Long id, @RequestBody ClientEntity client) {
        
        return clientService.updateClient(id, client);
    }
    
    @DeleteMapping("/{id}")
    public String deleteClient (@PathVariable Long id) {
        clientService.deleteClient(id);

        return "El cliente con el id: " + id + "se elimino correctamente.";
    }
}
