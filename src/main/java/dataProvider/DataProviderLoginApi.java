package dataProvider;

import api.LoginBodyDto;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderLoginApi {
    @DataProvider
    public Iterator<Object[]> login() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{LoginBodyDto.builder()
                .username("margo@gmail.com")
                .password("Mmar123456$")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> wrongLogin() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{LoginBodyDto.builder()
                .username("margogmail.com")
                .password("Mmar123456$")
                .build()
        });
        list.add(new Object[]{LoginBodyDto.builder()
                .username("hh")
                .password("Mmar123456$")
                .build()
        });
        list.add(new Object[]{LoginBodyDto.builder()
                .username("pashalagodni@gmail.com")
                .password("#######")
                .build()
        });
        list.add(new Object[]{LoginBodyDto.builder()
                .username("pashalagodni@gmail.com")
                .password("")
                .build()
        });
        return list.iterator();
    }
}
