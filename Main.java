package com.example.bus;
public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BusManager manager = new BusManager();

        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1 - file");
            System.out.println("2 - random");
            System.out.println("3 - hand");
            System.out.println("0 - exit");

            System.out.print("choise: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                running = false;
                continue;
            }

            System.out.print("number of bus: ");
            int size = scanner.nextInt();

            switch (choice) {
                case 1 -> manager.setStrategy(new FileBusFillStrategy());
                case 2 -> manager.setStrategy(new RandomBusFillStrategy());
                case 3 -> manager.setStrategy(new ManualBusFillStrategy());
                default -> {
                    System.out.println("error");
                    continue;
                }
            }

            List<Bus> buses = manager.createBuses(size);

            System.out.println("\bus list:");
            BubbleSort.sortByModel(buses);

            for (Bus bus : buses) {
                System.out.println(bus);
            }

        }
    }
}