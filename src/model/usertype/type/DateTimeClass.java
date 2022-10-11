package model.usertype.type;

// TODO добавить проверки, чтобы
// TODO 1 <= day <= 31
// TODO 1 <= month <= 12
// TODO 0 <= year (до н.э. не считаем, а будущее - да)
// TODO 0 <= hour <= 23
// TODO 0 <= minute <= 59
// TODO 0 <= second <= 59
// TODO Проверка на високосный год
// TODO Проверка на корректное число в месяце
// TODO Возможно придётся поправить toString() для вывода, чтобы:
// TODO Было не так 1/1/2021 0:0:1
// TODO А было вот так 01/01/2021 00:00:00 (возникнут ли проблемы с парсингом?)

public class DateTimeClass {
    private int day;
    private int month;
    private int year;

    private int hour;
    private int minute;
    private int second;

    public DateTimeClass(int day, int month, int year, int hour, int minute, int second) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return String.valueOf(day) + "/" +
                String.valueOf(month) + "/" +
                String.valueOf(year) + " " +
                String.valueOf(hour) + ":" +
                String.valueOf(month) + ":" +
                String.valueOf(second);
    }
}
