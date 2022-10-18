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
        System.out.println("\nsize = " + bts.getSize(0));
        try {
            Object s = bts.findByValue(new IntegerClass(5));
            System.out.println("I found " + s.toString());
            }
            catch(Exception ex)
            {
                System.out.println("Not found");
            }
        bts.printTree();
        BinaryTreeArray bts2 = bts.balance();
        System.out.println("------------BALANCED---------------");
        bts2.printTree();
        bts.save();
        bts2 = bts2.load();
        System.out.println("------------DESERIALIZED---------------");
        bts2.printTree();
        System.out.println("------------ITERATOR---------------");
        bts2.forEach((values) -> {
            System.out.println(values);
        });

    }
}
