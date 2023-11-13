package christmas.message;

public enum EventNoticeMessage {

    NOT_APPLICABLE_EVENT("[Notice] 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다."),
    IMPOSSIBLE_ORDER_BY_ONLY_DRINK("[Notice] 음료만 주문 시, 주문할 수 없습니다."),
    IMPOSSIBLE_ORDER_BY_MAX_ORDER_COUNT("[Notice] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.")
    ;

    private final String errorMessage;

    EventNoticeMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
