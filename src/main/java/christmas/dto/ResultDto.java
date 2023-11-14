package christmas.dto;

import java.util.List;

public record ResultDto(
        int date,
        List<OrderMenuDto> orderMenuDtos,
        int totalOrderPrice,
        boolean hasGift,
        List<EventDto> eventDtos,
        Integer totalBenefitAmount,
        int payment,
        String badgeName
) {
    public ResultDto(int date, List<OrderMenuDto> orderMenuDtos, int totalOrderPrice) {
        this(date, orderMenuDtos, totalOrderPrice, false,
                null, null, totalOrderPrice, "없음");
    }
}
