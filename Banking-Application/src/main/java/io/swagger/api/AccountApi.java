/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T12:47:35.450Z[GMT]")
@Api(value = "account", description = "the account API")
public interface AccountApi {

    @ApiOperation(value = "Creates an account for a user", nickname = "createAcc", notes = "", tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/account",
        consumes = { MediaType.APPLICATION_JSON_VALUE },
        method = RequestMethod.POST)
    ResponseEntity<Void> createAcc(@ApiParam(value = "Account object that needs to be added to the store" ,required=true )  @Valid @RequestBody Account body
);


    @ApiOperation(value = "Deletes an account", nickname = "deleteAcc", notes = "", tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Account not found") })
    @RequestMapping(value = "/account",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteAcc(@ApiParam(value = "User id to delete",required=true) @PathVariable("userId") Long userId
,@ApiParam(value = "" ) @RequestHeader(value="api_key", required=false) String apiKey
);


    @ApiOperation(value = "get all accounts", nickname = "listAccounts", notes = "Calling this method will get you a list of all the accounts", response = Account.class, responseContainer = "List", tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Account.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 403, message = "forbidden") })
    @RequestMapping(value = "/account/listallaccounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Account>> listAccounts();


    @ApiOperation(value = "Get a specific account", nickname = "listSpecificAccount", notes = "Calling this method will get you a specific bank account from a user", response = Account.class, responseContainer = "List", tags={ "accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Account.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/account/{accountId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Account>> listSpecificAccount(@NotNull @ApiParam(value = "ID of user", required = true) @Valid @RequestParam(value = "userId", required = true) Long userId
,@ApiParam(value = "Id of account",required=true) @PathVariable("accountId") Long accountId
);

}
