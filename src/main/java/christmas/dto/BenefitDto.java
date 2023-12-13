package christmas.dto;

public class BenefitDto {

    private final String name;
    private final int amount;

    public BenefitDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
