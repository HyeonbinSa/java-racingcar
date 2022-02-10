package racingcar;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Input input;
    private Output output;

    public Game() {
        input = new Input();
        output = new Output();
    }

    public void start() {
        String[] names = inputCarNames();
        int coin = inputCoin();
        List<Car> cars = generateCar(names);
        race(coin, cars);
        List<String> winners = getWinner(cars);
        output.printWinner(winners);
    }

    private void race(int coin, List<Car> cars) {
        output.printResultMessage();
        for (int index = 0; index < coin; index++) {
            moveCar(cars);
            output.printPosition(cars);
        }
    }

    private void moveCar(List<Car> cars) {
        for (Car car : cars) {
            car.movePosition();
        }
    }

    public List<Car> generateCar(String[] names) {
        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name));
        }
        return cars;
    }

    public List<String> getWinner(List<Car> cars) {
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();
        //분리
        for (Car car : cars) {
            if (maxPosition < car.getPosition()) {
                maxPosition = car.getPosition();
            }
        }
        for (Car car : cars) {
            if (car.isSamePosition(maxPosition)) {
                winners.add(car.getName());
            }
        }
        return winners;
    }

    private String[] inputCarNames() {
        output.inputCarNameMessage();
        return input.getNames();
    }

    private int inputCoin() {
        output.inputCoinMessage();
        return input.getCoin();
    }
}
