package io.swagger.dao;

import io.swagger.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    /**
     * Returns
     *
     * @param userid
     * @return
     */
    List<Account> findAccountsByUserid(Long userid);
}
