package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.utils.Triple;

import java.util.List;

public interface Displayable {
    default void display() {
        System.out.println(this);
    }

    int getDataCount();

    List<String> getDataHeader();

    List<String> getDataRepresentation();

}
