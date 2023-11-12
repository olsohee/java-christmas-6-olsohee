package christmas.dto;

import christmas.domain.Benefit;

public class BadgeDto {

    private final String badgeName;

    public BadgeDto(Benefit benefit) {
        badgeName = benefit.getBadge().getBadgeName();
    }

    public String getBadgeName() {
        return badgeName;
    }
}
