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
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createUser(@ApiParam(value = "Created user object",required=true) @Valid @RequestBody Users body
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
            @ApiResponse(code = 200, message = "successful operation", response = Users.class),
        @ApiResponse(code = 400, message = "Invalid userID supplied"),
        @ApiResponse(code = 404, message = "user not found") })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.PUT)
    ResponseEntity<Void> toggleUserStatus(@ApiParam(value = "The userID that needs to be set active or inactive",required=true) @PathVariable("id") Long id
);


    @ApiOperation(value = "Updated user", nickname = "updateUser", notes = "This can only be done by the admin.", tags={ "users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Users.class),
        @ApiResponse(code = 400, message = "Invalid user supplied"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users",
            produces = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "User id" ,required=true )  @Valid @RequestParam(value = "id", required = true) Long id
,@ApiParam(value = "new email",required=true) @Valid @RequestParam(value = "email", required = true) String email
,@ApiParam(value = "new password",required=true) @Valid @RequestParam(value = "password", required = true) String password
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
    @ApiOperation(value = "get a user with specific name", nickname = "userName", notes = "find a user with a specific name", response = Users.class, tags={ "users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Users.class),
            @ApiResponse(code = 404, message = "name not found") })
    @RequestMapping(value = "/users/search",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Users>> getUserByName(@NotNull @ApiParam(value = "search user by name", required = true) @Valid @RequestParam(value = "name", required = true) String name
    );

}
