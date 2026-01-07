package teamwork.models;

import java.util.Objects;

public class Bus implements Comparable<Bus> {
    private final int number;    // Номер
    private final String model;     // Модель
    private final int odometer;     // Пробег

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.odometer = builder.odometer;
    }

    public int getNumber() { return number; }
    public String getModel() { return model; }
    public int getOdometer() { return odometer; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return odometer == bus.odometer &&
                Objects.equals(number, bus.number) &&
                Objects.equals(model, bus.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, odometer);
    }

    public boolean isOdometerEven() {
        return odometer % 2 == 0;
    }

    @Override
    public String toString() {
        return String.format("Автобус [Номер: %s, Модель: %s, Пробег: %d км]",
                number, model, odometer);
    }

    @Override
    public int compareTo(Bus o) {
        return 0;
    }


    public static class Builder {
        private int number;
        private String model;
        private int odometer;

        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setOdometer(int odometer) {
            this.odometer = odometer;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
