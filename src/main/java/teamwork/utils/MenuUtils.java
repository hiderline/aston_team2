package teamwork.utils;

public class MenuUtils {
    public static void showMainMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Заполнить коллекцию");
        System.out.println("2. Показать коллекцию");
        System.out.println("3. Отсортировать коллекцию");
        System.out.println("4. Сохранить в файл");
        System.out.println("5. Найти количество вхождений");
        System.out.println("6. Очистить коллекцию");
        System.out.println("0. Выход");
    }

    public static void showFillCollectionMenu() {
        System.out.println("\n=== Заполнение коллекции ===");
        System.out.println("1. Вручную");
        System.out.println("2. Случайно");
        System.out.println("3. Из файла");
    }

    public static void showSortCollectionMenu() {
        System.out.println("\n=== Сортировка ===");
        System.out.println("Выберите поле для сортировки:");
        System.out.println("1. По номеру");
        System.out.println("2. По модели");
        System.out.println("3. По пробегу");
        System.out.println("4. По числовому полю (доп.задание 1)");
    }
}
