package com.br.WhatsCodeClientMicroservice.repository;

import com.br.WhatsCodeClientMicroservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
