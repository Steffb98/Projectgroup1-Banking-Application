package io.swagger.api;

import io.swagger.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@Controller
public class AccountApiController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private AccountService accountService;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountApiController(ObjectMapper objectMapper, HttpServletRequest request, AccountService accountService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.accountService = accountService;
    }

    public ResponseEntity createAcc(@ApiParam(value = "Account object that needs to be added to the store" ,required=true )  @Valid @RequestBody Account body
) {
        String accept = request.getHeader("Accept");
        try {
            accountService.CreateAccount(body);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<Account>> listAccounts() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"balance\" : 10.00,\n  \"iban\" : \"NL00RABO0123456789\",\n  \"isactive\" : true,\n  \"typeofaccount\" : \"saving\",\n  \"minimumbalance\" : -10.00\n}, {\n  \"balance\" : 10.00,\n  \"iban\" : \"NL00RABO0123456789\",\n  \"isactive\" : true,\n  \"typeofaccount\" : \"saving\",\n  \"minimumbalance\" : -10.00\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> listSpecificAccount(@NotNull @ApiParam(value = "ID of user", required = true) @Valid @RequestParam(value = "userId", required = true) Long userId
,@ApiParam(value = "Id of account",required=true) @PathVariable("accountId") String accountId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"balance\" : 10.00,\n  \"iban\" : \"NL00RABO0123456789\",\n  \"isactive\" : true,\n  \"typeofaccount\" : \"saving\",\n  \"minimumbalance\" : -10.00\n}, {\n  \"balance\" : 10.00,\n  \"iban\" : \"NL00RABO0123456789\",\n  \"isactive\" : true,\n  \"typeofaccount\" : \"saving\",\n  \"minimumbalance\" : -10.00\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> toggleStatusAcc(@ApiParam(value = "AccountID to set to active or inactive",required=true) @PathVariable("accountId") String accountId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
