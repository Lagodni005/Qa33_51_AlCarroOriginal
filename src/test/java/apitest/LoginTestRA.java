package apitest;

import api.LoginBodyDto;
import api.TokenDto;
import dataProvider.DataProviderLogin;
import dataProvider.DataProviderLoginApi;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginTestRA {
    Logger logger = LoggerFactory.getLogger(LoginTestRA.class);
    String endpoint = "/v1/user/login/usernamepassword";

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://ilcarro-backend.herokuapp.com/";
    }

    @Test(dataProvider = "login", groups = "smoke", dataProviderClass = DataProviderLoginApi.class)
    public void loginSuccess(LoginBodyDto loginBodyDto){
        LoginBodyDto log = LoginBodyDto.builder()
                .username(loginBodyDto.getUsername())
                .password(loginBodyDto.getPassword())
                .build();
        logger.info("Test data ---> " + log);
        logger.info("Assert check: status code 200 and access token is returned");

        TokenDto tDto = given()
                .body(log)
                .contentType("application/json")
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(TokenDto.class);
        logger.info("Access token ---> " + tDto.getAccessToken());
    }

    @Test(dataProvider = "wrongLogin", dataProviderClass = DataProviderLoginApi.class)
    public void WrongTest(LoginBodyDto loginBodyDto){
        LoginBodyDto log = LoginBodyDto.builder()
                .username(loginBodyDto.getUsername())
                .password(loginBodyDto.getPassword())
                .build();
        logger.info("Test data ---> " + log);
        logger.info("Assert check: status code 401, message 'Login or Password incorrect'");
        given()
                .body(log)
                .contentType("application/json")
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(401)
                .assertThat().body("message", containsString("Login or Password incorrect"))
                .assertThat().body("path", equalTo("/v1/user/login/usernamepassword"));
    }
}
