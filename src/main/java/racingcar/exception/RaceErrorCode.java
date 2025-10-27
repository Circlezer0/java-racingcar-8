package racingcar.exception;

public enum RaceErrorCode {
    // CarName Error
    NAME_NOT_EMPTY("자동차 이름은 비어 있을 수 없습니다."),
    MAX_NAME_LENGTH_EXCEEDED("자동차 이름의 최대 길이를 초과했습니다."),
    INVALID_NAME_FORMAT("자동차 이름에 허용되지 않는 문자가 포함되어 있습니다."),
    CAR_NAME_DUPLICATED("자동차 이름은 중복될 수 없습니다."),

    // Cars Error
    CAR_NAME_LIST_NOT_EMPTY("자동차 이름 목록은 비어 있을 수 없습니다."),
    CAR_SIZE_EXCEEDED("자동차의 최대 개수를 초과했습니다."),

    // RaceRound Error
    NEGATIVE_ROUND("경기 라운드는 0 이상이어야 합니다."),
    MAX_ROUND_EXCEEDED("경기 라운드의 최대값을 초과했습니다."),

    // Input Error
    READ_LINE_FAIL("입력 도중 오류가 발생했습니다."),
    NUMBER_FORMAT_ERROR("숫자 형식이 올바르지 않습니다."),

    // RandomIntProvider Error
    MIN_BIGGER_THAN_MAX("최소값은 최대값보다 클 수 없습니다."),
    NEGATIVE_RANGE("최소값과 최대값은 음수일 수 없습니다.")
    ;

    private final String message;

    RaceErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
