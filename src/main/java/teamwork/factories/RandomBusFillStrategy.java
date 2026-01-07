package teamwork.factories;

import teamwork.models.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBusFillStrategy implements BusFillStrategy {

    @Override
    public List<Bus> fillBuses(int size) {
        Random random = new Random();
        List<Bus> buses = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Bus bus = new Bus.Builder()
                    .setNumber(100 + i)
                    .setModel("Model_" + random.nextInt(10))
                    .setOdometer(random.nextInt(500_000))
                    .build();
            buses.add(bus);
        }
        return buses;
    }
}