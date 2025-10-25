package racingcar.model.car;

public class Car {
    private final CarName name;
    private int position;

    private Car(CarName name) {
        this.name = name;
        this.position = 0;
    }

    public static Car of(String name) {
        CarName carName = new CarName(name);
        return new Car(carName);
    }

    public CarName getCarName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        position++;
    }
}
