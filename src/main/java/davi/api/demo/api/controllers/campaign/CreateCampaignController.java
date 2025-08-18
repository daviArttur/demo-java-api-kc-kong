package davi.api.demo.api.controllers.campaign;

import davi.api.demo.api.requests.CreateCampaignRequest;
import davi.api.demo.api.responses.CreateCampaignResponse;
import davi.api.demo.api.responses.GetAllCampaignsResponse;
import davi.api.demo.application.dtos.*;
import davi.api.demo.application.inputs.CreateCampaignInput;
import davi.api.demo.application.useCases.CreateCampaignUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campaigns")
public class CreateCampaignController {
    private final CreateCampaignUseCase createCampaignUseCase;

    @Autowired
    public CreateCampaignController(CreateCampaignUseCase createCampaignUseCase) {
        this.createCampaignUseCase = createCampaignUseCase;
    }

    @PostMapping
    public ResponseEntity handle(@RequestBody CreateCampaignRequest request) {
        validateCreateCampaignRequest(request);

        ApplicationCreateBasicCampaignDataDto basicData = new ApplicationCreateBasicCampaignDataDto(
                request.title(),
                request.description()
        );

//        ApplicationCreateMoneyDonationConfigDto moneyConfig = new ApplicationCreateMoneyDonationConfigDto(
//                request.moneyDonationConfig().enabled(),
//                request.moneyDonationConfig().goal(),
//                request.moneyDonationConfig().useDescription()
//        );
//
//        ApplicationCreateItemDonationConfigDto itemConfig = new ApplicationCreateItemDonationConfigDto(
//                request.itemDonationConfig().enabled(),
//                request.itemDonationConfig().items().stream()
//                        .map(item -> new ApplicationCreateItemDto(
//                                item.name(),
//                                item.quantityGoal(),
//                                item.quantityReceived()
//                        ))
//                        .collect(Collectors.toList())
//        );

        CreateCampaignInput input = new CreateCampaignInput(basicData, null, null);
        createCampaignUseCase.perform(input);
        return ResponseEntity.ok().build();
    }

    private void validateCreateCampaignRequest(CreateCampaignRequest request) {
        if (request.title() == null || request.title().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (request.title().length() < 4 || request.title().length() > 40) {
            throw new IllegalArgumentException("Title must be between 4 and 40 characters");
        }

        if (request.description() == null || request.description().trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (request.description().length() < 4 || request.description().length() > 999) {
            throw new IllegalArgumentException("Description must be between 4 and 999 characters");
        }

        if (request.moneyDonationConfig() != null) {
            validateMoneyDonationConfig(request.moneyDonationConfig());
        }
        if (request.itemDonationConfig() != null) {
            validateItemDonationConfig(request.itemDonationConfig());
        }

        // Validate money donation config

        // Validate item donation config
    }

    private void validateMoneyDonationConfig(CreateCampaignRequest.MoneyDonationConfigRequest config) {
        if (config.enabled() == null) {
            throw new IllegalArgumentException("Money donation enabled status is required");
        }
        if (config.goal() == null || config.goal() < 1) {
            throw new IllegalArgumentException("Money donation goal must be at least 1");
        }
        if (config.goal() > 999999999) {
            throw new IllegalArgumentException("Money donation goal cannot exceed 999,999,999");
        }
        if (config.useDescription() != null && config.useDescription().length() > 1000) {
            throw new IllegalArgumentException("Use description cannot exceed 1000 characters");
        }
    }

    private void validateItemDonationConfig(CreateCampaignRequest.ItemDonationConfigRequest config) {
        if (config.enabled() == null) {
            throw new IllegalArgumentException("Item donation enabled status is required");
        }
        if (config.items() == null || config.items().isEmpty()) {
            throw new IllegalArgumentException("At least one item must be configured");
        }
        if (config.items().size() > 50) {
            throw new IllegalArgumentException("Cannot configure more than 50 items");
        }

        for (CreateCampaignRequest.ItemRequest item : config.items()) {
            validateItemRequest(item);
        }
    }

    private void validateItemRequest(CreateCampaignRequest.ItemRequest item) {
        if (item.name() == null || item.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Item name is required");
        }
        if (item.name().length() < 2 || item.name().length() > 100) {
            throw new IllegalArgumentException("Item name must be between 2 and 100 characters");
        }
        if (item.quantityGoal() == null || item.quantityGoal() < 1) {
            throw new IllegalArgumentException("Item quantity goal must be at least 1");
        }
        if (item.quantityGoal() > 999999) {
            throw new IllegalArgumentException("Item quantity goal cannot exceed 999,999");
        }
        if (item.quantityReceived() == null || item.quantityReceived() < 0) {
            throw new IllegalArgumentException("Item quantity received cannot be negative");
        }
        if (item.quantityReceived() > 999999) {
            throw new IllegalArgumentException("Item quantity received cannot exceed 999,999");
        }
    }
}
