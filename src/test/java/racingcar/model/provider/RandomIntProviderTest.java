package racingcar.model.provider;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RandomIntProviderTest {

    @ParameterizedTest(name = "min={0}, max={1}")
    @DisplayName("객체 정상 생성 테스트")
    @CsvSource({
            "0, 10",
            "5, 15"
    })
    void createCarsTest(int min, int max) {
        // when & then
        assertDoesNotThrow(() -> new RandomIntProvider(min, max));
    }

    @Test
    @DisplayName("랜덤 정수 제공 테스트")
    void provideRandomIntTest() {
        // given
        int min = 0, max = 10;
        int repeatCount = 100;
        RandomIntProvider intProvider = new RandomIntProvider(min, max);

        // when
        List<Integer> samples = new ArrayList<>();
        for (int i = 0; i < repeatCount; i++) {
            samples.add(intProvider.nextInt());
        }

        // then
        samples.forEach(v -> assertThat(v).isBetween(min, max));
    }

    @ParameterizedTest(name = "min={0}, max={1}")
    @DisplayName("최소값과 최대값이 같을 때 항상 그 값만 제공 테스트")
    @CsvSource({
            "0, 0",
            "1, 1",
            "5, 5",
            "10, 10"
    })
    void sameRangeSameValueTest(int min, int max) {
        // given
        RandomIntProvider intProvider = new RandomIntProvider(min, max);
        int repeatCount = 10;

        // when
        List<Integer> samples = new ArrayList<>();
        for (int i = 0; i < repeatCount; i++) {
            samples.add(intProvider.nextInt());
        }

        // then
        samples.forEach(v -> assertEquals(min, v));
    }

    @ParameterizedTest(name = "min={0}, max={1}")
    @DisplayName("최소값이 최대값보다 클 때 IllegalArgumentException")
    @CsvSource({
            "10, 5",
            "5, 4"
    })
    void biggerMinThanMaxErrorTest(int min, int max) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new RandomIntProvider(min, max));
    }

    @ParameterizedTest(name = "min={0}, max={1}")
    @DisplayName("음수 최소값 또는 최대값 설정 시 IllegalArgumentException")
    @CsvSource({
            "-1, 5",
            "0, -10",
            "-5, -1"
    })
    void negativeRangeErrorTest(int min, int max) {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new RandomIntProvider(min, max));
    }
}
