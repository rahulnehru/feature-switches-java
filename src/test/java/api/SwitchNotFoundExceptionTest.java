package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SwitchNotFoundExceptionTest {

    @Test
    void getMessageShouldReturnAFormattedExceptionMessage() {
        Exception e = new SwitchNotFoundException("ctx", new NullPointerException());
        Assertions.assertEquals("Could not find switch with name [ctx]", e.getMessage());
    }

}
