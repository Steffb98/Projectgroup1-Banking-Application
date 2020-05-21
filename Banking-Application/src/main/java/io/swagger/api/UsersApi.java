/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Users;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")
@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "Create user", nickname = "createUser", notes = "This can only be done by admin form the bank.", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/users",
        method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createUser(@ApiParam(value = "Created user object",required=true) @PathVariable("body") Users body
);


    @ApiOperation(value = "get all the users", nickname = "getAllUsers", notes = "Calling this method wil get you a list of all the users", response = Users.class, responseContainer = "List", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Users.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Users>> getAllUsers();


    @ApiOperation(value = "Changes user to active/inactive", nickname = "toggleUserStatus", notes = "This can only be done by the admin.", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid userID supplied"),
        @ApiResponse(code = 404, message = "user not found") })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.PUT)
    ResponseEntity<Void> toggleUserStatus(@ApiParam(value = "The userID that needs to be set active or inactive",required=true) @PathVariable("id") Long id
);


    @ApiOperation(value = "Updated user", nickname = "updateUser", notes = "This can only be done by the admin.", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid user supplied"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users",
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "User object that needs to be added to the store" ,required=true )  @Valid @RequestBody Users body
,@ApiParam(value = "email that need to be updated",required=true) @PathVariable("email") String email
,@ApiParam(value = "password that need to be updated",required=true) @PathVariable("password") String password
,@ApiParam(value = "Updated user object",required=true) @PathVariable("name") Users name
);


    @ApiOperation(value = "get a user with specific id", nickname = "userid", notes = "find a user with a specific id", response = Users.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Users.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "id not found") })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Users> userid(@ApiParam(value = "ID of specific user",required=true) @PathVariable("id") Long id
);

}
