package loader;

import com.typesafe.config.ConfigValue;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigLoaderTest {

    private final ConfigLoader loader = new ConfigLoader("switches.default");

    @Test
    void loadShouldReturnAMapOfKeysAndConfigValues() {
        Map<String, ConfigValue> config = loader.load();

        assertTrue(config.containsKey("switchA"));
        assertEquals("BOOLEAN", config.get("switchA").valueType().name());
        assertTrue(Boolean.parseBoolean(config.get("switchA").render()));

        assertTrue(config.containsKey("switchB"));
        assertEquals("BOOLEAN", config.get("switchB").valueType().name());
        assertFalse(Boolean.parseBoolean(config.get("switchB").render()));


        assertTrue(config.containsKey("multilevel.switchC"));
        assertEquals("STRING", config.get("multilevel.switchC").valueType().name());
        assertEquals("\"2020-01-01T00:00:00.000Z\"",  config.get("multilevel.switchC").render());

        assertTrue(config.containsKey("multilevel.switchD"));
        assertEquals("STRING", config.get("multilevel.switchD").valueType().name());
        assertEquals("\"false\"", config.get("multilevel.switchD").render());
    }
}
