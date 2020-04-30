package com.howtech.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.transaction.annotation.Transactional;

import com.howtech.model.User;

public interface UserRepository extends Neo4jRepository<User, Long> {
	boolean existsByUsername(String username);
	
	User findByUsername(String username);
	
	@Transactional
	void deleteByUsername(String username);
}
