package it.aitho.fabrickonboarding.repository;

import it.aitho.fabrickonboarding.entity.MoneyTransferTransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<MoneyTransferTransactionEntity, String> {

}
