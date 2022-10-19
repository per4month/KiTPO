package model.factory;

import model.usertype.prototype.AvailableTypes;
import model.usertype.prototype.DateTimeType;
import model.usertype.prototype.IntegerType;
import model.usertype.prototype.ProtoType;

import java.util.ArrayList;

public class FactoryType {
    public ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>();
        for(AvailableTypes at : AvailableTypes.values()) {
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
