package io.swagger.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.Users;
import io.swagger.service.AccountService;
import io.swagger.service.TransactionService;
import io.swagger.service.UsersService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.threeten.bp.OffsetDateTime;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class TransactionApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService service;

    @MockBean
    private UsersService usersService;

    @MockBean
    private AccountService accountService;

    private Transaction transaction;

    private Account testAccount1 =  new Account(Account.TypeofaccountEnum.SAVING, new BigDecimal(-10.00), true,51L);
    private Account testAccount2 = new Account(Account.TypeofaccountEnum.DEPOSIT, new BigDecimal(-10.00), true,51L);
    private Users testUser = new Users(51L,"Test", "Employee", "1", "1", true, Users.TypeofuserEnum.EMPLOYEE);
    private BigDecimal amount = new BigDecimal(10.00);

    private List<Account> testAccounts = accountService.getAccountsByUserId(51L);
    private Users users = usersService.getUserById(51L);

    @Test
    public void contexLoad() throws Exception {
        assertThat(service).isNotNull();
    }

    @BeforeEach
    public void setup(){
        transaction = new Transaction(testAccounts.get(0), testAccounts.get(1), new BigDecimal(10.00), users);
    }

    @Test
    public void getAllTransactionShouldReturnJsonArray() throws Exception {
        when(service.getAllTransactions()).thenReturn(Arrays.asList(transaction));
        this.mvc.perform(get("/transaction")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount").value(transaction.getAmount())
        );
    }/*

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


}