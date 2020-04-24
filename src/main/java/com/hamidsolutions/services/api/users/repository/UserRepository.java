package com.hamidsolutions.services.api.users.repository;

import com.hamidsolutions.services.api.users.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository extends ReactiveMongoRepository<User,String> {
    Mono<User> findOneByEmailAndDisableIsFalse(String email);
    Mono<Optional<User>> findOneByEmail(String email);
    Mono<User> findByEmail(String email);
    Mono<User> findByEmailAndActivateIsTrue(String email);
    Mono<User> findOneByCreateAccountKey(String key);
}
