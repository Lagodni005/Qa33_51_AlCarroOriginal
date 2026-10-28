package apitest;

import api.RegistrationBodyDto;
import dataProvider.DataProviderRegistration;
import dataProvider.DataProviderRegistrationApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTestRA {
    Logger logger = LoggerFactory.getLogger(RegistrationTestRA.class);
    String endpoint = "/v1/user/registration/usernamepassword";

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://ilcarro-backend.herokuapp.com/";
    }

    @Test(dataProvider = "registration", groups = "smoke", dataProviderClass = DataProviderRegistrationApi.class)
    public void RegistrationSuccess(RegistrationBodyDto registrationBodyDto){
        RegistrationBodyDto reg = RegistrationBodyDto.builder()
                .firstName(registrationBodyDto.getFirstName())
                .lastName(registrationBodyDto.getLastName())
                .username(registrationBodyDto.getUsername())
                .password(registrationBodyDto.getPassword())
                .build();
        logger.info("Test data ---> " + reg);
        logger.info("Assert check: status code 200 and access token is returned");
        String token = given()
                .body(reg)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body("accessToken", notNullValue())
                .extract()
                .path("accessToken");
        logger.info("Access token ---> " + token);
    }

    @Test(dataProvider = "registrationWrong", groups = "regress", dataProviderClass = DataProviderRegistrationApi.class)
    public void WrongRegistrationTest(RegistrationBodyDto registrationBodyDto, String expectedMessage){
        logger.info("Test data ---> " + registrationBodyDto);
        logger.info("Assert check: status code 400, message contains '" + expectedMessage + "'");
        given()
                .body(registrationBodyDto)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(400)
                .assertThat().body("error", equalTo("Bad Request"))
                .assertThat().body("message.toString()", containsString(expectedMessage))
                .assertThat().body("path", equalTo(endpoint));
    }
}
