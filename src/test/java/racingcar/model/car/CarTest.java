package racingcar.model.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @Test
    @DisplayName("객체 생성 테스트")
    void createCarTest() {
        // given
        String name = "car";

        // when
        Car car = Car.of("car");

        // then
        assertEquals("car", car.getName());
        assertEquals(0, car.getPosition());
    }

    @Test
    @DisplayName("이동 테스트")
    void moveCarTest() {
        // given
        Car car = Car.of("car");

        // when
        car.move();

        // then
        assertEquals(1, car.getPosition());
    }

    @ParameterizedTest
    @DisplayName("유효한 이름 생성 테스트")
    @ValueSource(strings = {"a", "ab", "a c", "AbCd", "a1cd3"})
    void validNameTest(String input) {
        // when
        Car car = Car.of(input);

        // then
        assertEquals(input, car.getName());
    }

    @Test
    @DisplayName("이름 길이 5자 초과 IllegalArgumentException")
    void nameMaxLengthErrorTest() {
        // given
        String name = "llllll";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            Car.of(name);
        });
    }

    @ParameterizedTest
    @DisplayName("이름이 공백일 때 IllegalArgumentException")
    @ValueSource(strings = {"", "  ", "\t", "\n"})
    void emptyNameErrorTest(String input) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            Car.of(input);
        });
    }

    @ParameterizedTest
    @DisplayName("이름에 특수문자 포함 IllegalArgumentException")
    @ValueSource(strings = {"car@", "c\nr", "c\tar", "ca;r"})
    void specialCharacterNameErrorTest(String input) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            Car.of(input);
        });
    }

    @Test
    @DisplayName("이름이 null일 때 IllegalArgumentException")
    void nullNameErrorTest() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            Car.of(null);
        });
    }
}
