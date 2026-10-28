package apitest;

import api.ErrorDto;
import api.LoginBodyDto;
import api.TokenDto;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkHttp {
    Logger logger = LoggerFactory.getLogger(LoginTestOkHttp.class);
    Gson gson = new Gson();
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client = new OkHttpClient();


    @Test(groups = {"smoke"})
    public void LoginSuccess() throws IOException {
        LoginBodyDto login = LoginBodyDto.builder()
                .username("margo@gmail.com")
                .password("Mmar123456$")
                .build();
        logger.info("Test data ---> " + login);
        RequestBody body = RequestBody.create(gson.toJson(login), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 200 and access token is returned");
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);

        String responseBody = response.body().string();
        TokenDto tokenDto = gson.fromJson(responseBody, TokenDto.class);
        logger.info("Access token ---> " + tokenDto.getAccessToken());
    }

    @Test(groups = {"smoke"})
    public void LoginWrongEmail() throws IOException {
        LoginBodyDto login = LoginBodyDto.builder()
                .username("margogmail.com")
                .password("Mmar123456$")
                .build();
        logger.info("Test data ---> " + login);
        RequestBody body = RequestBody.create(gson.toJson(login), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 401, message 'Login or Password incorrect'");
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 401);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getStatus(), 401);
        Assert.assertEquals(errorDto.getMessage(), "Login or Password incorrect");
        Assert.assertEquals(errorDto.getPath(), "/v1/user/login/usernamepassword");
    }

    @Test
    public void LoginWrongPassword() throws IOException {
        LoginBodyDto login = LoginBodyDto.builder()
                .username("margo@gmail.com")
                .password("Mmar$")
                .build();
        logger.info("Test data ---> " + login);
        RequestBody body = RequestBody.create(gson.toJson(login), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 401, message 'Login or Password incorrect'");
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 401);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getStatus(), 401);
        Assert.assertEquals(errorDto.getMessage(), "Login or Password incorrect");
        Assert.assertEquals(errorDto.getPath(), "/v1/user/login/usernamepassword");

    }

    @Test
    public void LoginUnregistred() throws IOException {
        LoginBodyDto login = LoginBodyDto.builder()
                .username("Pavel1001@gmail.com")
                .password("200623456@Qwert")
                .build();
        logger.info("Test data ---> " + login);
        RequestBody body = RequestBody.create(gson.toJson(login), JSON);
        Request request = new Request.Builder()
                .url("https://ilcarro-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        logger.info("Response code ---> " + response.code());
        logger.info("Assert check: status code 401, message 'Login or Password incorrect'");
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 401);

        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getStatus(), 401);
        Assert.assertEquals(errorDto.getMessage(), "Login or Password incorrect");
        Assert.assertEquals(errorDto.getPath(), "/v1/user/login/usernamepassword");
    }
}
