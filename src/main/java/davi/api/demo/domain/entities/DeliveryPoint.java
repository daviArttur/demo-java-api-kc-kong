package davi.api.demo.domain.entities;

import java.time.LocalTime;

public class DeliveryPoint extends Entity {
    private final String street;
    private final int number;
    private final String city;
    private final String state;
    private final String zipCode;
    private final LocalTime openingTime;
    private final LocalTime closingTime;

    public DeliveryPoint(Long id, String uuid, String street, int number, String city, String state, String zipCode,
                         LocalTime openingTime, LocalTime closingTime) {
        super(id, uuid);
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        validate();
    }

    public String getStreet() { return street; }
    public int getNumber() { return number; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public LocalTime getOpeningTime() { return openingTime; }
    public LocalTime getClosingTime() { return closingTime; }

    public void validate() throws IllegalArgumentException {
        if (street == null || street.isBlank() || street.length() > 100) {
            throw new IllegalArgumentException("Street must not be blank and must be at most 100 characters.");
        }

        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than 0.");
        }

        if (city == null || city.isBlank() || city.length() > 100) {
            throw new IllegalArgumentException("City must not be blank and must be at most 100 characters.");
        }

        if (state == null || state.isBlank() || state.length() != 2) {
            throw new IllegalArgumentException("State must be a 2-letter code.");
        }

        if (zipCode == null || !zipCode.matches("\\d{5}-\\d{3}")) {
            throw new IllegalArgumentException("Zip code must follow the format 12345-678.");
        }

        if (openingTime == null || closingTime == null) {
            throw new IllegalArgumentException("Opening and closing times must not be null.");
        }

        if (openingTime.isAfter(closingTime) || openingTime.equals(closingTime)) {
            throw new IllegalArgumentException("Opening time must be before closing time.");
        }
    }
}
