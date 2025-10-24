package racingcar.view.handler;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {

    public String readLine() {
        try {
            return Console.readLine();
        } catch (Exception e) {
            throw new IllegalArgumentException("입력 도중 오류가 발생했습니다.");
        }
    }

    public int readInt() {
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수를 입력해야 합니다.");
        } catch (Exception e) {
            throw new IllegalArgumentException("입력 도중 오류가 발생했습니다.");
        }
    }
}
