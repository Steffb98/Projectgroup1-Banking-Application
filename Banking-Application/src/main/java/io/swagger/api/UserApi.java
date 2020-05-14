/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T12:47:35.450Z[GMT]")
@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "Logs user into the system", nickname = "loginUser", notes = "", response = String.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = String.class),
        @ApiResponse(code = 400, message = "Invalid username/password supplied") })
    @RequestMapping(value = "/user/login",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> loginUser(@NotNull @ApiParam(value = "The email for login", required = true) @Valid @RequestParam(value = "email", required = true) String email
,@NotNull @ApiParam(value = "The password for login in clear text", required = true) @Valid @RequestParam(value = "password", required = true) String password
);


    @ApiOperation(value = "Logs out current logged in user session", nickname = "logoutUser", notes = "", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/user/logout",
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutUser();

}
