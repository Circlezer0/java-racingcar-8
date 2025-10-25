package racingcar.exception.code;

import racingcar.exception.BaseErrorCode;

public enum CarErrorCode implements BaseErrorCode {
    NAME_NOT_EMPTY("자동차 이름은 비어 있을 수 없습니다."),
    MAX_NAME_LENGTH_EXCEEDED("자동차 이름의 최대 길이를 초과했습니다."),
    INVALID_NAME_FORMAT("자동차 이름에 허용되지 않는 문자가 포함되어 있습니다."),
    CAR_NAME_DUPLICATED("자동차 이름은 중복될 수 없습니다."),

    CAR_NAME_LIST_NOT_EMPTY("자동차 이름 목록은 비어 있을 수 없습니다."),
    CAR_SIZE_EXCEEDED("자동차의 최대 개수를 초과했습니다.")
    ;

    private final String message;

    CarErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
