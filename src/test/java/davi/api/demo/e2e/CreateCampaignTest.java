package davi.api.demo.e2e;

import davi.api.demo.e2e.config.E2ETest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class CreateCampaignTest extends E2ETest {

    @Test
    public void createCampaign() throws Exception {
        // Arrange

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/campaigns"))
                .andReturn();
        var response = result.getResponse();
        var status = response.getStatus();
        var responseBody = response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        //JsonNode rootNode = mapper.readTree(jsonResponse);

        // Assert
        Assertions.assertEquals(400, status);
    }

    @Test
    public void createCampaign2() throws Exception {
        // Arrange
        // check uuid generation
        String requestBody = """
        {
          "title": "Help Local School",
          "description": "Fundraising campaign to support the local school with money and essential supplies.",
          "moneyDonationConfig": {
            "enabled": true,
            "goal": 50000,
            "useDescription": "Funds will be used for school renovations and supplies"
          },
          "itemDonationConfig": {
            "enabled": true,
            "items": [
              {
                "name": "Notebooks",
                "quantityGoal": 200,
                "quantityReceived": 10
              },
              {
                "name": "Pencils",
                "quantityGoal": 500,
                "quantityReceived": 50
              }
            ]
          }
        }
        """;

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/campaigns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        var response = result.getResponse();
        var status = response.getStatus();
        var responseBody = response.getContentAsString();

        // Assert
        Assertions.assertEquals(201, status); // expect CREATED if success
    }
}
