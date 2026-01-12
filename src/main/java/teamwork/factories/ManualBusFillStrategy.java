package teamwork.factories;

import teamwork.models.Bus;
import teamwork.validators.BusExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualBusFillStrategy extends BaseBusFillStrategy {

    @Override
    public List<Bus> fillBuses(int size) {
        Scanner scanner = new Scanner(System.in);
        List<Bus> buses = new ArrayList<>();

        BusExceptionHandler.printInfo("=== Ручной ввод автобусов ===");

        for (int i = 0; i < size; i++) {
            BusExceptionHandler.printInfo("Автобус #" + (i + 1) + " из " + size);
            String line = scanner.nextLine().trim();

            if (line.isEmpty())
                break;

            Bus bus = createBusFromLine(line);
            if (bus != null) {
                buses.add(bus);
            }

        }
        return buses;
    }
}
