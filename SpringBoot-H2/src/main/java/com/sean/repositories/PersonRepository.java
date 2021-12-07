package com.sean.repositories;

import com.sean.models.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
