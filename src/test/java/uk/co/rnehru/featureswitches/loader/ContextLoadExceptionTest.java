package uk.co.rnehru.featureswitches.loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.co.rnehru.featureswitches.loader.ContextLoadException;

public class ContextLoadExceptionTest {

    @Test
    void getMessageShouldReturnAFormattedExceptionMessage() {
        Exception e = new ContextLoadException("ctx", new NullPointerException());
        Assertions.assertEquals("Could not load context: [ctx]", e.getMessage());
    }

}
