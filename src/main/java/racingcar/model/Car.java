package racingcar.model;

public class Car {
    private final String name;
    private int position;

    private Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        position++;
    }

    private static final int MAX_NAME_LENGTH = 5;

    public static Car of(String name) {
        String normalizeName = normalizeName(name);
        validateName(normalizeName);

        return new Car(normalizeName);
    }

    private static String normalizeName(String name) {
        if (name == null) {
            return "";
        }
        return name.trim();
    }

    private static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 null이거나 공백일 수 없습니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5자를 초과할 수 없습니다.");
        }
        if (!name.matches("[A-Za-z0-9 ]+")) {
            throw new IllegalArgumentException("영문/숫자/공백만 허용됩니다.");
        }
    }
}
