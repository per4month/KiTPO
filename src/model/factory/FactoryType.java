package model.factory;

import model.usertype.prototype.AwaibleTypes;
import model.usertype.prototype.DateTimeType;
import model.usertype.prototype.IntegerType;
import model.usertype.prototype.ProtoType;

import java.util.ArrayList;

//TODO Реализовать фабрику
//TODO Такой вариант был в методичке // done

public class FactoryType {
    public ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>();
        for(AwaibleTypes at : AwaibleTypes.values()) {
            list.add(String.valueOf(at));
        }
        return list;

    }
    public ProtoType getBuilderByName(String name) throws Exception{
        switch(name) {
            case "Integer":
            {
                return new IntegerType();
            }
            case "DateTime":
            {
                return new DateTimeType();
            }
            default:
            throw new Exception("Can`t get builder by name");
        }
    }
}
