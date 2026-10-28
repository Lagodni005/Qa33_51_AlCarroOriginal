package apitest;

import api.CarDto;
import api.CarsDto;
import api.SearchDto;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class SearchTestRA {
    Logger logger = LoggerFactory.getLogger(SearchTestRA.class);
    String endPoint = "/v1/cars/search";

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://ilcarro-backend.herokuapp.com/";
    }

    @Test(groups = {"regress"})
    public void SearchSuccess(){
        SearchDto sah = SearchDto.builder()
                .city("Eilat")
                .startDate("2026-10-11")
                .endDate("2026-10-17")
                .build();
        logger.info("Test data ---> " + sah);
        logger.info("Assert check: status code 200, cars list is not empty, every car is in Eilat");

        CarsDto carsDto = given()
                .body(sah)
                .contentType("application/json")
                .when()
                .post(endPoint)
                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(CarsDto.class);

        Assert.assertFalse(carsDto.getCars().isEmpty());
        for (CarDto car : carsDto.getCars()) {
            Assert.assertEquals(car.getCity(), "Eilat");
        }
        logger.info("Cars found ---> " + carsDto.getCars().size());
        logger.info("First car ---> " + carsDto.getCars().get(0));
    }
}
