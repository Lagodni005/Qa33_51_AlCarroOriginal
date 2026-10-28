package dataProvider;

import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderFindCar {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private static String inDays(int days) {
        return LocalDate.now().plusDays(days).format(FORMAT);
    }

    @DataProvider
    public Iterator<Object[]> FindCarSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Haifa", inDays(1), inDays(3)});
        list.add(new Object[]{"Tel Aviv", inDays(10), inDays(15)});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> EmptyCity() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"", inDays(1), inDays(3)});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> WrongPeriod() {
        List<Object[]> list = new ArrayList<>();
        // dates in the past
        list.add(new Object[]{"Haifa", inDays(-5), inDays(-2), "You can't pick date before today"});
        // "to" is before "from"
        list.add(new Object[]{"Haifa", inDays(5), inDays(2), "Second date must be after first date"});
        return list.iterator();
    }
}
