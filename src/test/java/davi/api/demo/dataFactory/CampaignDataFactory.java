package davi.api.demo.dataFactory;

import davi.api.demo.application.dtos.ApplicationCreateBasicCampaignDataDto;
import davi.api.demo.application.dtos.ApplicationCreateItemDonationConfigDto;
import davi.api.demo.application.dtos.ApplicationCreateItemDto;
import davi.api.demo.application.dtos.ApplicationCreateMoneyDonationConfigDto;
import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;

public class CampaignDataFactory {
    private static final Faker faker = new Faker(new Locale("en"));

    public static ApplicationCreateBasicCampaignDataDto getBasicCampaignData() {
        return new ApplicationCreateBasicCampaignDataDto(
                faker.book().title(),
                faker.lorem().sentence(12)
        );
    }

    public static ApplicationCreateMoneyDonationConfigDto getMoneyDonationConfig() {
        return new ApplicationCreateMoneyDonationConfigDto(
                true,
                faker.number().numberBetween(50_000L, 1_000_000L),
                faker.lorem().sentence(10)
        );
    }

    public static ApplicationCreateItemDonationConfigDto getItemDonationConfig() {
        return new ApplicationCreateItemDonationConfigDto(
                true,
                List.of(
                        new ApplicationCreateItemDto(faker.commerce().productName(),
                                faker.number().numberBetween(100, 500),
                                faker.number().numberBetween(10, 200)),
                        new ApplicationCreateItemDto(faker.commerce().productName(),
                                faker.number().numberBetween(20, 200),
                                faker.number().numberBetween(5, 50)),
                        new ApplicationCreateItemDto(faker.commerce().productName(),
                                faker.number().numberBetween(50, 300),
                                faker.number().numberBetween(10, 100))
                )
        );
    }
}
