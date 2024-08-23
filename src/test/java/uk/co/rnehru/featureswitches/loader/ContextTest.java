package uk.co.rnehru.featureswitches.loader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import uk.co.rnehru.featureswitches.loader.Context;
import uk.co.rnehru.featureswitches.loader.ContextLoadException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ContextTest {

    @Test
    void santiseStringShouldRemoveWhitespace() {
        String s = " hello ";
        assertEquals("hello", Context.ContextBuilder.sanitiseString(s));
    }

    @Test
    void santiseStringShouldRemoveDoubleQuotes() {
        String s = "\"hello\"";
        assertEquals("hello", Context.ContextBuilder.sanitiseString(s));
    }

    @Test
    void santiseStringShouldRemoveDoubleQuotesAndWhitespace() {
        String s = " \"hello\" ";
        assertEquals("hello", Context.ContextBuilder.sanitiseString(s));
    }

    @Test
    void santiseStringShouldRemoveSingleQuotes() {
        String s = "'hello'";
        assertEquals("hello", Context.ContextBuilder.sanitiseString(s));
    }

    @Test
    void santiseStringShouldRemoveSingleQuotesAndWhitespace() {
        String s = " 'hello' ";
        assertEquals("hello", Context.ContextBuilder.sanitiseString(s));
    }

    @Test
    void contextBuilderBuildShouldCreateContextFromConfigIfDefined() throws ContextLoadException {
        Context context = new Context.ContextBuilder().build("switches.default");
        assertEquals("switches.default", context.getContextName());
    }

    @Test
    void contextBuilderBuildShouldThrowExceptionIfConfigNotDefinted() {
        Executable executable = () -> new Context.ContextBuilder().build("missing");
        assertThrows(ContextLoadException.class, executable, "Could not load context: [missing]");
    }


}
