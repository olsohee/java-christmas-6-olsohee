package christmas.dto;

import java.util.List;

public record PromotionDto(
        boolean hasGift,
        List<EventDto> eventDtos,
        Integer totalBenefitAmount,
        int payment,
        String badgeName
) {
}
