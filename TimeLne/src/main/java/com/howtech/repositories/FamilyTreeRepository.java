package com.howtech.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.howtech.models.FamilyTree;

@RepositoryRestResource (collectionResourceRel = "trees", path = "trees")
public interface FamilyTreeRepository extends PagingAndSortingRepository<FamilyTree, Long> {

}