package guru.qa.demoqa.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    public static Configuration getConfig(){
        return ConfigCache.getOrCreate(Configuration.class);
    }

}
