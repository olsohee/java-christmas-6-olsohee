package christmas.dto;

public class BenefitDto {

    private final String eventName;
    private final int benefitAmount;

    public BenefitDto(String eventName, int benefitAmount) {
        this.eventName = eventName;
        this.benefitAmount = benefitAmount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }
}
