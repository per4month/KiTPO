package model.usertype.prototype;

import model.comparator.Comparator;
import model.comparator.DateTimeComparator;
import model.usertype.type.DateTimeClass;

import java.io.InputStreamReader;
import java.util.Random;

public class DateTimeType implements ProtoType {
    private DateTimeClass dateTimeValue;

    @Override
    public String typeName() {
        return "DATETIME";
    }

    @Override
    public Object create() {

        //генерация случайных чисел
        // дата [1; 31]
        int minDay = 1, maxDay = 31;
        // месяц [1; 12]
        int minMonth = 1, maxMonth = 12;
        // год [988; 2048]
        int minYear = 988, maxYear = 2048;
        // часы [0; 23]
        int minHour = 0, maxHour = 23;
        // минуты, секунды [0 ; 59]
        int minTime = 0, maxTime = 59;

        Random rand = new Random();
        int day = rand.nextInt((maxDay - minDay) + 1);
        int month = rand.nextInt((maxMonth - minMonth) + 1);
        int year = rand.nextInt((maxYear - minYear) + 1);
        int hour = rand.nextInt(maxHour - minHour);
        int minute = rand.nextInt(maxTime - minTime);
        int second = rand.nextInt(maxTime - minTime);

        dateTimeValue = new DateTimeClass(day, month, year, hour, minute, second);

        return dateTimeValue;
    }

    @Override
    public Object clone() {
        if (dateTimeValue != null) {
            DateTimeClass copyDateTime = new DateTimeClass(dateTimeValue.getDay(),
                    dateTimeValue.getHour(), dateTimeValue.getYear(),
                    dateTimeValue.getHour(), dateTimeValue.getMinute(),
                    dateTimeValue.getSecond());
            return copyDateTime;
        }
        return null;
    }

    @Override
    public Object readValue(InputStreamReader in) {
        //TODO Доработать чтение
        return null;
    }

    @Override
    public Object parseValue(String someString) {
        //TODO Доработать парсинг
        return null;
    }

    @Override
    public Comparator getTypeComparator() {
        Comparator comparator = new DateTimeComparator();
        return comparator;
    }
}
