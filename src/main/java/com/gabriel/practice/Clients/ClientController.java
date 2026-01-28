package com.gabriel.practice.Clients;

import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ClientEntity>> getClients() {
        
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable Long id) {
        
        return ResponseEntity.ok(clientService.getClientsById(id));
    }

    @PostMapping //No lleva ("/{id}") porque el id lo genera la base de datos
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity client) {

        ClientEntity saved = clientService.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> updateClient(@PathVariable Long id, @RequestBody ClientEntity client) {

        return ResponseEntity.ok(clientService.updateClient(id, client));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient (@PathVariable Long id) {
        clientService.deleteClient(id);

        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
