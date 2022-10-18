package model.comparator;

import model.usertype.type.IntegerClass;

public class IntegerComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return ((IntegerClass)o1).getValue() - ((IntegerClass)o2).getValue();
    }
}
