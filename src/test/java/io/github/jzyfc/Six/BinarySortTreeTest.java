package io.github.jzyfc.Six;

import io.github.jzyfc.Seven.data.Displayable;
import io.github.jzyfc.Seven.data.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySortTreeTest {

    BinarySortTree<Person> bst;

    @BeforeEach
    void construct() {
        bst = new BinarySortTree<>(List.of(
                new Person("10", "男", 20),
                new Person("11", "男", 20),
                new Person("12", "男", 20),
                new Person("13", "男", 20),
                new Person("01", "男", 20),
                new Person("02", "男", 20)
        ));
    }

    @Test
    void insert() {
        bst.midOrder(Displayable::display);
        System.out.println("------");
        bst.insert(new Person("04", "男", 21));
        bst.midOrder(Displayable::display);
    }

    @Test
    void delete() {
        bst.midOrder(Displayable::display);
        System.out.println("------");
        bst.delete(new Person("02", "男", 20));
        bst.midOrder(Displayable::display);
    }

    @Test
    void preOrder() {
        bst.preOrder(Displayable::display);
    }

    @Test
    void midOrder() {
        bst.midOrder(Displayable::display);
    }

    @Test
    void postOrder() {
        bst.postOrder(Displayable::display);
    }
}