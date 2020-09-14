package edu.dc.vorarlberg;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        LocalDate startDay = LocalDate.now();

        System.out.println("|Mo|Di|Mi|Do|Fr|Sa|So|");
        int i = 1;
        for (; i<startDay.withDayOfMonth(1).getDayOfWeek().getValue(); ++i)
        {
            System.out.print("|  ");
        }
        for (int j=1; j <= startDay.lengthOfMonth(); ++j)
        {
            if ((i % 7 == 0) || (i % 7 == 6)) {
                System.out.printf("%s%2s", "|", "WE");
            } else {
                System.out.printf("%s%2d", "|", j);
            }
            ++i;
            if (i % 7 == 1) System.out.println("|");
        }
        if (i % 7 != 1) {
            for (; i % 7 != 1; ++i) System.out.print("|  ");
            System.out.println("|");
        }

        MainWindow mw = new MainWindow();
        mw.setVisible(true);
    }
}
