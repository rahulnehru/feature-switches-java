package loader;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class ConfigLoader {

    protected ConfigLoader(final String configPath) {
        this.configPath = configPath;
    }

    private final String configPath;

    private Config loadFromConfig(final String configPath) {
        Config config = ConfigFactory.load().getConfig(configPath);
        config.checkValid(config);
        return config;
    }

    private Map<String, ConfigValue> mapConfig(final Config config) {
        Map<String, ConfigValue> mapped = new HashMap<>();
        Set<Map.Entry<String, ConfigValue>> configEntries = config.entrySet();
        configEntries.forEach(c -> mapped.put(c.getKey(), c.getValue()));
        return mapped;
    }

    protected Map<String, ConfigValue> load() {
        return mapConfig(loadFromConfig(this.configPath));
    }

}
