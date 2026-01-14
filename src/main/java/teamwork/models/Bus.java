package teamwork.models;

import java.util.Objects;

public class Bus {

    private final Integer number;    // Номер
    private final String model;     // Модель
    private final Integer odometer;     // Пробег

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.odometer = builder.odometer;
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

    public boolean isNumberEven() {
        return number % 2 == 0;
    }

    @Override
    public String toString() {
        return String.format("Автобус [Номер: %s, Модель: %s, Пробег: %d км]",
                number, model, odometer);
    }

    public static class Builder {
        private Integer number;
        private String model;
        private Integer odometer;

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder odometer(Integer odometer) {
            this.odometer = odometer;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
