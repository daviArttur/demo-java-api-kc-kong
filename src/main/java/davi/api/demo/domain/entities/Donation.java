package davi.api.demo.domain.entities;

public class Donation extends Entity {
    private Long id;
    private String uuid;

    public Donation(Long id, String uuid) {
        super(id, uuid);
    }
}
