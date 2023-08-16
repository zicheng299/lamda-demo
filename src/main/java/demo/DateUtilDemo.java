package demo;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateUtilDemo {


    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2023, 8, 8);
        LocalDate end = LocalDate.of(2023, 10, 9);


//        List<WeekRange> weekRanges = partitionIntoWeeks(start, end);
        List<WeekRange> weekRanges = partitionIntoMonths(start, end);

        WeekRange weekRange1 = weekRanges.get(0);
        weekRange1.setStart(start);

        for (WeekRange weekRange : weekRanges) {
            System.out.println(weekRange);
        }



    }

    public static List<WeekRange> partitionIntoMonths(LocalDate start, LocalDate end) {
        List<WeekRange> monthRanges = new ArrayList<>();

        LocalDate currentDate = start;
        while (currentDate.isBefore(end) || currentDate.isEqual(end)) {
            LocalDate monthStart = currentDate.withDayOfMonth(1);
            LocalDate monthEnd = monthStart.plusMonths(1).minusDays(1);

            if (monthEnd.isAfter(end)) {
                monthEnd = end;
            }

            monthRanges.add(new WeekRange(monthStart, monthEnd));
            currentDate = monthStart.plusMonths(1);
        }

        return monthRanges;
    }

    public static List<WeekRange> partitionIntoWeeks(LocalDate start, LocalDate end) {
        List<WeekRange> weekRanges = new ArrayList<>();

        LocalDate currentDate = start;
        while (currentDate.isBefore(end) || currentDate.isEqual(end)) {
            LocalDate weekStart = currentDate.with(TemporalAdjusters.previousOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek())).plusDays(1);
            LocalDate weekEnd = currentDate.with(TemporalAdjusters.nextOrSame(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek()));

            if (weekEnd.isAfter(end)) {
                weekEnd = end;
            }

            weekRanges.add(new WeekRange(weekStart, weekEnd));
            currentDate = weekEnd.plusDays(1);
        }

        return weekRanges;
    }
}

class WeekRange {
    private LocalDate start;
    private LocalDate end;

    public WeekRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "WeekRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
