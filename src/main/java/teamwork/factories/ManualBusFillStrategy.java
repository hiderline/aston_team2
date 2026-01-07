package teamwork.factories;

import teamwork.models.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualBusFillStrategy implements BusFillStrategy {

    @Override
    public List<Bus> fillBuses(int size) {
        Scanner scanner = new Scanner(System.in);
        List<Bus> buses = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            System.out.println("Bus #" + (i + 1));

            System.out.print("number : ");
            int number = scanner.nextInt();

            System.out.print("model: ");
            String model = scanner.next();

            System.out.print("adometr: ");
            int odometer = scanner.nextInt();

            Bus bus = new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setOdometer(odometer)
                    .build();

            buses.add(bus);
        }
        return buses;
    }
}
