package io.swagger.service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.stereotype.Service;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public void addTransaction(Transaction transaction) {
        transaction.setDate(OffsetDateTime.now());
        transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findOne(id);
    }

    public List<Transaction> getAllTransactionsFromUser(Long userId) { return (List<Transaction>) transactionRepository.getAllByBy_IdOrderByDate(userId); }

    public List<Transaction> getAllTransactionsFromAccount(String accountId) { return (List<Transaction>) transactionRepository.getTransactionsByAccount(accountId); }
}
