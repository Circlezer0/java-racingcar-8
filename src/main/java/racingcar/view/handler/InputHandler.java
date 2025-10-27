package racingcar.view.handler;

import camp.nextstep.edu.missionutils.Console;
import racingcar.exception.RaceException;
import racingcar.exception.RaceErrorCode;

public class InputHandler {

    public String readLine() {
        try {
            return Console.readLine();
        } catch (Exception e) {
            throw new RaceException(RaceErrorCode.READ_LINE_FAIL);
        }
    }

    public int readInt() {
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new RaceException(RaceErrorCode.NUMBER_FORMAT_ERROR);
        } catch (Exception e) {
            throw new RaceException(RaceErrorCode.READ_LINE_FAIL);
        }
    }
}
