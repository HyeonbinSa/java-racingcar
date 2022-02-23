package racingcar.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.util.RandomNumberGenerator;

public class Cars {

    private final RandomNumberGenerator randomNumberGenerator;
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
        this.randomNumberGenerator = new RandomNumberGenerator();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void moveCar() {
        cars.forEach(car -> car.movePosition(randomNumberGenerator.generate()));
    }

    public List<String> getNamesOfWinners() {
        int maxPosition = this.findMaxPosition();
        return this.findCarNamesByPosition(maxPosition);
    }

    private int findMaxPosition() {
        Comparator<Car> comparatorByPosition = Comparator.comparingInt(Car::getPosition);
        return cars.stream()
            .max(comparatorByPosition).get().getPosition();
    }

    private List<String> findCarNamesByPosition(int position) {
        return cars.stream()
            .filter(car -> car.isSamePosition(position))
            .map(Car::getName)
            .collect(Collectors.toList());
    }
}
