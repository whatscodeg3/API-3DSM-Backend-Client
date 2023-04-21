package com.br.WhatsCodeClientMicroservice.repository;

import com.br.WhatsCodeClientMicroservice.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
