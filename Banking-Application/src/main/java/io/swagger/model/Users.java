package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Users
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@Entity
public class Users {

  @Id
  @SequenceGenerator(name = "user_seq", initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("firstname")
  private String firstname = null;

  @JsonProperty("lastname")
  private String lastname = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("isactive")
  private Boolean isactive = null;

  @JsonProperty("typeofuser")
  private Users.TypeofuserEnum typeofuser = null;

  public TypeofuserEnum getTypeofuser() {
    return typeofuser;
  }

  public void setTypeofuser(TypeofuserEnum typeofuser) {
    this.typeofuser = typeofuser;
  }

  public enum TypeofuserEnum {
    EMPLOYEE("employee"),

    CUSTOMER("customer");

    private String value;

    TypeofuserEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static Users.TypeofuserEnum fromValue(String text) {
      for (Users.TypeofuserEnum b : Users.TypeofuserEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  public Users(Long id, String firstname, String lastname, String email, String password, Boolean isactive, TypeofuserEnum typeofuser) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.isactive = isactive;
    this.typeofuser = typeofuser;
  }

  public Users(String firstname, String lastname, String email, String password, Boolean isactive, TypeofuserEnum typeofuser) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.isactive = isactive;
    this.setTypeofuser(typeofuser);
  }

  public Users() {
  }

  public Users id(Long id) {
    this.id = id;
    return this;
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

  public Users firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    if (firstname == ""){
      throw new IllegalArgumentException("FirstName cannot be empty");
    }
    this.firstname = firstname;
  }

  public Users lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    if (lastname == ""){
      throw new IllegalArgumentException("LastName cannot be empty");
    }
    this.lastname = lastname;
  }

  public Users email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(example = "inholland@student.nl", required = true, value = "")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    if (email == ""){
      throw new IllegalArgumentException("Email cannot be empty");
    }
    this.email = email;
  }

  public Users password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    if (password == ""){
      throw new IllegalArgumentException("Password cannot be empty");
    }
    this.password = password;
  }

  public Users isactive(Boolean isactive) {
    this.isactive = isactive;
    return this;
  }

  /**
   * Get isactive
   * @return isactive
  **/
  @ApiModelProperty(example = "true", required = true, value = "")
      @NotNull

    public Boolean isIsactive() {
    return isactive;
  }

  public void setIsactive(Boolean isactive) {
    this.isactive = isactive;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Users users = (Users) o;
    return Objects.equals(this.id, users.id) &&
        Objects.equals(this.firstname, users.firstname) &&
        Objects.equals(this.lastname, users.lastname) &&
        Objects.equals(this.email, users.email) &&
        Objects.equals(this.password, users.password) &&
        Objects.equals(this.isactive, users.isactive) &&
         Objects.equals(this.getTypeofuser(), users.getTypeofuser());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, email, password, isactive, getTypeofuser());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Users {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    isactive: ").append(toIndentedString(isactive)).append("\n");
    sb.append("    typeofuser: ").append(toIndentedString(getTypeofuser())).append("\n");
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
