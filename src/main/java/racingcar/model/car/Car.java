package racingcar.model.car;

import racingcar.model.strategy.MoveStrategy;

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

    public void move(MoveStrategy strategy) {
        if (strategy.canMove()) {
            position++;
        }
    }
}
