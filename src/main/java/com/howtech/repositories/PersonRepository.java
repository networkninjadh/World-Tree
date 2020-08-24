package com.howtech.repositories;

import org.springframework.data.repository.CrudRepository;

import com.howtech.models.Person;


/**
@RepositoryRestResource (collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	List<Person> findByLastName(@Param("lastName") String lastName);
}
**/

public interface PersonRepository extends CrudRepository<Person, Long> {

	Person findByFirstName();
	
}