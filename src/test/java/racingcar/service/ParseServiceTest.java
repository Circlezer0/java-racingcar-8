package racingcar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParseServiceTest {


    @ParameterizedTest
    @MethodSource("provideCarNameInputs")
    @DisplayName("정상입력 문자열 파싱 테스트")
    void inputTest(String input, int expectedCount) {
        // given
        ParseService parseService = new ParseService();

        // when
        List<String> strings = parseService.parseCarNames(input);

        // then
        assertEquals(expectedCount, strings.size());
    }

    @Test
    @DisplayName("공백 포함인 경우 공백을 제거해서 반환 테스트")
    void trimWhitespaceTest() {
        // given
        String input = " car1 , car2 , car3 ";
        ParseService parseService = new ParseService();

        // when
        List<String> strings = parseService.parseCarNames(input);

        // then
        assertEquals(List.of("car1", "car2", "car3"), strings);
    }

    @Test
    @DisplayName("빈 문자열 입력 시 빈 리스트 반환 테스트")
    void emptyInputTest() {
        // given
        String input = "";
        ParseService parseService = new ParseService();

        // when
        List<String> strings = parseService.parseCarNames(input);

        // then
        assertEquals(0, strings.size());
    }

    @Test
    @DisplayName("null 입력 시 빈 리스트 반환 테스트")
    void nullInputTest() {
        // given
        String input = null;
        ParseService parseService = new ParseService();

        // when
        List<String> strings = parseService.parseCarNames(input);

        // then
        assertEquals(0, strings.size());
    }

    @Test
    @DisplayName("구분자가 동시에 여러개 오는 경우 정상 파싱")
    void multipleDelimitersTest() {
        // given
        String input = "car1,,car2,,,car3,";
        ParseService parseService = new ParseService();

        // when
        List<String> strings = parseService.parseCarNames(input);

        // then
        assertEquals(List.of("car1", "", "car2", "", "", "car3"), strings);
    }



    private static Stream<Arguments> provideCarNameInputs() {
        return Stream.of(
                Arguments.of("car1", 1),
                Arguments.of("car1,car2", 2),
                Arguments.of("car1,car2,car3", 3),
                Arguments.of("x,y,z,w", 4),
                Arguments.of("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t", 20),
                Arguments.of(" alpha , beta , gamma ", 3),
                Arguments.of("one,two,three,four,five,six", 6),
                Arguments.of("A1,B2,C3", 3)
        );
    }
}
