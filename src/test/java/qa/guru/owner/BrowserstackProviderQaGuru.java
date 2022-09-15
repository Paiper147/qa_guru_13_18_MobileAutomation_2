package qa.guru.owner;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/browserstackQaGuru.properties")
public interface BrowserstackProviderQaGuru extends Config {

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

