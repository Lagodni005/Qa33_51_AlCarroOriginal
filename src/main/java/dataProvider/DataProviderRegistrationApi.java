package dataProvider;

import api.RegistrationBodyDto;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderRegistrationApi {
    @DataProvider
    public Iterator<Object[]> registration() {
        List<Object[]> list = new ArrayList<>();
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        list.add(new Object[]{RegistrationBodyDto.builder()
                .firstName("Alex")
                .lastName("Logan")
                .username("Lagarril" + i + "@gmail.com")
                .password(i + "#234Qwerty")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationWrong(){
        List<Object[]> list = new ArrayList<>();
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        list.add(new Object[]{RegistrationBodyDto.builder()
                .firstName("Sasha")
                .lastName("Pavlov")
                .username("gmail.com")
                .password(i + "@1001Qwert")
                .build(),
                "must be a well-formed email address"
        });
        list.add(new Object[]{RegistrationBodyDto.builder()
                .firstName("Sasha")
                .lastName("Pavlov")
                .username("pavlovav" + i + "@gmail.com")
                .password("1001")
                .build(),
                "At least 8 characters"
        });
        list.add(new Object[]{RegistrationBodyDto.builder()
                .firstName("Sasha")
                .lastName("Pavlov")
                .username("pavlovav" + i + "@gmail.com")
                .password("bgbgfbgfgb")
                .build(),
                "At least 8 characters"
        });
        list.add(new Object[]{RegistrationBodyDto.builder()
                .firstName("Sasha")
                .lastName("Pavlov")
                .username("pavlovav" + i + "@gmail.com")
                .password("@#$%$#@#")
                .build(),
                "At least 8 characters"
        });
        list.add(new Object[]{RegistrationBodyDto.builder()
                .firstName("Margo")
                .lastName("Margo")
                .username("margo@gmail.com")
                .password("Mmar123456$")
                .build(),
                "User already exists"
        });
        return list.iterator();
    }
}
