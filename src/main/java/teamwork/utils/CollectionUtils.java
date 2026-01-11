package teamwork.utils;

import teamwork.factories.BusManager;
import teamwork.factories.FileBusFillStrategy;
import teamwork.factories.ManualBusFillStrategy;
import teamwork.factories.RandomBusFillStrategy;
import teamwork.models.Bus;

import java.util.List;

import static teamwork.utils.ConsoleUtils.getChoiceFromConsole;
import static teamwork.utils.ConsoleUtils.getNumberFromConsole;
import static teamwork.utils.MenuUtils.showManualFillMenu;

public class CollectionUtils {

    private static List<Bus> buses;
    private static final String MESSAGE = "Введите кол-во элементов для заполнения: ";

    public static void fillCollection() {
        BusManager busManager = new BusManager();
        boolean isMenuActive = true;

        while (isMenuActive) {
            MenuUtils.showFillCollectionMenu();
            String choice = getChoiceFromConsole();
            int amount;

            switch (choice) {
                case "1":
                    amount = getNumberFromConsole(MESSAGE);
                    showManualFillMenu();

                    busManager.setStrategy(new ManualBusFillStrategy());
                    buses = busManager.createBuses(amount);
                    isMenuActive = false;
                    break;
                case "2":
                    amount = getNumberFromConsole(MESSAGE);

                    busManager.setStrategy(new RandomBusFillStrategy());
                    buses = busManager.createBuses(amount);
                    isMenuActive = false;
                    break;
                case "3":
                    amount = getNumberFromConsole(MESSAGE);

                    busManager.setStrategy(new FileBusFillStrategy());
                    buses = busManager.createBuses(amount);
                    isMenuActive = false;
                    break;
                case "0":
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    public static void displayCollection() {
        if (buses.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Bus bus : buses) {
                System.out.println(bus);
            }
            System.out.println("Всего автобусов: " + buses.size());
        }
    }

    public static void sortCollection() {
        MenuUtils.showSortCollectionMenu();
    }

    public static void saveToFile() {

    }

    public static void clearCollection() {
        buses.clear();
    }
}
