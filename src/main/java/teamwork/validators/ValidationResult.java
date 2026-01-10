package teamwork.validators;

/**
 * Класс для хранения результатов валидации
 */
public class ValidationResult {
    private boolean numberValid;
    private boolean modelValid;
    private boolean odometerValid;
    private int number;
    private String model;
    private int odometer;

    public boolean isAllValid() {
        return numberValid && modelValid && odometerValid;
    }

    // Геттеры и сеттеры
    public boolean isNumberValid() { return numberValid; }
    public void setNumberValid(boolean numberValid) { this.numberValid = numberValid; }

    public boolean isModelValid() { return modelValid; }
    public void setModelValid(boolean modelValid) { this.modelValid = modelValid; }

    public boolean isOdometerValid() { return odometerValid; }
    public void setOdometerValid(boolean odometerValid) { this.odometerValid = odometerValid; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getOdometer() { return odometer; }
    public void setOdometer(int odometer) { this.odometer = odometer; }
}
