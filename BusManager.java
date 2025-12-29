package com.example.bus;

import java.util.List;

public class BusManager {

    private BusFillStrategy strategy;

    public void setStrategy(BusFillStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Bus> createBuses(int size) {
        return strategy.fillBuses(size);
    }
}
