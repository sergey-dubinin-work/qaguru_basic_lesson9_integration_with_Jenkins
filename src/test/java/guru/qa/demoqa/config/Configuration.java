package guru.qa.demoqa.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:selenoid.properties"
})
public interface Configuration extends Config {

    @Key("selenoid.url")
    String selenoidUrl();

}
