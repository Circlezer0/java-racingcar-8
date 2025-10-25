package racingcar.view.handler;

import java.util.ArrayList;
import java.util.List;

public class SpyOutputHandler extends OutputHandler {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void printLine(String message) {
        lines.add(message);
    }

    public List<String> getLines() {
        return lines;
    }
}
