package io.swagger.model;

import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T12:47:35.450Z[GMT]")
@Entity
public class Transaction   {

  @Id
  @SequenceGenerator(name = "transaction_seq", initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("from")
  @ManyToOne
  private Account from = null;

  @JsonProperty("to")
  @ManyToOne
  private Account to = null;

  @JsonProperty("amount")
  private Long amount = null;

  @JsonProperty("by")
  @ManyToOne
  private Users by = null;

  @JsonProperty("date")
  private LocalDateTime date = null;

  public Transaction id(Long id) {
    this.id = id;
    return this;
  }

  public Transaction() {
  }

  public Transaction(Account from, Account to, Long amount, Users by, LocalDateTime date) {
    this.from = from;
    this.to = to;
    this.amount = amount;
    this.by = by;
    this.date = date;
  }


    /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction from(Account from) {
    this.from = from;
    return this;
  }

  /**
   * AccountId form the account
   * @return from
  **/
  @ApiModelProperty(required = true, value = "AccountId form the account")
      @NotNull

    public Account getFrom() {
    return from;
  }

  public void setFrom(Account from) {
    this.from = from;
  }

  public Transaction to(Account to) {
    this.to = to;
    return this;
  }

  /**
   * AccountId from the account
   * @return to
  **/
  @ApiModelProperty(required = true, value = "AccountId from the account")
      @NotNull

    public Account getTo() {
    return to;
  }

  public void setTo(Account to) {
    this.to = to;
  }

  public Transaction amount(Long amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Transaction by(Users by) {
    this.by = by;
    return this;
  }

  /**
   * UserId from the user
   * @return by
  **/
  @ApiModelProperty(required = true, value = "UserId from the user")
      @NotNull

    public Users getBy() {
    return by;
  }

  public void setBy(Users by) {
    this.by = by;
  }

  public Transaction date(LocalDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")

    @Valid
    public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }



  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.from, transaction.from) &&
        Objects.equals(this.to, transaction.to) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.by, transaction.by) &&
        Objects.equals(this.date, transaction.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, from, to, amount, by, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    by: ").append(toIndentedString(by)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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
}
