package christmas.dto.benefitDto;

import christmas.domain.Benefit;
import christmas.domain.Date;
import christmas.domain.Order;

import java.util.List;

public class BenefitsDto {

    private final boolean gift;
    private final List<BenefitDto> benefitDtos;
    private final int totalBenefitAmount;

    public BenefitsDto(Benefit benefit, Order order, Date date) {
        gift = benefit.hasGift();
        benefitDtos = benefit.getEvents().stream()
                .map(event -> new BenefitDto(event.getEventName(), event.getBenefitAmount(order, date)))
                .toList();
        totalBenefitAmount = benefit.getTotalBenefitAmount();
    }

    public boolean getGift() {
        return gift;
    }

    public List<BenefitDto> getBenefitDtos() {
        return benefitDtos;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
