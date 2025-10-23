package racingcar.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @Test
    void 객체_생성_테스트() {
        // given
        String name = "car";

        // when
        Car car = CarFactory.of("car");

        // then
        assertEquals("car", car.getName());
        assertEquals(0, car.getPosition());
    }

    @Test
    void 이동_테스트() {
        // given
        Car car = CarFactory.of("car");

        // when
        car.move();

        // then
        assertEquals(1, car.getPosition());
    }

    @Test
    void 이름_길이_5자_초과_IllegalArgumentException() {
        // given
        String name = "llllll";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            CarFactory.of(name);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n"})
    void 이름_공백_IllegalArgumentException(String input) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            CarFactory.of(input);
        });
    }

    @Test
    void 이름_null_IllegalArgumentException() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            CarFactory.of(null);
        });
    }
}
