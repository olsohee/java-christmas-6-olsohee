package christmas.dto;

public class NonPromotionDto {

    private final int totalPrice;

    public NonPromotionDto(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
