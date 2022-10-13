import model.structure.BinaryTreeArray;
import model.usertype.prototype.IntegerType;
import model.usertype.type.IntegerClass;

public class Main {
    public static void main(String[] args) {
        IntegerType intType = new IntegerType();

        BinaryTreeArray bts = new BinaryTreeArray(intType.getTypeComparator());

        bts.addValue(intType.create());
        bts.addValue(intType.create());
        bts.addValue(intType.create());
        bts.addValue(intType.create());
        bts.addValue(intType.create());
        bts.addValue(intType.create());

        bts.printTree();

        bts.printArray();

        System.out.println("\nsize = " + bts.getSize(2));
        System.out.println("\nsearch = " + ((IntegerClass) bts.getDataAtIndex(2)).toString());

    }
}
