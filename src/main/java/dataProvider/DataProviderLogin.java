package dataProvider;

import model.Login;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderLogin{
    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> Login() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Login.builder()
                .email("lagodnip4@gmail.com")
                .password("20042005Lpa@")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> WrongEmail() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Login.builder()
                .email("@gmail.com")
                .password("20042005Lpa@")
                .build()
        });
        list.add(new Object[]{Login.builder()
                .email("gmail.com")
                .password("20042005Lpa@")
                .build()
        });
        list.add(new Object[]{Login.builder()
                .email("123456234@")
                .password("20042005Lpa@")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> WrongPassword() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Login.builder()
                .email("lagodnip4@gmail.com")
                .password("2004")
                .build()
        });
        list.add(new Object[]{Login.builder()
                .email("lagodnip4@gmail.com")
                .password("hghgghg")
                .build()
        });
        list.add(new Object[]{Login.builder()
                .email("lagodnip4@gmail.com")
                .password("jjjjhjkhj97879789789kkkkk79k79k79798k79k9k97k979k79k97k79k79k97k9k7k7979k79k79k9k79k79k79k779k9k7k97k97k79k97k97k979k79k79k79k79k779k79k79k79k79k79k9k79k79k9k9k779k79kk979k79k9k79k79k79k79k799k9k79k79kk97k97k79k79k79k79k79k")
                .build()
        });
        return list.iterator();
    }
}
