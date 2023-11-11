package christmas.domain;

public enum Badge {
    STAR(5000),
    TREE(10000),
    SANTA(20000),
    NOT_APPLICABLE(-1)
    ;

    private final int price;

    Badge(int price) {
        this.price = price;
    }

    public static Badge getBadge(int totalBenefitAmount) {
        if(totalBenefitAmount >= 20000) {
            return SANTA;
        }

        if(totalBenefitAmount >= 10000) {
            return TREE;
        }

        if(totalBenefitAmount >= 5000) {
            return STAR;
        }

        return NOT_APPLICABLE;
    }
}
