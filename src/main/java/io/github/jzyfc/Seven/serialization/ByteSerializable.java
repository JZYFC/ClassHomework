package io.github.jzyfc.Seven.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Implement this interface for custom serialization and deserialization
 *
 * @param <T> Your type to be serialized
 * @implNote Any type implement this interface should provide an empty constructor
 */
public interface ByteSerializable<T> {
    /**
     * @param base a {@link ByteArrayInputStream}, you should write your bytes here
     * @return same {@link ByteArrayInputStream} as parameter
     * @throws IOException some {@link IOException}
     */
    ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException;

    /**
     * @param base a {@link ByteArrayOutputStream}, you should read your data here
     * @return deserialized object, typically {@code this}
     * @throws IOException some {@link IOException}
     */
    T deserialize(ByteArrayInputStream base) throws IOException;
}
