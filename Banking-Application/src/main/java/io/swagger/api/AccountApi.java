/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@Api(value = "account", description = "the account API")
public interface AccountApi {

    @ApiOperation(value = "Creates an account for a user", nickname = "createAcc", notes = "", tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/account",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createAcc(@ApiParam(value = "Account object that needs to be added to the store" ,required=true )  @Valid @RequestBody Account body
    );

    @ApiOperation(value = "get account by Iban", nickname = "getAccountByIban", notes = "Calling this method will retrieve an account with the provided iban", response = Account.class, tags={ "accounts", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "search results matching criteria", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "accounts not found") })
    @RequestMapping(value = "/account/{accountId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Account> getAccountByIban(@NotNull @ApiParam(value = "Account of iban to show", required = true) @Valid @RequestParam(value = "iban", required = true) String iban
    );

    @ApiOperation(value = "get account by userid", nickname = "getAccountByUserID", notes = "Calling this method will retrieve accounts based on the userID", response = Account.class, responseContainer = "List", tags={ "accounts", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "search results matching criteria", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "accounts not found") })
    @RequestMapping(value = "/account/listaccount",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> getAccountByUserID(@NotNull @ApiParam(value = "Accounts of user to show", required = true) @Valid @RequestParam(value = "userId", required = true) Long userId
    );


    @ApiOperation(value = "Changes account to inactive/active", nickname = "toggleStatusAcc", notes = "", tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Account not found") })
    @RequestMapping(value = "/account/{accountId}",
        method = RequestMethod.PUT)
    ResponseEntity<Void> toggleStatusAcc(@ApiParam(value = "AccountID to set to active or inactive",required=true) @PathVariable("accountId") String accountId
);

}
