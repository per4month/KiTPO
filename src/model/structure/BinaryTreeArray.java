package model.structure;

//TODO реализовать структуру
//TODO реализовать вывод и чтение из файла структуры данных
//TODO Реализована вставка в дерево, обход (печать в массив и печать дерева),
//TODO Нужно: удаление по логическому номеру (индексу),
//      поиск по по логическому номеру (индексу) (есть, но работает криво), балансировка
//      поиск по значению, итератор/foreach,
//      запись/чтение в/из бинарника


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

    /*

    Код с Сипрога

    // Число вершин в поддереве
    public int getSize(int n){
        if (n >= size || arrayTree.get(n) == null)
            return 0;
        return 1 + getSize(2 * n) + getSize(2 * n + 1);
    }

    // Обход дерева
    void scan(int n, int level, int ln){
        if (n >= size || arrayTree.get(n) == null)
            return;
        scan(2  * n,level + 1, ln);
        System.out.println("l = " + level + "; n = " + ln + "; data = " + arrayTree.get(n).toString() + "\n";
        ln++;
        scan(2 * n+1,level + 1, ln);
    }

    // Поиск вершины по логическому номеру
    Object getDataAtIndex(int m, int n){

        if (m>=size || m>=getSize(n))
            return null;

        int ll = getSize(2 * n); // число вершин в левом поддереве

        if (m < ll)
            return getDataAtIndex(m,2 * n); // ЛН в левом поддереве

        m -= ll; // отбросить вершины левого поддерева

        if (m-- ==0)
            return arrayTree.get(n); // ЛН – номер текущей вершины

        return getDataAtIndex(m,2 * n + 1);  // в правое поддерево с остатком ЛН
    }

// Включение с сохранением порядка
    void insert(int n, Object obj){
        if (n>=sz){                                             // увеличение размерности при выходе

            sz*=2;                                       // за пределы массива

            p=(char**)realloc(p,sz*sizeof(char*));

            for (int i=sz/2;i<sz;i++) p[i]=NULL; // с обнулением новой части

        }

        if (p[n] == NULL) { p[n]=ss; return; }         // свободная вершина - включение

        if (strcmp(ss,p[n])<0)

            insert(2*n, ss);                           // выбор левого или правого поддерева

        else                                                      // в зависимости от результата сравнения

            insert(2*n+1, ss);}

    /*Для балансировки двоичного дерева используется обход с сохранением упорядоченной последовательности
    в линейном массиве (массиве указателей). Затем массив рекурсивно делится пополам, а значение из середины
    интервала включается в новое дерево, которое получается сбалансированным.*/


//------------------------------------------------------85-03.cpp

//-- обход дерева с сохранением строк в линейном массиве

   /* void set(char *pp[], int n, int &ln){

        if (n>=sz || p[n]==NULL) return;

        set(pp,2*n,ln);

        pp[ln++]=p[n];

        set(pp,2*n+1,ln);}*/

// Построение сбалансированного дерева

    /*void balance(char *p[], int a, int b){

        if (a>b) return;

        int m=(a+b)/2;                                        // взять строку из середины интервала

        insert(1,p[m]);                                        // и включить в двоичное дерево

        balance(p,a,m-1);                                   // рекурсивно выполнить для левой и

        balance(p,m+1,b);                                  // правой частей

    }*/

// Балансировка дерева

    /*void balance(){

        int sz1=size(1),ln=0;

        char **pp=new char*[sz1];

        set(pp,1,ln);

        delete p;

        init();

        balance(pp,0,sz1-1);

    }};*/


}