package teamwork.models;

import java.util.Objects;

public class Bus implements Comparable<Bus> {

    private final Integer number;
    private final String model;
    private final Integer odometer;

    public Bus(Integer number, String model, Integer odometer) {
        this.number = number;
        this.model = model;
        this.odometer = odometer;
    }

    public Integer getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(odometer, bus.odometer) &&
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
        private Integer number;
        private String model;
        private Integer odometer;

        public Builder setNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setOdometer(Integer odometer) {
            this.odometer = odometer;
            return this;
        }

        public Bus build() {
            return new Bus(number, model, odometer);
        }
    }
}
