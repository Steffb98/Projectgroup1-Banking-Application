package io.swagger.dao;

import io.swagger.model.ApiKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeyRepository extends CrudRepository<ApiKey, String> {
    /**
     * Returns
     *
     * @param key
     * @return
     */
    List<ApiKey> findApiKeyByKey(String key);
    ApiKey findById(String key);
}
