package com.br.WhatsCodeClientMicroservice.repository;

import com.br.WhatsCodeClientMicroservice.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT * from Client WHERE cpf=:cpf", nativeQuery = true)
    Client findByCpf(@Param("cpf")String cpf);

}
