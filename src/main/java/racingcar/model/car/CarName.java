package racingcar.model.car;

import racingcar.exception.RaceException;
import racingcar.exception.code.CarErrorCode;

public record CarName(String name) {

    private static final int MAX_NAME_LENGTH = 5;
    private static final String NAME_PATTERN = "[A-Za-z0-9 ]+";

    public CarName(String name) {
        String normalizedName = normalizeName(name);
        validateName(normalizedName);
        this.name = normalizedName;
    }

    private static String normalizeName(String name) {
        if (name == null) {
            return "";
        }
        return name.trim();
    }

    private static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new RaceException(CarErrorCode.NAME_NOT_EMPTY);
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new RaceException(CarErrorCode.MAX_NAME_LENGTH_EXCEEDED);
        }
        if (!name.matches(NAME_PATTERN)) {
            throw new RaceException(CarErrorCode.INVALID_NAME_FORMAT);
        }
    }
}
