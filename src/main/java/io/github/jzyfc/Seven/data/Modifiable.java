package io.github.jzyfc.Seven.data;

import java.util.List;

public interface Modifiable {
    List<String> toStrings();

    void fromStrings(List<String> strings);
}
