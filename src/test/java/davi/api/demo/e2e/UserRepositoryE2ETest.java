package davi.api.demo.e2e;

import davi.api.demo.domain.entities.User;
import davi.api.demo.e2e.config.E2ETest;
import davi.api.demo.infra.repositories.UserRepositoryHibernate;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Random;


public class UserRepositoryE2ETest extends E2ETest {

    @Test
    void shouldCreateAndRetrieveUserViaHttp() throws Exception {
        // Arrange
        var user1 = new User(null, new Random().nextInt() + "@mail.com", "Pass123$");
        var user2 = new User(null, new Random().nextInt() + "@mail.com", "Pass123$");
        List<User> createdUsers = List.of(user1, user2);
        //var jsonBody = getRequestBodyToCreateUser(email, password);
//        var createUserResult = mockMvc.perform(MockMvcRequestBuilders.post("/users?email=joao@email.com")
//                        .contentType("application/json")
//                        .content(jsonBody));
        userRepository.save(user1);
        userRepository.save(user2);
        // Perform HTTP GET to retrieve users


        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                //.andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);

        // Assert
        for (int i = 0; i < createdUsers.toArray().length; i++) {
            var email = rootNode.get(i).get("email").asText();
            var expectedEmail = createdUsers.get(i).email;
            var isPasswordIncluded = rootNode.get(i).has("password");
            Assertions.assertEquals(expectedEmail, email);
            Assertions.assertFalse(isPasswordIncluded);
        }
        //createUserResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String getRequestBodyToCreateUser(String email, String password) {
        return String.format("""
        {
            "email": "%s",
            "password": "%s"
        }
        """, email, password);
    }
}