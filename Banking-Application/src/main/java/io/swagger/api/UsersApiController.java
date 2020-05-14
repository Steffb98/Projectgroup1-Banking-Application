package io.swagger.api;

import io.swagger.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T12:47:35.450Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private UsersService usersService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UsersService usersService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.usersService = usersService;
    }

    public ResponseEntity createUser( @PathVariable Users user ) {
        //String accept = request.getHeader("Accept");
        //return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
        try {
            usersService.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "The email that needs to be deleted",required=true) @PathVariable("email") String email
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Users>> getAllUsers() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return ResponseEntity.status(200).body(usersService.getAllUsers());
//            try {
//                return new ResponseEntity<List<Users>>(objectMapper.readValue("[ {\n  \"firstname\" : \"firstname\",\n  \"password\" : \"password\",\n  \"id\" : 0,\n  \"email\" : \"inholland@student.nl\",\n  \"lastname\" : \"lastname\"\n}, {\n  \"firstname\" : \"firstname\",\n  \"password\" : \"password\",\n  \"id\" : 0,\n  \"email\" : \"inholland@student.nl\",\n  \"lastname\" : \"lastname\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<List<Users>>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
        }

        return ResponseEntity.status(200).body(usersService.getAllUsers());
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "User object that needs to be added to the store" ,required=true )  @Valid @RequestBody Users body
,@ApiParam(value = "email that need to be updated",required=true) @PathVariable("email") String email
,@ApiParam(value = "password that need to be updated",required=true) @PathVariable("password") String password
,@ApiParam(value = "Updated user object",required=true) @PathVariable("name") Users name
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Users> userid(@ApiParam(value = "ID of specific user",required=true) @PathVariable("id") Long id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Users>(objectMapper.readValue("{\n  \"firstname\" : \"firstname\",\n  \"password\" : \"password\",\n  \"id\" : 0,\n  \"email\" : \"inholland@student.nl\",\n  \"lastname\" : \"lastname\"\n}", Users.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Users>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Users>(HttpStatus.NOT_IMPLEMENTED);
    }

}
