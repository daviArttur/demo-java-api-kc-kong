package davi.api.demo.e2e;

import davi.api.demo.e2e.config.E2ETest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class CreateCampaignTest extends E2ETest {

    @Test
    public void createCampaign() {
        try {
            // Arrange

            // Act
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/campaigns"))
                    //.andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            String jsonResponse = result.getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);


            // Assert
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
