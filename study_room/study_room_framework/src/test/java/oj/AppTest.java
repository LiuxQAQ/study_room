package oj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppTest {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy-MM");

        String format = df.format(new Date());
        System.out.println(format);
    }
}
