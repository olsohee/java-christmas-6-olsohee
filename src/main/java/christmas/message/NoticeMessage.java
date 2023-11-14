package christmas.message;

public enum NoticeMessage {

    NOT_APPLICABLE_EVENT("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다."),
    IMPOSSIBLE_ORDER_BY_ONLY_DRINK("음료만 주문 시, 주문할 수 없습니다."),
    IMPOSSIBLE_ORDER_BY_MAX_ORDER_COUNT("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");

    private static final String NOTICE_HEADER = "[NOTICE]";
    private final String noticeMessage;

    NoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public String getNoticeMessage() {
        return String.format("%s %s", NOTICE_HEADER, noticeMessage);
    }
}
