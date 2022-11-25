package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.utils.Triple;

import java.util.List;

/**
 * Implement this interface for console output {@link #display()} and swing table representation
 */
public interface Displayable {
    default void display() {
        System.out.println(this);
    }

    /**
     * @return numbers of displayed data
     */
    int getDataCount();


    /**
     * @return description of your data
     */
    List<String> getDataHeader();

    /**
     * @return actual representation of your data. The order of representation should be same as {@link #getDataHeader()}
     * @see #getDataHeader()
     */
    List<String> getDataRepresentation();

}
