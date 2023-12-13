package christmas.domain;

public enum Badge {

    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NONE("없음", -1)
    ;

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Badge findBadgeByBenefitAmount(int benefitCount) {
        if (benefitCount >= 20000) return SANTA;
        if (benefitCount >= 10000) return TREE;
        if (benefitCount >= 5000) return SANTA;
        return NONE;
    }

    public int getPrice() {
        return price;
    }
}
