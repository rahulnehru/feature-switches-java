package api;

import model.BooleanSwitch;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;


public class FeatureSwitchesTest {

    private final FeatureSwitches f = FeatureSwitches.getInstance();

    @AfterEach
    void after() {
        FeatureSwitches.getInstance().resetAll();
        FeatureSwitches.getInstance().resetAll("switches.secondContext");
    }

    @Test
    void getInstanceShouldReturnOnlySingleInstance() {
        FeatureSwitches g = FeatureSwitches.getInstance();
        assertEquals(f,g);
        g.turnAllOff();
        assertFalse(g.getSwitch("switchA").isOn());
        f.turnAllOn();
        assertTrue(g.getSwitch("switchA").isOn());
    }

    @Test
    void getSwitchShouldReturnSwitchIfExistsInDefaultContext() {
        assertTrue(f.getSwitch("switchA") instanceof BooleanSwitch);
    }

    @Test
    void getSwitchShouldThrowSwitchNotFoundExceptionIfSwitchNotInDefaultContext() {
        Executable e = () -> f.getSwitch("switchL");
        assertThrows(SwitchNotFoundException.class, e, "Could not find switch with name [switchL]");
    }

    @Test
    void getSwitchShouldReturnSwitchIfExistsInNonDefaultContext() {
        assertTrue(FeatureSwitches.getInstance().getSwitch("switches.secondContext", "switchE") instanceof BooleanSwitch);
    }

    @Test
    void getSwitchShouldThrowSwitchNotFoundExceptionIfSwitchNotInOtherContext() {
        Executable e = () -> f.getSwitch("switches.secondContext", "switchL");
        assertThrows(SwitchNotFoundException.class, e, "Could not find switch with name [switchL]");
    }

    @Test
    void getAllSwitchesShouldReturnAllSwitchesForDefaultContext() {
        assertEquals(4, f.getAllSwitches().size());
    }

    @Test
    void getAllSwitchesShouldReturnAllSwitchesForNonDefaultContext() {
        assertEquals(1, f.getAllSwitches("switches.secondContext").size());
    }

    @Test
    void getAllSwitchesShouldThrowExceptionIfContextNotFound() {
        Executable e = () -> f.getAllSwitches("switches.missingContext");
        assertThrows(ContextNotFoundException.class, e, "Could not find context [switches.missingContext]");
    }

    @Test
    void turnAllOnOffShouldTurnOnAllSwitchesInDefaultContext() {
        f.turnAllOn();
        f.getAllSwitches().forEach((key, value) -> assertTrue(value.isOn()));

        f.turnAllOff();
        f.getAllSwitches().forEach((key, value) -> assertFalse(value.isOn()));
    }

    @Test
    void turnAllOnOffShouldTurnOnAllSwitchesInContext() {
        f.turnAllOn("switches.secondContext");
        f.getAllSwitches("switches.secondContext").forEach((key, value) -> assertTrue(value.isOn()));

        f.turnAllOff("switches.secondContext");
        f.getAllSwitches("switches.secondContext").forEach((key, value) -> assertFalse(value.isOn()));
    }

}
