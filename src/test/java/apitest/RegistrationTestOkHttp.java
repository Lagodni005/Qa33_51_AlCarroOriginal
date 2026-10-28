package apitest;

import api.ErrorDto;
import api.RegistrationBodyDto;
import api.TokenDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTestOkHttp {
    Logger logger = LoggerFactory.getLogger(RegistrationTestOkHttp.class);
    Gson gson = new Gson();
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client = new OkHttpClient();


    @Test(groups = {"smoke"})
    public void RegistrationSuccess() throws IOException {
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        RegistrationBodyDto registration = RegistrationBodyDto.builder()
                .firstName("Pavel")
                .lastName("Lagodni")
                .username("lago" + i + "@gmail.com")
                .password(i + "Hgf@123")
                .build();
        logger.info("Test data ---> " + registration);
        RequestBody body = RequestBody.create(gson.toJson(registration), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 200 and access token is returned");
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);
        TokenDto tokenDto = gson.fromJson(responseBody, TokenDto.class);
        logger.info("Access token ---> " + tokenDto.getAccessToken());
    }

    @Test(groups = {"smoke"})
    public void RegistrationWrongEmail() throws IOException{
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        RegistrationBodyDto registration = RegistrationBodyDto.builder()
                .firstName("Pavel")
                .lastName("Lagodni")
                .username("lago" + i + "gmail.com")
                .password(i + "Hgf@123")
                .build();
        logger.info("Test data ---> " + registration);
        RequestBody body = RequestBody.create(gson.toJson(registration), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 400, message 'must be a well-formed email address'");
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getStatus(),400);
        Assert.assertEquals(errorDto.getError(), "Bad Request");
        Assert.assertEquals(errorDto.getMessage().toString(), "{username=must be a well-formed email address}");
    }

    @Test
    public void RegistrationWrongPassword() throws IOException{
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        RegistrationBodyDto registration = RegistrationBodyDto.builder()
                .firstName("Pavel")
                .lastName("Lagodni")
                .username("lago" + i + "@gmail.com")
                .password(i + "@123")
                .build();
        logger.info("Test data ---> " + registration);
        RequestBody body = RequestBody.create(gson.toJson(registration), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 400, password format error message");
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getStatus(),400);
        Assert.assertEquals(errorDto.getError(), "Bad Request");
        Assert.assertEquals(errorDto.getMessage().toString(), "{password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number; Can contain special characters [@$#^&*!]}");
    }
}
