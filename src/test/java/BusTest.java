import org.junit.jupiter.api.Test;
import teamwork.models.Bus;
import teamwork.validators.BusValidator;

import static org.junit.jupiter.api.Assertions.*;

public class BusTest {
    @Test
    void builderCreateBusIsCorrect(){
        Bus bus = new Bus.Builder()
                .setNumber(1)
                .setModel("ModelS")
                .setOdometer(100)
                .build();

        assertEquals(1, bus.getNumber());
        assertEquals("ModelS", bus.getModel());
        assertEquals(100, bus.getOdometer());
    }

    @Test
    void busValidatorNumberIsCorrect(){
        boolean result= BusValidator.validateNumber("Number");
        assertFalse(result);
    }

    @Test
    void busValidatorModelIsCorrect(){
        boolean result= BusValidator.validateModel("");
        assertFalse(result);
    }

    @Test
    void busValidatorOdometerIsCorrect(){
        boolean result= BusValidator.validateOdometer("km");
        assertFalse(result);
    }
}
