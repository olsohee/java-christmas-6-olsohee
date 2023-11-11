package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("총혜택 금액에 따른 배지 계산")
    @Test
    void getBadge() {
        // given
        Badge startBadge = Badge.getBadge(5000);
        Badge treeBadge = Badge.getBadge(10000);
        Badge santaBadge = Badge.getBadge(20000);
        Badge notApplicable = Badge.getBadge(4000);

        // then
        Assertions.assertThat(startBadge).isEqualTo(Badge.STAR);
        Assertions.assertThat(treeBadge).isEqualTo(Badge.TREE);
        Assertions.assertThat(santaBadge).isEqualTo(Badge.SANTA);
        Assertions.assertThat(notApplicable).isEqualTo(Badge.NOT_APPLICABLE);
    }
}
