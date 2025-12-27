package com.example.bus;

public class Bus {

    private int number;
    private String model;
    private int mileage;

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public int getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    // Builder
    public static class Builder {
        private int number;
        private String model;
        private int mileage;

        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
