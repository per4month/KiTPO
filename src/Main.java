import model.structure.BinaryTreeArray;
import model.usertype.prototype.DateTimeType;
import model.usertype.prototype.IntegerType;
import model.usertype.type.DateTimeClass;
import model.usertype.type.IntegerClass;
import view.GUI;


public class Main {
    public static void main(String[] args) {

        // GUI
        GUI gui = new GUI();
        gui.showGui();

        // Здесь выполняются все операции одним потоком
        IntegerType intType = new IntegerType();
        DateTimeType dateType = new DateTimeType();

        IntegerClass int1 = (IntegerClass) intType.parseValue("323");
        System.out.println(int1.toString());

        DateTimeClass int2 = (DateTimeClass) dateType.parseValue("1/1/2021 02:03:04");
        System.out.println(int2.toString());

    }
}
