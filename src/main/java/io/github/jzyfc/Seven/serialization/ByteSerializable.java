package io.github.jzyfc.Seven.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface ByteSerializable<T extends ByteSerializable<T>> {
    ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException;

    T deserialize(ByteArrayInputStream base) throws IOException;
}
