package io.github.jzyfc.Seven.serialization;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SerializeUtils {
    @NotNull
    public static String parseStringFromBytes(ByteArrayInputStream base) throws IOException {
        byte[] LenBytes = base.readNBytes(4);
        int len = ByteBuffer.wrap(LenBytes).getInt();
        byte[] stringBytes = base.readNBytes(len);
        return new String(stringBytes, StandardCharsets.UTF_8);
    }

    public static int parseIntFromBytes(ByteArrayInputStream base) throws IOException {
        return ByteBuffer.wrap(base.readNBytes(4)).getInt();
    }

    public static void serializeToBytes(String string, ByteArrayOutputStream base) throws IOException {
        byte[] nameBytes = string.getBytes(StandardCharsets.UTF_8);
        base.write(ByteBuffer.allocate(4).putInt(nameBytes.length).array());
        base.write(nameBytes);
    }

    public static void serializeToBytes(int i, ByteArrayOutputStream base) throws IOException {
        base.write(ByteBuffer.allocate(4).putInt(i).array());
    }
}
