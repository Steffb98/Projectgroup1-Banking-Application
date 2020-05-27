package io.swagger.api;

import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.TransactionService;
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
public class TransactionApiController implements TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private TransactionService transactionService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionApiController(ObjectMapper objectMapper, HttpServletRequest request, TransactionService transactionService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionService = transactionService;
    }

    public ResponseEntity addTransaction(@ApiParam(value = "Transaction object that needs to be added to the store" ,required=true )  @Valid @RequestBody Transaction body
) {
        String accept = request.getHeader("Accept");
        try {
            transactionService.addTransaction(body);
            return  ResponseEntity.status(HttpStatus.OK).body(body);
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
