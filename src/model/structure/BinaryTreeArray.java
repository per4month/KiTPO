package model.structure;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//TODO реализовать структуру
//TODO реализовать вывод и чтение из файла структуры данных
//TODO Реализована вставка в дерево, обход (печать в массив и печать дерева),
//TODO Нужно: 
//удаление по логическому номеру (индексу)
//удаление по значению нужно? -- no
//поиск по по логическому номеру (индексу) (есть, но работает криво),  
//балансировка -- DONE
//поиск по значению -- done
//итератор/foreach,
//запись/чтение в/из бинарника -- нам что то мешает записывать тупа подряд с 0 по конец объекта?


import model.comparator.Comparator;

import java.util.ArrayList;

public class BinaryTreeArray implements Serializable {

    private ArrayList <Object> arrayTree;

    private transient Comparator comparator;

    private int size;
    // Инициализация структуры данных
    public BinaryTreeArray(){
        size = 10;
        arrayTree = new ArrayList <Object> (size);
        comparator = null;
    }

    public BinaryTreeArray(Comparator comparator){
        size = 10;
        arrayTree = new ArrayList <Object> (size);
        for (int i = 0; i < size; i++)
            arrayTree.add(null);
        this.comparator = comparator;
    }
    private BinaryTreeArray(int size, ArrayList<Object> t, Comparator c) {
        this.size = size;
        this.comparator = c;
        this.arrayTree = t;
    }
    public void save()  {
        try {
        FileOutputStream outputStream = new FileOutputStream("saved.ser");
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(this);
        out.close();
        outputStream.close();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
         
    }
    public BinaryTreeArray load() {
        BinaryTreeArray loadedArrayTree = null;
        try {
        FileInputStream fileIn = new FileInputStream("saved.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        loadedArrayTree = (BinaryTreeArray) in.readObject();
        in.close();
        fileIn.close();
        }
        catch(IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedArrayTree;

    }


    // Вcпомогательный метод вставки значения в массив
    private void insertRecursive(int current, Object obj){
        if (current >= size){ // увеличение размерности при выходе
            size *= 2; // за пределы массива
            for (int i = size/2; i <= size; i++) // с обнулением новой части
               arrayTree.add(null);
        }

        if (arrayTree.get(current) == null) {
            arrayTree.set(current, obj);
            return;
        }

        if (comparator.compare(obj,arrayTree.get(current)) < 0)
            insertRecursive(2 * current + 1, obj);
        else
            insertRecursive(2 * current + 2, obj);
    }

    // Вставка значения в дерево
    public void addValue(Object value) {
        insertRecursive(0, value);
    }
    private Object findRecursive(int current, Object value) {
        if (current > size) {
            return null;
        }
        if (comparator.compare(value, arrayTree.get(current)) == 0)
        return arrayTree.get(current);
        if (comparator.compare(value,arrayTree.get(current)) < 0)
        return findRecursive(2 * current + 1, value);
        else
        return findRecursive(2 * current + 2, value);
        
    }
    public Object findByValue(Object value) throws Exception{
        Object temp = findRecursive(0, value);
        if(temp == null) throw new Exception("Binary tree has no such value");
        return temp;

    }

    private void scan(int current, int level, boolean boolTree){
        if (current >= size)
            return;
        if (arrayTree.get(current) == null)
            return;

        scan(2 * current + 1, level + 1, boolTree);

        if (boolTree) {
            for (int i = 0; i < level; i++)
                System.out.print("\t");
            System.out.print(arrayTree.get(current).toString() + "\n");
        }
        else
            System.out.print(arrayTree.get(current).toString()+ " ");

        scan(2 * current + 2, level + 1, boolTree);
    }

    public void printTree(){
        scan(0,0, true);
    }

    public void printArray(){
        scan(0,0, false);
    }

    // Число вершин в поддереве
    public int getSize(int num){
        if (num >= size || arrayTree.get(num) == null)
            return 0;
        return 1 + getSize(2 * num + 1) + getSize(2 * num + 2);
    }

    //не работает как надо и порой кидает ошибкт
    private Object getDataAtIndexRecursive(int help, int searchIndex){

        if (help >= size || help >= getSize(searchIndex))
            return null;
        if (arrayTree.get(searchIndex) == null)
            return null;

        int cntL = getSize(2 * searchIndex + 1); // число вершин в левом поддереве

        if (help < cntL)
            return getDataAtIndexRecursive(help,2 * searchIndex + 1); // Логический номер в левом поддереве

        help -= cntL; // отбросить вершины левого поддерева

        if (help-- == 0)
            return arrayTree.get(searchIndex); // Логический номер – номер текущей вершины

        return getDataAtIndexRecursive(help,2 * searchIndex + 2);  // в правое поддерево с остатком Логического номера
    }
    //не работает как надо
    public Object getDataAtIndex(int searchIndex){
        return getDataAtIndexRecursive(0, searchIndex);
    }
    // итератор forEach
    public void forEach(DoWith func)
    {
        if (arrayTree == null || size <= 0)
            return;
        int sz = getSize(0);
        Vector <Integer> v = new Vector<Integer>(size);
        setHelp(v, 0);
        for(int i=0; i < sz; i++)
        {
            func.doWith(arrayTree.get(v.get(i)));
        }
    }
    //рекурсивная балансировка
    private void  balance(Vector<Object> t, int a, int b, ArrayList<Object> r) {

        if (a>b) return;
        if (a==b) return;

        int m=(a+b) >>> 1;                                        // взять строку из середины интервала
                          
        insertRecursive(r, 0,t.get(m));

        balance(t, m+1,b, r);                                   // рекурсивно выполнить для левой и

        balance(t, a,m,r);                                  // правой частей

    }
    //вставка для нового аррайлист при балансировке
    private void insertRecursive(ArrayList<Object> t, int current, Object obj){
        if (current >= size){ // увеличение размерности при выходе
            size *= 2; // за пределы массива
            for (int i = size/2; i <= size; i++) // с обнулением новой части
               t.add(null);
        }

        if (t.get(current) == null) {
            t.set(current, obj);
            return;
        }

        if (comparator.compare(obj,t.get(current)) < 0)
            insertRecursive(t, 2 * current + 1, obj);
        else
            insertRecursive(t, 2 * current + 2, obj);
    }
    //главный метод балансировки
    public BinaryTreeArray balance(){

        int sz1=getSize(0);

        Vector <Object> newArray = new Vector<Object> (size); //вектор индексов

        ArrayList<Object> newArrayTree = new ArrayList<Object>(size);
        for(int i = 0; i < size; i++) {
            newArrayTree.add(null);
        }
        set(newArray,0);
        balance(newArray,0, sz1, newArrayTree);
        BinaryTreeArray balanced = new BinaryTreeArray(this.size, newArrayTree, this.comparator);
        return balanced;

    }
    //метод для добавления индексов в вектор
    private void set(Vector<Object> t, int n){

        if (n>=size || arrayTree.get(n) == null) return;
        
        set(t,2*n+1);
        t.add(arrayTree.get(n));
        set(t,2*n+2);
        
    }
    //Вспомогательный метод обхода для forEach
    private void setHelp(Vector<Integer> t, int n){

        if (n>=size || arrayTree.get(n) == null) return;
        
        setHelp(t,2*n+1);
        t.add(n);
        setHelp(t,2*n+2);
        
    }
}