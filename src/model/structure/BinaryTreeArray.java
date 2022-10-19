package model.structure;

import model.comparator.Comparator;

import java.util.ArrayList;

public class BinaryTreeArray {

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
    private int getSize(int num){
        if (num >= size || arrayTree.get(num) == null)
            return 0;
        return 1 + getSize(2 * num + 1) + getSize(2 * num + 2);
    }

    private Object getDataAtIndexRecursive(int searchIndex, int help){
        if (searchIndex >= size || searchIndex >= getSize(help))
            return null;

        int cntL = getSize(2 * help + 1); // число вершин в левом поддереве

        if (searchIndex < cntL)
            return getDataAtIndexRecursive(searchIndex,2 * help + 1); // Логический номер в левом поддереве

        searchIndex -= cntL; // отбросить вершины левого поддерева

        if (searchIndex-- == 0)
            return arrayTree.get(help); // Логический номер – номер текущей вершины

        return getDataAtIndexRecursive(searchIndex,2 * help + 2);  // в правое поддерево с остатком Логического номера
    }

    //нумерация "слева-направо", начинается с 0, см. cprog 8.5
    public Object getDataAtIndex(int searchIndex){
        return getDataAtIndexRecursive(searchIndex, 0);
    }

    public void removeNodeByIndex(int index){
        Object obj = getDataAtIndex(index);
        removeNodeByValue(0, obj);
    }

    // Функция для удаления узла из BST (array implementation)
    public void removeNodeByValue(int current, Object key)
    {
        if (current >= size)
            return;

        // базовый случай: ключ не найден в дереве
        if (arrayTree.get(current) == null) {
            return;
        }

        // если заданный ключ меньше корневого узла, повторить для левого поддерева
        if (comparator.compare(key, arrayTree.get(current)) < 0) {
            removeNodeByValue(2 * current + 1, key);
        }

        // если данный ключ больше, чем корневой узел, повторить для правого поддерева
        else if (comparator.compare(key, arrayTree.get(current)) > 0) {
            removeNodeByValue(2 * current + 2, key);
        }

        // ключ найден
        else {
            // Случай 1: удаляемый узел не имеет потомков (это листовой узел)
            if (2 * current + 1 >= size && 2 * current + 2 >= size){
                // обновить узел до null
                arrayTree.set(current, null);
                return;
            }
            else if (arrayTree.get(2 * current + 1) == null && arrayTree.get(2 * current + 2) == null)
            {
                // обновить узел до null
                arrayTree.set(current, null);
                return;
            }

            // Случай 2: удаляемый узел имеет двух потомков
            else if (arrayTree.get(2 * current + 1) != null && arrayTree.get(2 * current + 2) != null)
            {
                // найти его неупорядоченный узел-предшественник
                Object helpObj = new Object();
                Object predecessor = findMaximumKey(2 * current + 1, helpObj);

                // копируем значение предшественника в текущий узел
                arrayTree.set(current, predecessor);

                // рекурсивно удаляем предшественника
                removeNodeByValue(2 * current + 1, predecessor);
            }

            // Случай 3: удаляемый узел имеет только одного потомка
            else {
                // выбираем дочерний узел
                if (arrayTree.get(2 * current + 1) != null){ // если удаляемый узел имеет потомка в левом поддереве
                    // смещаем элементы в массиве
                    arrayShiftRecursive(current,2 * current + 1);
                }
                else { // если удаляемый узел имеет потомка в правом поддереве
                    // смещаем элементы в массиве
                    arrayShiftRecursive(current,2 * current + 2);
                }
            }
        }
    }

    private void arrayShiftRecursive(int rootIdx, int index){
        if (rootIdx > size || index > size)
            return;
        if (arrayTree.get(index) == null)
            return;
        arrayTree.set(rootIdx, arrayTree.get(index));
        arrayTree.set(index, null);
        if (2 * index + 1 >= size || 2 * rootIdx + 1 >= size)
            return;
        if (arrayTree.get(2 * index + 1) != null) // смещаем левое поддерево
            arrayShiftRecursive(2 * rootIdx + 1, 2 * index + 1);
        if (arrayTree.get(2 * index + 2) != null)  // смещаем правое поддерево
            arrayShiftRecursive(2 * rootIdx + 2, 2 * index + 2);
    }

    private Object findMaximumKey(int index, Object obj)
    {
        if (index >= size)
            return obj;
        if (arrayTree.get(index) == null)
            return obj;
        obj = findMaximumKey(2 * index + 2, arrayTree.get(index));
        return obj;
    }
}