package io.swagger.api;

import io.swagger.model.Users;
import io.swagger.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService service;

    private Users users;

    @BeforeEach
    public void setup(){
        Integer email = new Random().nextInt(63724);
        users = new Users(4L,"Tfrtgyest", "hjgjhgk", email.toString(), "45", true, Users.TypeofuserEnum.EMPLOYEE);
    }

    @Test
    public void callingAllUsersShouldReturnOK() throws Exception {
        this.mvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    public void getAllUsersShouldReturnJsonArray() throws Exception {
        when(service.getAllUsers()).thenReturn(Arrays.asList(users));
        this.mvc.perform(get("/users")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email").value(users.getEmail())
                );
    }
}
