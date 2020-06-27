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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost"})
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")

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

    public ResponseEntity createUser(@ApiParam(value = "Created user object",required=true) @Valid @RequestBody Users body
) {
        String accept = request.getHeader("Accept");
        try {
            Boolean checkIfEmailExist = usersService.checkIfEmailExist(body);
            if(checkIfEmailExist == true) {
                Users user = usersService.addUser(body);
                return ResponseEntity.status(HttpStatus.CREATED).body(user);
            }else{
                return ResponseEntity.status(401).build();
            }
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<Users>> getAllUsers() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return ResponseEntity.status(200).body(usersService.getAllUsers());
        }
        return new ResponseEntity<List<Users>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity toggleUserStatus(@ApiParam(value = "The userID that needs to be set active or inactive",required=true) @PathVariable("id") Long id
) {
        String accept = request.getHeader("Accept");
        try {
            if(usersService.getUserById(id) == null){
                return ResponseEntity.status(400).build();
            }else{
                usersService.switchUserStatus(id);
                return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserById(id));
            }
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity updateUser(@ApiParam(value = "User id" ,required=true )  @Valid @RequestParam(value = "id", required = true) Long id
,@ApiParam(value = "New email",required=true) @Valid @RequestParam(value = "email", required = true) String email
,@ApiParam(value = "New password",required=true) @Valid @RequestParam(value = "password", required = true) String password
) {
        String accept = request.getHeader("Accept");
            try {
                usersService.updateUser(id, email, password);
                return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUsers());
            } catch (IllegalArgumentException iae) {
                return ResponseEntity.status(400).build();
            }
    }

    public ResponseEntity<Users> userid(@ApiParam(value = "ID of specific user",required=true) @PathVariable("id") Long id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if(usersService.getUserById(id) == null){
                    return ResponseEntity.status(400).build();
                }else{
                    return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserById(id));
                }

            } catch (IllegalArgumentException iae) {
                log.error("The id is not valid", iae);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return new ResponseEntity<Users>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Users>> getUserByName(@NotNull @ApiParam(value = "Search by name", required = true)@Valid @RequestParam(value = "name", required = true) String name
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Users> user = usersService.getUserByName(name);
                if(user.isEmpty()){
                    return ResponseEntity.status(404).build();
                }else{
                    return ResponseEntity.status(200).body(user);
                }
            } catch (IllegalArgumentException iae) {
                log.error("The name is not valid", iae);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }


}
