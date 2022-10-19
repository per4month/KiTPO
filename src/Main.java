import model.structure.BinaryTreeArray;
import model.usertype.prototype.DateTimeType;
import model.usertype.prototype.IntegerType;
import model.usertype.type.DateTimeClass;
import model.usertype.type.IntegerClass;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        IntegerType intType = new IntegerType();
        DateTimeType dateType = new DateTimeType();

        IntegerClass int1 = (IntegerClass) intType.parseValue("323");
        System.out.println(int1.toString());

        DateTimeClass int2 = (DateTimeClass) dateType.parseValue("1/1/2021 02:03:04");
        System.out.println(int2.toString());

       /* BinaryTreeArray bts = new BinaryTreeArray(intType.getTypeComparator());

        bts.addValue(new IntegerClass(4));
        bts.addValue(new IntegerClass(3));
        bts.addValue(new IntegerClass(10));
        bts.addValue(new IntegerClass(1));
        bts.addValue(new IntegerClass(5));
        bts.addValue(new IntegerClass(11));
        bts.addValue(new IntegerClass(8));
        bts.addValue(new IntegerClass(7));
        bts.addValue(new IntegerClass(9));

        bts.printTree();

        System.out.println("--------------------------");

        bts.removeNodeByIndex(8);*/

        //bts.printTree();
    }
}
