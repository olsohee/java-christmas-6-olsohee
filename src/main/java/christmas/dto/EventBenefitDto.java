package christmas.dto;

import java.util.List;

public class EventBenefitDto {

    private final int totalPrice;
    private final boolean hasGift;
    private final List<BenefitDto> benefitDtos;
    private final String badge;

    public EventBenefitDto(int totalPrice, boolean hasGift, List<BenefitDto> benefitDtos, String badge) {
        this.totalPrice = totalPrice;
        this.hasGift = hasGift;
        this.benefitDtos = benefitDtos;
        this.badge = badge;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean hasGift() {
        return hasGift;
    }

    public List<BenefitDto> getBenefitDtos() {
        return benefitDtos;
    }

    public String getBadge() {
        return badge;
    }
}
