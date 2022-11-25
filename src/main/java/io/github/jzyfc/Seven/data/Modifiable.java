package io.github.jzyfc.Seven.data;

import java.util.List;

/**
 * Implement this interface for data manipulation with swing gui
 */
public interface Modifiable {
    List<String> toStrings();

    void fromStrings(List<String> strings);
}
