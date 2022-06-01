package it.aitho.fabrickonboarding.repository;

import it.aitho.fabrickonboarding.entity.TransactionTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends CrudRepository<TransactionTypeEntity, String> {

}
