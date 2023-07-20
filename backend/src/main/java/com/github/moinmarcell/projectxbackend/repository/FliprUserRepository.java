package com.github.moinmarcell.projectxbackend.repository;

import com.github.moinmarcell.projectxbackend.model.FliprUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FliprUserRepository extends MongoRepository<FliprUser, String> {
    Optional<FliprUser> findByEmail(String email);

    Optional<FliprUser> findByUsernameIgnoreCase(String username);
}
