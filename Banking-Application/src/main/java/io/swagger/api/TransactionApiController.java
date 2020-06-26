package io.swagger.api;

import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.AccountService;
import io.swagger.service.TransactionService;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@CrossOrigin(origins = {"http://localhost"})
@Controller
public class TransactionApiController implements TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private TransactionService transactionService;

    private AccountService accountService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionApiController(ObjectMapper objectMapper, HttpServletRequest request, TransactionService transactionService, AccountService accountService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    public ResponseEntity addTransaction(@ApiParam(value = "Transaction object that needs to be added to the store" ,required=true )  @Valid @RequestBody Transaction body
) {
        String accept = request.getHeader("Accept");
        try {

        if(body.getAmount().compareTo(body.getSender().getTransactionLimit()) == 1 || body.getAmount().compareTo(new BigDecimal(0)) == -1){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else if(body.getSender().getBalance().subtract(body.getAmount()).compareTo(body.getSender().getMinimumbalance()) == -1){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            } else if (transactionService.getAllTransactionsFromAccount(body.getSender().getIban()).size() == body.getSender().getDayLimit()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            } else {
                body.getSender().setBalance(body.getSender().getBalance().subtract(body.getAmount()));
                body.getReceiver().setBalance(body.getReceiver().getBalance().add(body.getAmount()));
                accountService.updateAmount(body.getSender());
                accountService.updateAmount(body.getReceiver());
                transactionService.addTransaction(body);
                return ResponseEntity.status(HttpStatus.OK).body(body);
            }

        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public ResponseEntity<List<Transaction>> getAllTransactions() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return ResponseEntity.status(200).body(transactionService.getAllTransactions());
            } catch (IllegalArgumentException iae) {
                return ResponseEntity.status(400).build();
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> getTransactionById(@ApiParam(value = "ID of transaction to return",required=true) @PathVariable("transactionId") Long transactionId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return ResponseEntity.status(200).body(transactionService.getTransactionById(transactionId));
            } catch (IllegalArgumentException iae) {
                log.error("Not a valid ID", iae);
                return ResponseEntity.status(400).build();
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransactionFromUser(@ApiParam(value = "ID of a user",required=true) @PathVariable("userId") Long userId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return ResponseEntity.status(200).body(transactionService.getAllTransactionsFromUser(userId));
            } catch (IllegalArgumentException iae) {
                log.error("Not a valid ID", iae);
                return ResponseEntity.status(400).build();
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransactionFromAcount(String acountId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return ResponseEntity.status(200).body(transactionService.getAllTransactionsFromAccount(acountId));
            } catch (IllegalArgumentException iae) {
                log.error("Not a valid ID", iae);
                return ResponseEntity.status(400).build();
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
