package io.github.jzyfc.Five;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {

    List<Integer> list;

    @BeforeEach
    void beforeEach() {
        list = new List<>(new Integer[]{1, 2, 3, 4});
    }

    @Test
    void init() {
        System.out.println(list);
    }

    @Test
    void insert() {
        System.out.println(list);
        list.insert(5, 4);
        System.out.println(list);
        list.insert(-1, 1);
        System.out.println(list);
    }

    @Test
    void delete() {
        System.out.println(list);
        list.delete(0); // delete the first elem
        System.out.println(list);
        list.delete(1); // delete middle elem
        System.out.println(list);
        list.delete(1); // delete the last elem
        System.out.println(list);
    }

    @Test
    void findFirst() {
        System.out.println(list.findFirst(1));
        System.out.println(list.findFirst(0));
    }

    @Test
    void findLast() {
        System.out.println(list.findLast(4));
        System.out.println(list.findLast(100));
    }

    @Test
    void concat() {
        List<Integer> otherList = new List<>(new Integer[]{5, 6, 7, 8});
        list.concat(otherList);
        System.out.println(list);
    }

    @Test
    void sort() {
        List<Integer> notSorted = new List<>(new Integer[]{11, 12, 13, 14, 15});
        System.out.println(notSorted);
        notSorted.sort((o1, o2) -> o2 % 10 - o1 % 10);
        System.out.println(notSorted);
    }

    @Test
    void join() {
        List<Integer> list1 = new List<>(new Integer[]{1, 3, 5});
        List<Integer> list2 = new List<>(new Integer[]{2, 4, 6});
        list1.join(list2);
        System.out.println(list1);
        System.out.println(list2);
    }

    @Test
    void clear() {
        System.out.println(list);
        list.clear();
        System.out.println(list);
    }

    @Test
    void append() {
        System.out.println(list);
        list.append(5);
        List<Integer> newList = new List<>();
        newList.append(1);
        newList.append(2);
        System.out.println(newList);
    }

    @SuppressWarnings("deprecation")
    @Test
    void deepCopy() {
        System.out.println(list);
        try {
            List<Integer> listCopy = list.deepCopy();
            System.out.println(listCopy);
        } catch (List.DeepCopyNotImplemented e) {
            e.printStackTrace();
        }
    }
}