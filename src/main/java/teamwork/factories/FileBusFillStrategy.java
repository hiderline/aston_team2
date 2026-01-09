package teamwork.factories;

import teamwork.models.Bus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBusFillStrategy implements BusFillStrategy {
    public final static String FILENAME = "data.csv";

    @Override
    public List<Bus> fillBuses(int size) {
        List<Bus> buses = new ArrayList<>();
        String csvSplitBy = ",";

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILENAME)) {
            if (is == null) {
                throw new FileNotFoundException("Файл " + FILENAME + " не найден в ресурсах");
            }
            Scanner scanner = new Scanner(is, "UTF-8");
            int lineNumber = 0;

            while (buses.size() < size && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;

                if (lineNumber == 1)
                    continue;

                String[] data = line.split(csvSplitBy);

                int number = Integer.parseInt(data[0]);
                String model = data[1];
                int odometer = Integer.parseInt(data[2]);

                Bus bus = new Bus.Builder()
                        .setNumber(number)
                        .setModel(model)
                        .setOdometer(odometer)
                        .build();

                buses.add(bus);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл data.csv не найден");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(buses.size() + " добавлено из файла");
        return buses;
    }
}