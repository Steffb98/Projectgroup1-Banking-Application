package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Random;

import io.swagger.dao.AccountRepository;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T12:47:35.450Z[GMT]")
@Entity
  public class Account   {

  public Account(TypeofaccountEnum typeofaccount, Integer userid) {
    this.iban = GenerateIBAN();
    this.balance = new BigDecimal(0.00);
    this.typeofaccount = typeofaccount;
    this.userid = userid;
  }

  @Id
  @JsonProperty("iban")
  private String iban = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  @JsonProperty("userid")
  private Integer userid = null;

  /**
   * Gets or Sets typeofaccount
   */
  public enum TypeofaccountEnum {
    SAVING("saving"),
    
    DEPOSIT("deposit");

    private String value;

    TypeofaccountEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeofaccountEnum fromValue(String text) {
      for (TypeofaccountEnum b : TypeofaccountEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("typeofaccount")
  private TypeofaccountEnum typeofaccount = null;

  public Account iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * Get iban
   * @return iban
  **/
  @ApiModelProperty(example = "NL00RABO0123456789", required = true, value = "")
      @NotNull

    public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public Account balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  **/
  @ApiModelProperty(example = "10.00", required = true, value = "")
      @NotNull

    @Valid
    public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Account userid(Integer userid) {
    this.userid = userid;
    return this;
  }

  /**
   * Get userid
   * @return userid
   **/
  @ApiModelProperty(example = "10.00", required = true, value = "")
  @NotNull

  @Valid
  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  public Account typeofaccount(TypeofaccountEnum typeofaccount) {
    this.typeofaccount = typeofaccount;
    return this;
  }

  /**
   * Get typeofaccount
   * @return typeofaccount
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public TypeofaccountEnum getTypeofaccount() {
    return typeofaccount;
  }

  public void setTypeofaccount(TypeofaccountEnum typeofaccount) {
    this.typeofaccount = typeofaccount;
  }

  public Account() {
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.iban, account.iban) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.typeofaccount, account.typeofaccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, balance, typeofaccount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    typeofaccount: ").append(toIndentedString(typeofaccount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public String GenerateIBAN(){
    Random rand = new Random();
    String IBAN = "NL";
    for (int i = 0; i < 2; i++) {
      int n = rand.nextInt(10) + 0;
      IBAN += Integer.toString(n);
    }
    IBAN += " BLUE";
    for (int i = 0; i < 13; i++) {
      if (i % 5 == 0) {
        IBAN += " ";
      } else {
        int n = rand.nextInt(10) + 0;
        IBAN += Integer.toString(n);
      }
    }
    return IBAN;
  }
}