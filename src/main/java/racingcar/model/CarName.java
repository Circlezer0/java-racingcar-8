package racingcar.model;

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
            throw new IllegalArgumentException("이름은 null이거나 공백일 수 없습니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5자를 초과할 수 없습니다.");
        }
        if (!name.matches(NAME_PATTERN)) {
            throw new IllegalArgumentException("영문/숫자/공백만 허용됩니다.");
        }
    }
}
