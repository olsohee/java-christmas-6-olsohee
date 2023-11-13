package christmas.dto;

import java.util.List;

public record ResultDto(
        int date,
        List<OrderMenuDto> orderMenuDtos,
        int totalOrderPrice,
        boolean hasGift,
        List<BenefitDto> benefitDtos,
        int totalBenefitAmount,
        int payment,
        String badgeName
) {

    public ResultDto(int date, List<OrderMenuDto> orderMenuDtos, int totalOrderPrice) {
        this(date, orderMenuDtos, totalOrderPrice, false, null, 0, totalOrderPrice, "없음");
    }
}
