package christmas.domain.benefit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @DisplayName("총혜택 금액에 따른 배지 계산")
    @ValueSource(ints = {0, 4999})
    @ParameterizedTest
    void getBadgeNotApplicable(int totalBenefitAmount) {
        Assertions.assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.NOT_APPLICABLE);
    }

    @ValueSource(ints = {5000, 5001, 9999})
    @ParameterizedTest
    void getBadgeStar(int totalBenefitAmount) {
        Assertions.assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.STAR);
    }

    @ValueSource(ints = {10000, 10001, 19999})
    @ParameterizedTest
    void getBadgeTree(int totalBenefitAmount) {
        Assertions.assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.TREE);
    }

    @ValueSource(ints = {20000, 20001})
    @ParameterizedTest
    void getBadgeSanta(int totalBenefitAmount) {
        Assertions.assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.SANTA);
    }
}
