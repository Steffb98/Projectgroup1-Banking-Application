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

    public ResponseEntity createAcc(@ApiParam(value = "Account object that needs to be added to the store" ,required=true )  @Valid @RequestBody Account account
)   {
        String accept = request.getHeader("Accept");
        try {
            accountService.CreateAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(account);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //TODO: Make simpler method, Duplicate code met togglestatus?
    public ResponseEntity<Account> getAccountByIban(@NotNull @ApiParam(value = "Account of iban to show", required = true) @Valid @RequestParam(value = "iban", required = true) String iban
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if (iban.length() == 22) {
                Account acc = accountService.getAccountsByIban(iban);
                if (acc != null){
                    return ResponseEntity.status(HttpStatus.OK).body(acc);
                }
                else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(acc);
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accountService.getAccountsByIban(iban));
            }
        }
        //TODO: verwijderen???
        return new ResponseEntity<Account>(HttpStatus.I_AM_A_TEAPOT);
    }

    public ResponseEntity<List<Account>> getAccountByUserID(@NotNull @ApiParam(value = "Account of user to show", required = true) @Valid @RequestParam(value = "userId", required = true) Long userId
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountsByUserId(userId));
            } catch (IllegalArgumentException iae) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        //TODO: verwijderen???
        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity toggleStatusAcc(@ApiParam(value = "AccountID to set to active or inactive",required=true) @PathVariable("accountId") String iban
)   {
        String accept = request.getHeader("Accept");
        try {
            if (iban.length() == 22) {
                Account acc = accountService.getAccountsByIban(iban);
                if (acc != null) {
                    accountService.ToggleActivity(iban);
                    return ResponseEntity.status(HttpStatus.OK).body(iban);
                }
                else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(acc);
                }
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accountService.getAccountsByIban(iban));
            }
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
