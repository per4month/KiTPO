package model.usertype.prototype;

import model.comparator.Comparator;
import model.comparator.IntegerComparator;
import model.usertype.type.IntegerClass;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class IntegerType implements ProtoType {

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
        IntegerClass intValue = new IntegerClass(rand.nextInt((max - min)) + min);
        return intValue;
    }

    @Override
    public Object clone(Object obj) {
        IntegerClass copyInt = new IntegerClass(((IntegerClass)obj).getValue());
        return copyInt;
    }

    @Override
    public Object readValue(InputStreamReader in) throws IOException{
        return in.read();
    }

    @Override
    public Object parseValue(String someString) {
        return Integer.parseInt(someString);
        // то что нужно?
        // нужно создавать объект типа IntegerClass?
    }

    @Override
    public Comparator getTypeComparator() {
        Comparator comparator = new IntegerComparator();
        return comparator;
    }
}
