package com.howtech.security.repositories;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.howtech.models.User;


public interface UserRepository extends Neo4jRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
