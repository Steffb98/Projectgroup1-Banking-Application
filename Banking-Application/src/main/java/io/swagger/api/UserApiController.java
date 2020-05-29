package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Users;
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
@CrossOrigin(origins = {"http://localhost"})
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-21T13:09:59.263Z[GMT]")

@Controller
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private UsersService usersService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request,UsersService usersService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.usersService = usersService;
    }

    public ResponseEntity loginUser(@NotNull @ApiParam(value = "The email for login", required = true) @Valid @RequestParam(value = "email", required = true) String email
,@NotNull @ApiParam(value = "The password for login in clear text", required = true) @Valid @RequestParam(value = "password", required = true) String password
) {
        String accept = request.getHeader("Accept");
        System.out.println(password);
        try {
            Users user= usersService.login(email, password);
            if(user == null){
                return ResponseEntity.status(400).build();
            }
            else{
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
            }
        }catch (IllegalArgumentException iae) {
            log.error("Incorrect email or password", iae);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> logoutUser() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
