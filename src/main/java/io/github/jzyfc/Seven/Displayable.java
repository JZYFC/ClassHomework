package io.github.jzyfc.Seven;

public interface Displayable {
    default void display() {
        System.out.println(this);
    }
}
