package racingcar.exception.code;

import racingcar.exception.BaseErrorCode;

public enum InputErrorCode implements BaseErrorCode {
    READ_LINE_FAIL("입력 도중 오류가 발생했습니다."),
    NUMBER_FORMAT_ERROR("숫자 형식이 올바르지 않습니다.")
    ;

    private final String message;

    InputErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
