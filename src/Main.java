import model.structure.BinaryTreeArray;
import model.usertype.prototype.IntegerType;
import model.usertype.type.IntegerClass;

public class Main {
    public static void main(String[] args) {
        IntegerType intType = new IntegerType();

        BinaryTreeArray bts = new BinaryTreeArray(intType.getTypeComparator());

        bts.addValue(intType.create());
        bts.addValue(new IntegerClass(5));
        bts.addValue(intType.create());
        bts.addValue(intType.create());
        bts.addValue(intType.create());
        bts.addValue(intType.create());

        bts.printTree();

        bts.printArray();
        try {
        Object s = bts.findByValue(new IntegerClass(5));
        System.out.println("I found " + s.toString());
        }
        catch(Exception ex)
        {
            System.out.println("Not found");
        }
        System.out.println("\nsize = " + bts.getSize(2));
        System.out.println("\nsearch = " + ((IntegerClass) bts.getDataAtIndex(1)).toString());

    }
}
