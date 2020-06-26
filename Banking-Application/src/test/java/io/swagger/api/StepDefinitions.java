package io.swagger.api.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
import org.threeten.bp.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private Transaction transaction;

    private Account testAccount = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,51L);
    private Account testAccount2 = new Account(Account.TypeofaccountEnum.DEPOSIT, new BigDecimal(-10.00), true,51L);
    private Users testUser = new Users("Test", "Employee", "1", "1", true, Users.TypeofuserEnum.EMPLOYEE);

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "http://localhost:8080/transaction";

    @When("I retrieve all transaction")
    public void i_retrieve_all_transaction() throws URISyntaxException {
        URI uri = new URI(baseUrl);
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
        URI uri = new URI(baseUrl + "/" + id);
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
        Transaction transaction = new Transaction(0L, testAccount, testAccount2, new BigDecimal(10.00), testUser, OffsetDateTime.now());
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(transaction), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I retrieve transaction from user {int}")
    public void iRetrieveTransactionFromUserWithId(int id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "/user/" + id);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);

    }

    @When("I retrieve transaction from account {string}")
    public void iRetrieveTransactionFromAccountWithId(String id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "/account/" + id);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);

    }
}
