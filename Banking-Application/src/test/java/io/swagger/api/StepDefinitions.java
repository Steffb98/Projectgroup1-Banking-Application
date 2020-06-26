package io.swagger.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.dao.AccountRepository;
import io.swagger.dao.UsersRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.Users;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import org.threeten.bp.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private Transaction transaction;
    private Users user;
    private Account account;

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "http://localhost:8080/";

    // USER TESTS!!!

    @When("I retrieve all users")
    public void iRetrieveAllUsers() throws URISyntaxException {
        URI uri = new URI(baseUrl + "users");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("I get a list of {int} users")
    public void iGetAListOfUsers(int size) throws JSONException {
        response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        Assert.assertEquals(size, array.length());
    }

    @When("I retrieve user with id {int}")
    public void iRetrieveUserWithId(int id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "users/" + id);
        try {
            responseEntity = template.getForEntity(uri, String.class);
        } catch (HttpClientErrorException e) {
            byte[] bytes = e.getResponseBodyAsByteArray();

            //Convert byte[] to String
            String s = new String(bytes);
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            System.out.println(s);
        }
    }

    @When("I post an user")
    public void iPostAnUser() throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        Integer email = new Random().nextInt(63724);
        user = new Users(4L,"Tfrtgyest", "hjgjhgk", email.toString(), "45", true, Users.TypeofuserEnum.EMPLOYEE);
        URI uri = new URI(baseUrl + "users");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(user), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I Change the status of an user with id {int}")
    public void iChangeUserStatusWithId(int id) throws URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();

        user = new Users("Test", "Employee","456" , "145", true, Users.TypeofuserEnum.EMPLOYEE);
        URI uri = new URI(baseUrl + "users/" + id);
        //responseEntity = template.put(uri, mapper);
    }

    @When("I retrieve user with name {string}")
    public void iRetrieveUserWithName(String name) throws URISyntaxException {
        URI uri = new URI(baseUrl + "users/search?name=" + name);
        try {
            responseEntity = template.getForEntity(uri, String.class);
        } catch (HttpClientErrorException e) {
            byte[] bytes = e.getResponseBodyAsByteArray();

            //Convert byte[] to String
            String s = new String(bytes);
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            System.out.println(s);
        }
    }

    // ACCOUNT TESTS!!

    @When("I retrieve all accounts")
    public void iRetrieveAllAccounts() throws URISyntaxException {
        URI uri = new URI(baseUrl + "account");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("I get a list of {int} accounts")
    public void iGetAListOfAccounts(int size) throws JSONException {
        response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        Assert.assertEquals(size, array.length());
    }

    @When("I retrieve account with id {string}")
    public void iRetrieveAccountWithId(String id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "account/?iban=" + id);
        try {
            responseEntity = template.getForEntity(uri, String.class);
        } catch (HttpClientErrorException e) {
            byte[] bytes = e.getResponseBodyAsByteArray();

            //Convert byte[] to String
            String s = new String(bytes);
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            System.out.println(s);
        }
    }

    @When("I post an account")
    public void iPostAnAccount() throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        account = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,51L);
        URI uri = new URI(baseUrl + "account");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(account), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I retrieve account with userId {int}")
    public void iRetrieveAccountWithUserId(int id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "account/listaccount?userId=" + id);
        try {
            responseEntity = template.getForEntity(uri, String.class);
        } catch (HttpClientErrorException e) {
            byte[] bytes = e.getResponseBodyAsByteArray();

            //Convert byte[] to String
            String s = new String(bytes);
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            System.out.println(s);
        }

    }
    // TRANSACTION TESTS!!

    @When("I retrieve all transaction")
    public void i_retrieve_all_transaction() throws URISyntaxException {
        URI uri = new URI(baseUrl + "transaction");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("I get http status {int}")
    public void iGetHttpStatus(int status){
        Assert.assertEquals(responseEntity.getStatusCodeValue(), status);
    }

    @Then("I get a list of {int} transactions")
    public void iGetAListOfTransactions(int size) throws JSONException {
        response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        Assert.assertEquals(size, array.length());
    }

    @When("I retrieve transaction with id {int}")
    public void iRetrieveTransactionWithId(int id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "transaction/" + id);
        try {
            responseEntity = template.getForEntity(uri, String.class);
        } catch (HttpClientErrorException e){
            byte[] bytes = e.getResponseBodyAsByteArray();

            //Convert byte[] to String
            String s = new String(bytes);
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            System.out.println(s);
        }
    }

    @Then("The transaction amount is {string}")
    public void theTransactionAmountIs(String amount) throws JSONException {
        response = responseEntity.getBody();
        Assert.assertEquals(amount,
                new JSONObject(response)
                        .getString("amount"));
    }

    @When("I post a transaction")
    public void iPostATransaction() throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        transaction = new Transaction(1L, account, account, new BigDecimal(10.00), user);
        URI uri = new URI(baseUrl + "transaction");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(transaction), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I retrieve transaction from user {int}")
    public void iRetrieveTransactionFromUserWithId(int id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "transaction/user/" + id);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);

    }

    @When("I retrieve transaction from account {string}")
    public void iRetrieveTransactionFromAccountWithId(String id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "transaction/account/" + id);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);

    }

}
