package io.swagger.api;

import io.swagger.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.AccountService;
import io.swagger.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@CrossOrigin(origins = {"http://localhost"})
@Controller
public class AccountApiController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private AccountService accountService;
    private UsersService usersService;

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

    public ResponseEntity<Account> getAccountByIban(@NotNull @ApiParam(value = "Account of iban to show", required = true) @Valid @RequestParam(value = "iban", required = true) String iban
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if (iban.length() == 22) {
                Account acc = accountService.getAccountByIban(iban);
                try{
                    if (acc == null){
                        throw new IllegalArgumentException();
                    }
                    else{
                        return ResponseEntity.status(HttpStatus.OK).body(acc);
                    }
                } catch(IllegalArgumentException iae){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else{
            return new ResponseEntity<Account>(HttpStatus.FORBIDDEN);
        }
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
        else{
            return new ResponseEntity<List<Account>>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity toggleActivityAcc(@ApiParam(value = "AccountID to set to active or inactive",required=true) @PathVariable("accountId") String iban
)   {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if (iban.length() == 22) {
                try{
                    Account acc = accountService.getAccountByIban(iban);
                    if (acc == null){
                        throw new IllegalArgumentException();
                    }
                    else{
                        accountService.ToggleAccountActivity(iban);
                        return ResponseEntity.status(HttpStatus.OK).build();
                    }
                } catch(IllegalArgumentException iae){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
