package model.usertype.prototype;

import model.comparator.Comparator;
import model.comparator.IntegerComparator;
import model.usertype.type.IntegerClass;

import java.io.InputStreamReader;
import java.util.Random;

public class IntegerType implements ProtoType {
    private IntegerClass intValue;

    @Override
    public String typeName() {
        return "INTEGER";
    }

    @Override
    public Object create() {
        // генерация случайных чисел [-1000; 1000]
        int min = -1000;
        int max = 1000;
        Random rand = new Random();
        intValue = new IntegerClass(rand.nextInt((max - min) + 1));
        return intValue;
    }

    @Override
    public Object clone() {
        if (intValue != null) {
            IntegerClass copyInt = new IntegerClass(intValue.getValue());
            return copyInt;
        }
        return null;
    }

    @Override
    public Object readValue(InputStreamReader in) {
        //TODO Дописать чтение
        return null;
    }

    @Override
    public Object parseValue(String someString) {
        //TODO Дописать парсинг
        return null;
    }

    @Override
    public Comparator getTypeComparator() {
        Comparator comparator = new IntegerComparator();
        return comparator;
    }
}
