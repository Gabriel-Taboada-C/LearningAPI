package com.gabriel.practice.Clients;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <ClientEntity,Long> {

    Optional <ClientEntity> findByName(String name);

}
