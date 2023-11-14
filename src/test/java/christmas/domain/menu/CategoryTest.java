package christmas.domain.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static christmas.domain.menu.Category.MAIN;

class CategoryTest {

    @DisplayName("음료 메뉴인지 판단")
    @EnumSource(value = Menu.class, names = {"ZERO_COKE", "RED_WINE", "CHAMPAGNE"})
    @ParameterizedTest
    void isDrinkMenu(Menu menu) {
        Assertions.assertThat(Category.DRINK.isContain(menu))
                .isTrue();
    }

    @DisplayName("디저트 메뉴인지 판단")
    @EnumSource(value = Menu.class, names = {"CHOCO_CAKE", "ICE_CREAM"})
    @ParameterizedTest
    void isDessertMenu(Menu menu) {
        Assertions.assertThat(Category.DESERT.isContain(menu))
                .isTrue();
    }

    @DisplayName("메인 메뉴인지 판단")
    @EnumSource(value = Menu.class, names = {"T_BORN_STAKE", "BARBECUE_RIP", "SEAFOOD_PASTA", "CHRISTMAS_PASTA"})
    @ParameterizedTest
    void isMainMenu(Menu menu) {
        Assertions.assertThat(MAIN.isContain(menu))
                .isTrue();
    }
}
