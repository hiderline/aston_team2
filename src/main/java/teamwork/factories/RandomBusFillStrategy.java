package teamwork.factories;

import teamwork.models.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBusFillStrategy implements BusFillStrategy {
    private static final int BASE_NUMBER = 100;
    private static final String BASE_NAME = "RandomModel";
    private static final int BASE_MODEL = 10;
    private static final int BASE_ODOMETER = 1_000_000;

    @Override
    public List<Bus> fillBuses(int size) {
        Random random = new Random();
        List<Bus> buses = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Bus bus = new Bus.Builder()
                    .setNumber(BASE_NUMBER + i)
                    .setModel(BASE_NAME + random.nextInt(BASE_MODEL))
                    .setOdometer(random.nextInt(BASE_ODOMETER))
                    .build();
            buses.add(bus);
        }
        return buses;
    }
}