package it.aitho.fabrickonboarding.service.impl;

import it.aitho.fabrickonboarding.client.FabrickClient;
import it.aitho.fabrickonboarding.dto.accountbalance.AccountBalancePayload;
import it.aitho.fabrickonboarding.dto.transactions.GetTransactionsPayload;
import it.aitho.fabrickonboarding.entity.MoneyTransferTransactionEntity;
import it.aitho.fabrickonboarding.mapper.TransactionsMapper;
import it.aitho.fabrickonboarding.repository.TransactionRepository;
import it.aitho.fabrickonboarding.repository.TransactionTypeRepository;
import it.aitho.fabrickonboarding.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final FabrickClient fabrickClient;
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionsMapper transactionsMapper;

    public AccountServiceImpl(FabrickClient fabrickClient, TransactionRepository transactionRepository, TransactionTypeRepository transactionTypeRepository, TransactionsMapper transactionsMapper) {
        this.fabrickClient = fabrickClient;
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionsMapper = transactionsMapper;
    }

    @Override
    public AccountBalancePayload retrieveAccountBalance(String accountId) {
        return fabrickClient.retrieveAccountBalance(accountId).getPayload();
    }

    @Override
    public GetTransactionsPayload retrieveAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate) {
        var result = fabrickClient.retrieveAccountTransactions(accountId, fromAccountingDate, toAccountingDate).getPayload();
        var transactionsEntities = Optional.of(result)
                .map(GetTransactionsPayload::getList)
                .orElse(new ArrayList<>())
                .stream()
                .map(transactionsMapper::map)
                .collect(Collectors.toList());
        if (!transactionsEntities.isEmpty()) {
            transactionTypeRepository.saveAll(transactionsEntities
                    .stream()
                    .map(MoneyTransferTransactionEntity::getType)
                    .collect(Collectors.toSet())
            );
            transactionRepository.saveAll(transactionsEntities);
        }
        return result;
    }
}
