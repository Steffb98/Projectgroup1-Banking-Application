package io.swagger.model;

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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@Entity
public class Transaction   {

  @Id
  @SequenceGenerator(name = "transaction_seq", initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
  @JsonProperty("id")
  private Long id = null;

  @ManyToOne
  @JsonProperty("sender")
  private Account sender = null;

  @ManyToOne
  @JsonProperty("receiver")
  private Account receiver = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @ManyToOne
  @JsonProperty("by")
  private Users by = null;

  @JsonProperty("date")
  private OffsetDateTime date = null;

  public Transaction id(Long id) {
    this.id = id;
    return this;
  }

  public Transaction(Account sender, Account receiver, BigDecimal amount, Users by) {
    this.sender = sender;
    this.receiver = receiver;
    this.amount = amount;
    this.by = by;
    this.date = OffsetDateTime.now();
  }

  public Transaction() {
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

  public Transaction sender(Account sender) {
    this.sender = sender;
    return this;
  }

  /**
   * iban form the sender account
   * @return sender
  **/
  @ApiModelProperty(required = true, value = "iban form the sender account")
      @NotNull

    public Account getSender() {
    return sender;
  }

  public void setSender(Account sender) {
    this.sender = sender;
  }

  public Transaction receiver(Account receiver) {
    this.receiver = receiver;
    return this;
  }

  /**
   * iban from the receiver account
   * @return receiver
  **/
  @ApiModelProperty(required = true, value = "iban from the receiver account")
      @NotNull

    public Account getReceiver() {
    return receiver;
  }

  public void setReceiver(Account receiver) {
    this.receiver = receiver;
  }

  public Transaction amount(BigDecimal amount) {
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
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
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

  public Transaction date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
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
        Objects.equals(this.sender, transaction.sender) &&
        Objects.equals(this.receiver, transaction.receiver) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.by, transaction.by) &&
        Objects.equals(this.date, transaction.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sender, receiver, amount, by, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    receiver: ").append(toIndentedString(receiver)).append("\n");
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
