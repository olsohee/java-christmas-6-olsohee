package christmas.message;

public enum OutputMessage {

    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ORDER_MENU("<주문 메뉴>"),
    ORDER_MENU_INFO("%s %d개"),
    TOTAL_PRICE("<할인 전 총주문 금액>"),
    GIFT("<증정 메뉴>"),
    BENEFIT_INFO("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    PAYMENT_PRICE("<할인 후 예상 결제 금액>"),
    BADGE("<12월 이벤트 배지>"),
    PRICE("%d원"),
    BENEFIT("%s: -%d원"),
    NONE("없음")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
