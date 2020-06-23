package io.swagger.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ApiKey {

    public ApiKey(){};

    @Id
    private String key;

    public ApiKey(String key){
        this.key = key;
    }
}
