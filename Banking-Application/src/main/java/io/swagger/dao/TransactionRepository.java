package io.swagger.dao;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Iterable<Transaction> getAllByBy_IdOrderByDate(Long userId);

    @Query("select t from Transaction t where t.receiver.iban = ?1 or t.sender.iban = ?1")
    Iterable<Transaction> getTransactionsByAccount(String accountId);
}
