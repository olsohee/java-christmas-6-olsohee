package christmas.domain.order;

public class TotalOrderPrice {

    private final int totalOrderPrice;

    public TotalOrderPrice(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public boolean isApplicableEvent() {
        return totalOrderPrice >= 10000;
    }

    public boolean isApplicableGiftEvent() {
        return totalOrderPrice >= 120000;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }
}
