package home.owner;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/browserstackHome.properties")
public interface BrowserstackProviderHome extends Config {

    @Key("userName")
    String userName();

    @Key("userPassword")
    String userPassword();

    @Key("app")
    String app();

    @Key("device")
    String device();

    @Key("os_version")
    String os_version();
}

