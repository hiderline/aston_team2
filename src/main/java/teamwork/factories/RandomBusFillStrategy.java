package teamwork.factories;

import teamwork.models.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.StreamSupport.stream;

public class RandomBusFillStrategy implements BusFillStrategy {
    private static final int BASE_NUMBER = 100;
    private static final int RANDOM_NUMBER_LIMIT = 899;
    private static final String BASE_NAME = "RandomModel";
    private static final int BASE_MODEL = 10;
    private static final int BASE_ODOMETER = 1_000_000;

    @Override
    public List<Bus> fillBuses(int size) {
        Random random = new Random();
        List<Bus> buses;

        buses = Stream.generate(() -> new Bus.Builder()
                        .setNumber(BASE_NUMBER + random.nextInt(RANDOM_NUMBER_LIMIT))
                        .setModel(BASE_NAME + random.nextInt(BASE_MODEL))
                        .setOdometer(random.nextInt(BASE_ODOMETER))
                        .build())
                .limit(size)
                .toList();

        return buses;
    }
}