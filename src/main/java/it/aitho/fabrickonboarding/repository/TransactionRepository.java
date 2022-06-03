package it.aitho.fabrickonboarding.repository;

import it.aitho.fabrickonboarding.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, String> {

}
