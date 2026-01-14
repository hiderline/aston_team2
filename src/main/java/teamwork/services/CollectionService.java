package teamwork.services;

import teamwork.factories.BusManager;
import teamwork.factories.FileBusFillStrategy;
import teamwork.factories.ManualBusFillStrategy;
import teamwork.factories.RandomBusFillStrategy;
import teamwork.models.Bus;
import teamwork.utils.MenuUtils;
import teamwork.validators.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class CollectionService {

    private final List<Bus> buses;

    public CollectionService() {
        this.buses = new ArrayList<>();
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void fillCollection(InputService inputService) {
        BusManager busManager = new BusManager();
        boolean menuActive = true;

        while (menuActive) {
            MenuUtils.showFillCollectionMenu();
            int choice = inputService.getIntInput("Выберите способ: ", 0, 3);

            switch (choice) {
                case 1:
                    fillManually(busManager, inputService);
                    menuActive = false;
                    break;
                case 2:
                    fillRandomly(busManager, inputService);
                    menuActive = false;
                    break;
                case 3:
                    fillFromFile(busManager, inputService);
                    menuActive = false;
                    break;
                case 0:
                    menuActive = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void fillManually(BusManager busManager, InputService inputService) {
        int amount = inputService.getIntInput("Введите кол-во элементов для заполнения:");
        MenuUtils.showManualFillMenu();
        busManager.setStrategy(new ManualBusFillStrategy());
        buses.addAll(busManager.createBuses(amount));
    }

    private void fillRandomly(BusManager busManager, InputService inputService) {
        int amount = inputService.getIntInput("Введите кол-во элементов для заполнения (max 100):", 1, 100);
        busManager.setStrategy(new RandomBusFillStrategy());
        buses.addAll(busManager.createBuses(amount));
        inputService.waitForEnter();
    }

    private void fillFromFile(BusManager busManager, InputService inputService) {
        int amount = inputService.getIntInput("Введите кол-во элементов для заполнения:");
        busManager.setStrategy(new FileBusFillStrategy());
        buses.addAll(busManager.createBuses(amount));
        inputService.waitForEnter();
    }

    public void displayCollection() {
        if (buses.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Bus bus : buses) {
                System.out.println(bus.toString());
            }
            System.out.println("Всего автобусов: " + buses.size());
        }
    }

    public void clearCollection() {
        buses.clear();
        ExceptionHandler.printInfo("Коллекция очищена");
    }
}
