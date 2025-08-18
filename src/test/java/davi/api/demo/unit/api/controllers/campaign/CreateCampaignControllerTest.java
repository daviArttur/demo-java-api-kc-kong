package davi.api.demo.unit.api.controllers.campaign;

import davi.api.demo.api.controllers.campaign.CreateCampaignController;
import davi.api.demo.application.useCases.CreateCampaignUseCase;
import davi.api.demo.unit.config.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;

public class CreateCampaignControllerTest extends UnitTest {

    private CreateCampaignController createCampaignController;
    private CreateCampaignUseCase createCampaignUseCase;

    @BeforeEach
    public void beforeEach() {
        createCampaignUseCase = Mockito.mock(CreateCampaignUseCase.class);
        createCampaignController = new CreateCampaignController(createCampaignUseCase);
    }

    @Test
    public void test() {
        // Arrange
        //Mockito.when(createCampaignUseCase.perform(Mockito.any())).thenReturn(null);

        // Act
        var result = createCampaignController.handle(null);

        // Assert
        Assertions.assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
    }
}
