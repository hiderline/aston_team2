package com.example.bus;
public class Main{
    public static void main(String[] args) {
        List<Bus> buses = new ArrayList<>();

        buses.add(new Bus.Builder()
                .setNumber(12)
                .setModel("MAN")
                .setMileage(120000)
                .build());

        buses.add(new Bus.Builder()
                .setNumber(5)
                .setModel("Volvo")
                .setMileage(90000)
                .build());

        buses.add(new Bus.Builder()
                .setNumber(20)
                .setModel("Ikarus")
                .setMileage(150000)
                .build());

        BubbleSort.sortByMileage(buses);

        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }
}