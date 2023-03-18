package com.org.athtec.SpringCrudSQL.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.org.athtec.SpringCrudSQL.domain.Persona;

public interface TransactionalRepository extends MongoRepository<Persona, Long>
{

}
