package racingcar.view.handler;

import java.util.List;
import racingcar.exception.RaceException;
import racingcar.exception.RaceErrorCode;

public class FakeInputHandler extends InputHandler {

    private final List<String> inputs;
    private int currentIndex = 0;

    public FakeInputHandler(String... inputs) {
        this.inputs = List.of(inputs);
    }

    @Override
    public String readLine() {
        if (currentIndex >= inputs.size()) {
            throw new RaceException(RaceErrorCode.READ_LINE_FAIL);
        }
        return inputs.get(currentIndex++);
    }

    @Override
    public int readInt() {
        String input = readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RaceException(RaceErrorCode.NUMBER_FORMAT_ERROR);
        }
    }
}
