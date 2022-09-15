package helpers;

import org.aeonbits.owner.ConfigFactory;
import qa.guru.owner.BrowserstackProviderQaGuru;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String videoURL(String sessionId){
        BrowserstackProviderQaGuru browserstackProviderQaGuru = ConfigFactory.create(BrowserstackProviderQaGuru.class, System.getProperties());

        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(browserstackProviderQaGuru.userName(), browserstackProviderQaGuru.userPassword())
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }

}
