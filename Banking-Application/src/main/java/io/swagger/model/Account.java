package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Random;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@Entity
public class Account   {

  @Id
  @JsonProperty("iban")
  private String iban = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

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

  @JsonProperty("minimumbalance")
  private BigDecimal minimumbalance = null;

  @JsonProperty("isactive")
  private Boolean isactive = null;

  @JsonProperty("userid")
  private Long userid = null;

  public Account(TypeofaccountEnum typeofaccount, BigDecimal minimumbalance, Boolean isactive, Long userid) {
    this.iban = GenerateIBAN();
    this.balance = new BigDecimal(0.00);
    this.typeofaccount = typeofaccount;
    this.minimumbalance = minimumbalance;
    this.isactive = isactive;
    this.userid = userid;
  }

  public Account() {
  }

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

  public Account minimumbalance(BigDecimal minimumbalance) {
    this.minimumbalance = minimumbalance;
    return this;
  }

  /**
   * Get minimumbalance
   * @return minimumbalance
  **/
  @ApiModelProperty(example = "-10.00", required = true, value = "")
      @NotNull

    @Valid
    public BigDecimal getMinimumbalance() {
    return minimumbalance;
  }

  public void setMinimumbalance(BigDecimal minimumbalance) {
    this.minimumbalance = minimumbalance;
  }

  public Account isactive(Boolean isactive) {
    this.isactive = isactive;
    return this;
  }

  /**
   * Get isactive
   * @return isactive
  **/
  @ApiModelProperty(example = "true", required = true, value = "")
      @NotNull

    public Boolean getIsactive() {
    return isactive;
  }

  public void setIsactive(Boolean isactive) {
    this.isactive = isactive;
  }

  public Account userid(Long userid) {
    this.userid = userid;
    return this;
  }

  /**
   * Get isactive
   * @return isactive
   **/
  @ApiModelProperty(example = "0", required = true, value = "")
  @NotNull

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
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
        Objects.equals(this.typeofaccount, account.typeofaccount) &&
        Objects.equals(this.minimumbalance, account.minimumbalance) &&
        Objects.equals(this.isactive, account.isactive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, balance, typeofaccount, minimumbalance, isactive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    typeofaccount: ").append(toIndentedString(typeofaccount)).append("\n");
    sb.append("    minimumbalance: ").append(toIndentedString(minimumbalance)).append("\n");
    sb.append("    isactive: ").append(toIndentedString(isactive)).append("\n");
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
    IBAN += " INHO";
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
