package christmas.service;

import christmas.domain.benefit.ApplicableEvents;
import christmas.domain.benefit.Badge;
import christmas.domain.event.Event;
import christmas.domain.menu.Menu;
import christmas.domain.order.Date;
import christmas.domain.order.OrderMenus;
import christmas.domain.order.TotalOrderPrice;
import christmas.dto.EventDto;
import christmas.dto.PromotionDto;

import java.util.List;

public class PromotionService {

    private final Date date;
    private final OrderMenus orderMenus;
    private final TotalOrderPrice totalOrderPrice;
    private ApplicableEvents applicableEvents;
    private Badge badge;

    public PromotionService(Date date, OrderMenus orderMenus, TotalOrderPrice totalOrderPrice) {
        this.date = date;
        this.orderMenus = orderMenus;
        this.totalOrderPrice = totalOrderPrice;
    }

    public void startPromotion() {
        applicableEvents = new ApplicableEvents(Event.getApplicableEvents(this.date, this.orderMenus, this.totalOrderPrice));
        badge = Badge.getBadge(applicableEvents.calculateTotalBenefitAmount(this.date, this.orderMenus));
    }

    public PromotionDto createPromotionDto() {
        List<EventDto> eventDtos = applicableEvents.getEvents().stream()
                .map(event -> new EventDto(event.getEventName(), event.getBenefitAmount(date, orderMenus)))
                .toList();
        int totalBenefitAmount = applicableEvents.calculateTotalBenefitAmount(this.date, this.orderMenus);
        int payment = totalOrderPrice.getTotalOrderPrice() - totalBenefitAmount;
        if (applicableEvents.containGiftEvent()) {
            payment += Menu.CHAMPAGNE.getPrice();
        }

        return new PromotionDto(applicableEvents.containGiftEvent(), eventDtos,
                totalBenefitAmount, payment, badge.getBadgeName());
    }
}
