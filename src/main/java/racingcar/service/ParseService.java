package racingcar.service;

import java.util.Arrays;
import java.util.List;

public class ParseService {

    private static final String DELIMITER = ",";

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
