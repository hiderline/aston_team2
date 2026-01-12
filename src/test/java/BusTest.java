import org.junit.jupiter.api.Test;
import teamwork.factories.BusCreator;
import teamwork.models.Bus;
import teamwork.validators.Validators;

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
    void busCreatorCreateFromLineIsCorrect() {
        String line = "123, Oka, 12000";
        Bus bus = BusCreator.createBusFromLine(line);
        assertNotNull(bus);
    }

    @Test
    void busValidatorNumberIsCorrect(){
        boolean result= Validators.validateNumber("Number");
        assertFalse(result);
    }

    @Test
    void busValidatorModelIsCorrect(){
        boolean result= Validators.validateModel("");
        assertFalse(result);
    }

    @Test
    void busValidatorOdometerIsCorrect(){
        boolean result= Validators.validateOdometer("km");
        assertFalse(result);
    }
}
