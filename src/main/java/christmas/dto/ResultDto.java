package christmas.dto;

import java.util.List;

public record ResultDto(
        int date,
        List<OrderMenuDto> orderMenuDtos,
        int totalOrderPrice,
        PromotionDto promotionDto) {
}
