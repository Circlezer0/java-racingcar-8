package racingcar.service;

import java.util.Arrays;
import java.util.List;

public class ParseService {

    private static final String DELIMITER = ",";

    /**
     * 자동차 이름들을 파싱하여 리스트로 반환한다.
     * 입력이 null 이거나 빈 문자열인 경우 빈 리스트를 반환한다.
     * @param input 콤마(,)로 구분된 자동차 이름 문자열
     * @return 파싱된 자동차 이름 리스트
     */
    public List<String> parseCarNames(String input) {
        if (input == null || input.isBlank()) {
            return List.of();
        }

        String[] splitArray = input.split(DELIMITER);

        return Arrays.stream(splitArray)
                .map(String::trim)
                .toList();
    }
}
