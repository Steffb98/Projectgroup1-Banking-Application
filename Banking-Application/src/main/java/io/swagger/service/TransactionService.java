package io.swagger.service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.SecurityUserDetails;
import io.swagger.model.Transaction;
import io.swagger.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public boolean checkAuthorization(String iban){
        Account acc = accountService.getAccountByIban(iban);
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users u = ((SecurityUserDetails) o).getUser();

        if (! acc.getUserid().equals(u.getId()) && !((SecurityUserDetails) o).getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            return false;
        }
        return true;
    }

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
        return transactionRepository.findById(id).orElse(null);
    }

    public List<Transaction> getAllTransactionsFromUser(Long userId) { return (List<Transaction>) transactionRepository.getAllByBy_IdOrderByDate(userId); }

    public List<Transaction> getAllTransactionsFromAccount(String accountId) { return (List<Transaction>) transactionRepository.getTransactionsByAccount(accountId); }
}
