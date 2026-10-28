package dataProvider;

import model.Registration;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderRegistration {
    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> RegistrationSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Registration.builder()
                .name("Lisa")
                .lastName("Snow")
                .email("snow" + System.currentTimeMillis() + "@gmail.com")
                .password("Snow123456$")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> EmptyName() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Registration.builder()
                .name("")
                .lastName("Snow")
                .email("snow@gmail.com")
                .password("Snow123456$")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> EmptyLastName() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Registration.builder()
                .name("Lisa")
                .lastName("")
                .email("snow@gmail.com")
                .password("Snow123456$")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> WrongEmail() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Registration.builder()
                .name("Lisa")
                .lastName("Snow")
                .email("sngmail.com")
                .password("Snow123456$")
                .build()
        });
        list.add(new Object[]{Registration.builder()
                .name("Lisa")
                .lastName("Snow")
                .email("")
                .password("Snow123456$")
                .build()
        });
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> WrongPassword() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Registration.builder()
                .name("Lisa")
                .lastName("Snow")
                .email("sngaa@mail.com")
                .password("!!!!")
                .build()
        });
        list.add(new Object[]{Registration.builder()
                .name("Lisa")
                .lastName("Snow")
                .email("sngaa@mail.com")
                .password("")
                .build()
        });
        return list.iterator();
    }
}
