package io.swagger.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.Users;
import io.swagger.service.TransactionService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean private TransactionService service;
    private Transaction transaction;

    private final Account testAccount = new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,51L);
    private final Account testAccount2 = new Account(Account.TypeofaccountEnum.DEPOSIT, new BigDecimal(-10.00), true,51L);
    private final Users testUser = new Users("Test", "Employee", "1", "1", true, Users.TypeofuserEnum.EMPLOYEE);

    /*@BeforeEach
    public void setup(){
        transaction = new Transaction(testAccount, testAccount2, new BigDecimal(10.00), testUser);
    }

    @Test
    public void getAllTransactionShouldReturnJsonArray() throws Exception {
        given(service.getAllTransactions()).willReturn(Arrays.asList(transaction));
        this.mvc.perform(get("/transaction")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount").value(transaction.getAmount())
        );
    }

    @Test
    public void callingAllTransactionsShouldReturnOK() throws Exception {
        this.mvc.perform(get("/transaction")).andExpect(status().isOk());
    }

    @Test
    public void postingATransactionSchouldReturn201Created() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Transaction transaction = new Transaction(testAccount, testAccount2, new BigDecimal(10.00), testUser);
        this.mvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated());
    }*/

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "http://localhost:8080/api/transaction";

    @When("I retrieve all transaction")
    public void iRetreiveAllTransactions() throws URISyntaxException{
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("I get http status {int}")
    public void iGetHttpStatus(int status){
        Assert.assertEquals(responseEntity.getStatusCodeValue(), status);
    }

    @Then("I get a list of {int} transactions")
    public void iGetAListOfTransactions(int size) throws JSONException{
        response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        Assert.assertEquals(size, array.length());
    }

    @When("I retrieve transaction with id {int}")
    public void iRetrieveTransactionWithId(int id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "/" + id);
        responseEntity = template.getForEntity(uri, String.class);
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
        Transaction transaction = new Transaction(testAccount, testAccount2, new BigDecimal(10.00), testUser);
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(transaction), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }
}