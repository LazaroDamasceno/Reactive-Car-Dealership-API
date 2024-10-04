package com.api.v1.domain.users;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface UsersRepository extends ReactiveCrudRepository<Users, UUID> {
}
