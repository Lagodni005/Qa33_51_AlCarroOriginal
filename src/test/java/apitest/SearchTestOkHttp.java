package apitest;

import api.CarsDto;
import api.SearchDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTestOkHttp {
    Logger logger = LoggerFactory.getLogger(SearchTestOkHttp.class);
    Gson gson = new Gson();
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client = new OkHttpClient();

    @Test(groups = {"regress"})
    public void SearchSuccessTest() throws IOException {
        SearchDto searchDto = SearchDto.builder()
                .city("Eilat")
                .startDate("2026-10-11")
                .endDate("2026-10-17")
                .build();
        logger.info("Test data ---> " + searchDto);
        RequestBody body = RequestBody.create(gson.toJson(searchDto), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/cars/search")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 200 and cars list is returned");
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        String responseBody = response.body().string();
        CarsDto carsDto = gson.fromJson(responseBody, CarsDto.class);
        logger.info("Cars found ---> " + carsDto.getCars());
    }
}
