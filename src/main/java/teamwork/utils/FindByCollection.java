package teamwork.utils;

import teamwork.factories.BusCreator;
import teamwork.models.Bus;

import java.util.List;
import java.util.Scanner;

public class FindByCollection {

    public static void findByValue(List<Bus> buses, int numThread) {
        Scanner scanner = new Scanner(System.in);
        int[] results = new int[numThread];
        Thread[] threads = new Thread[numThread];
        int size = buses.size();
        int colElementsByThread = size / numThread;
        int delta = size - (colElementsByThread * numThread);
        Bus bus = null;

        while (bus == null) {
            System.out.println("Введите значения элемента (номер, модель, пробег):");
            String line = scanner.nextLine();
            bus = BusCreator.createBusFromLine(line);
        }

        Bus currentBus = bus;
        for (int i = 0; i < numThread; i++) {
            int startInd = i * colElementsByThread;
            int endInd = startInd + colElementsByThread + (i == (numThread - 1) ? delta : 0);
            int indexResult = i;

            threads[i] = new Thread(() -> {
                for (int j = startInd; j < endInd; j++) {
                    if (buses.get(j).equals(currentBus)) {
                        results[indexResult]++;
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThread; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int count = 0;
        for (int i = 0; i < numThread; i++) {
            count += results[i];
        }

        System.out.println("Количество найденых элементов: " + count);
    }
}
