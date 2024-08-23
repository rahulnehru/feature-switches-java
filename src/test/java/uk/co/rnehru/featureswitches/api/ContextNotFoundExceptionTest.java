package uk.co.rnehru.featureswitches.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class ContextNotFoundExceptionTest {

    @Test
    void getMessageShouldReturnAFormattedExceptionMessage() {
        Exception e = new ContextNotFoundException("ctx", new NullPointerException());
        Assertions.assertEquals("Could not find context [ctx]", e.getMessage());
    }

}
