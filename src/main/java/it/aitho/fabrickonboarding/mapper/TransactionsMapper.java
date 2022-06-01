package it.aitho.fabrickonboarding.mapper;

import it.aitho.fabrickonboarding.dto.transactions.Transaction;
import it.aitho.fabrickonboarding.entity.MoneyTransferTransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionsMapper {
    MoneyTransferTransactionEntity map(Transaction dto);
}
