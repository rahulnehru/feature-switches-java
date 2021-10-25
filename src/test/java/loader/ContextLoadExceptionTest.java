package loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContextLoadExceptionTest {

    @Test
    void getMessageShouldReturnAFormattedExceptionMessage() {
        Exception e = new ContextLoadException("ctx", new NullPointerException());
        Assertions.assertEquals("Could not load context: [ctx]", e.getMessage());
    }

}
