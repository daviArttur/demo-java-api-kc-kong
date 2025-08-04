package davi.api.demo.api.controllers;

import davi.api.demo.api.requests.CreateCampaignRequest;
import davi.api.demo.api.responses.CreateCampaignResponse;
import davi.api.demo.api.responses.GetAllCampaignsResponse;
import davi.api.demo.application.dtos.ApplicationCampaignDto;
import davi.api.demo.application.dtos.ApplicationMoneyDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationItemDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationItemDto;
import davi.api.demo.application.inputs.CreateCampaignInput;
import davi.api.demo.application.dtos.ApplicationCreateBasicCampaignDataDto;
import davi.api.demo.application.dtos.ApplicationCreateMoneyDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationCreateItemDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationCreateItemDto;
import davi.api.demo.application.useCases.CreateCampaignUseCase;
import davi.api.demo.application.useCases.GetAllCampaignsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campaigns")
public class CampaignController extends BaseController {

    private final CreateCampaignUseCase createCampaignUseCase;
    private final GetAllCampaignsUseCase getAllCampaignsUseCase;

    @Autowired
    public CampaignController(CreateCampaignUseCase createCampaignUseCase, 
                           GetAllCampaignsUseCase getAllCampaignsUseCase) {
        this.createCampaignUseCase = createCampaignUseCase;
        this.getAllCampaignsUseCase = getAllCampaignsUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateCampaignResponse> createCampaign(@RequestBody CreateCampaignRequest request) {
        // Validate request manually since validation dependency might not be available
        validateCreateCampaignRequest(request);
        
        // Convert request to application DTOs
        ApplicationCreateBasicCampaignDataDto basicData = new ApplicationCreateBasicCampaignDataDto(
                request.title(),
                request.description()
        );

        ApplicationCreateMoneyDonationConfigDto moneyConfig = new ApplicationCreateMoneyDonationConfigDto(
                request.moneyDonationConfig().enabled(),
                request.moneyDonationConfig().goal(),
                request.moneyDonationConfig().useDescription()
        );

        ApplicationCreateItemDonationConfigDto itemConfig = new ApplicationCreateItemDonationConfigDto(
                request.itemDonationConfig().enabled(),
                request.itemDonationConfig().items().stream()
                        .map(item -> new ApplicationCreateItemDto(
                                item.name(),
                                item.quantityGoal(),
                                item.quantityReceived()
                        ))
                        .collect(Collectors.toList())
        );

        CreateCampaignInput input = new CreateCampaignInput(basicData, moneyConfig, itemConfig);
        createCampaignUseCase.perform(input);
        
        // For now, return a simple response. In a real implementation, 
        // you would get the created campaign data from the use case
        return ResponseEntity.ok(new CreateCampaignResponse(
                "generated-uuid", // This should come from the use case
                request.title(),
                request.description(),
                LocalDateTime.now()
        ));
    }

    @GetMapping
    public ResponseEntity<GetAllCampaignsResponse> getAllCampaigns() {
        List<ApplicationCampaignDto> campaigns = getAllCampaignsUseCase.perform();
        
        List<GetAllCampaignsResponse.CampaignResponse> campaignResponses = campaigns.stream()
                .map(this::convertToCampaignResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(new GetAllCampaignsResponse(campaignResponses));
    }
    
    private GetAllCampaignsResponse.CampaignResponse convertToCampaignResponse(ApplicationCampaignDto campaignDto) {
        GetAllCampaignsResponse.MoneyDonationConfigResponse moneyConfig = null;
        GetAllCampaignsResponse.ItemDonationConfigResponse itemConfig = null;
        
        if (campaignDto.moneyDonationConfig() != null) {
            moneyConfig = new GetAllCampaignsResponse.MoneyDonationConfigResponse(
                    campaignDto.moneyDonationConfig().enabled(),
                    campaignDto.moneyDonationConfig().goal(),
                    campaignDto.moneyDonationConfig().useDescription()
            );
        }
        
        if (campaignDto.itemDonationConfig() != null) {
            List<GetAllCampaignsResponse.ItemResponse> items = campaignDto.itemDonationConfig().items().stream()
                    .map(this::convertToItemResponse)
                    .collect(Collectors.toList());
            
            itemConfig = new GetAllCampaignsResponse.ItemDonationConfigResponse(
                    campaignDto.itemDonationConfig().enabled(),
                    items
            );
        }
        
        return new GetAllCampaignsResponse.CampaignResponse(
                campaignDto.id(),
                campaignDto.uuid(),
                campaignDto.title(),
                campaignDto.description(),
                LocalDateTime.now(), // These should come from the domain entities
                LocalDateTime.now(),
                moneyConfig,
                itemConfig
        );
    }
    
    private GetAllCampaignsResponse.ItemResponse convertToItemResponse(ApplicationItemDto itemDto) {
        return new GetAllCampaignsResponse.ItemResponse(
                1L, // This should come from the domain entity
                "item-uuid", // This should come from the domain entity
                itemDto.name(),
                itemDto.quantityGoal(),
                itemDto.quantityReceived(),
                LocalDateTime.now(), // These should come from the domain entities
                LocalDateTime.now()
        );
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
        
        if (request.moneyDonationConfig() == null) {
            throw new IllegalArgumentException("Money donation config is required");
        }
        if (request.itemDonationConfig() == null) {
            throw new IllegalArgumentException("Item donation config is required");
        }
        
        // Validate money donation config
        validateMoneyDonationConfig(request.moneyDonationConfig());
        
        // Validate item donation config
        validateItemDonationConfig(request.itemDonationConfig());
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
