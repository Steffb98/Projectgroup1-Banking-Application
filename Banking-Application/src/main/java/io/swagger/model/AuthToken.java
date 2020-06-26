package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

/**
 * AuthToken
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-26T12:45:00.697Z[GMT]")
public class AuthToken {

    @Id
    @JsonProperty("authToken")
    private String authToken = null;
    private Long userId = null;

    public AuthToken authToken(String authToken, Long userId){
        this.authToken = authToken;
        this.userId = userId;
        return this;
    }

    /**
     * Get authToken
     * @return authToken
     */

    @ApiModelProperty(example = "b15938252a78", required = true, value = "")
        @NotNull

    public String getAuthToken(){
        return authToken;
    }
    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public Long getUserId(){
        return  userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if(o == null || getClass() != o.getClass()){
            return false;
        }
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(this.authToken, authToken.authToken);
    }

    @Override
    public int hashCode(){
        return Objects.hash(authToken);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("class AuthToken {\n");
        stringBuilder.append("    authToken: ").append(toIndentedString(authToken)).append("\n");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null){
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
