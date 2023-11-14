package christmas.domain.benefit;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NOT_APPLICABLE("없음", -1);

    private final String badgeName;
    private final int price;

    Badge(String badgeName, int price) {
        this.badgeName = badgeName;
        this.price = price;
    }

    public static Badge getBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= 20000) {
            return SANTA;
        }

        if (totalBenefitAmount >= 10000) {
            return TREE;
        }

        if (totalBenefitAmount >= 5000) {
            return STAR;
        }

        return NOT_APPLICABLE;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
