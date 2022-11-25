package io.github.jzyfc.Seven.serialization;

import java.io.*;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class DataFile<T extends ByteSerializable<? super T>> {
    private final File opFile;

    private final Supplier<T> supplier;

    /**
     * @param opFile the data storing file
     * @param emptyConstructor an empty constructor of T for deserialization
     */
    public DataFile(File opFile, Supplier<T> emptyConstructor) {
        this.opFile = opFile;
        this.supplier = emptyConstructor;
    }

    public File getFile() {
        return opFile;
    }


    /**
     * @return a list of deserialize objects
     */
    public List<T> readFile() {
        try (FileInputStream fis = new FileInputStream(opFile)) {
            byte[] bytes = fis.readAllBytes();
            ArrayList<T> returnList = new ArrayList<>();
            try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
                while (true) {
                    try {
                        @SuppressWarnings("unchecked")
                        T obj = (T) supplier.get().deserialize(bis);
                        returnList.add(obj);
                    } catch (BufferUnderflowException e) {
//                        e.printStackTrace();
                        break;
                    }
                }
            }
            return returnList;
        } catch (IOException e) {
            System.err.println("文件未找到");
        }
        return null;
    }

    /**
     * @param array elements in this array will be written in to the file
     */
    public void writeFile(T[] array) {
        try {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                for (T obj : array) {
                    obj.serialize(bos);
                }
                try (FileOutputStream fos = new FileOutputStream(opFile)) {
                    fos.write(bos.toByteArray());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("序列化失败");
        }
    }

    /**
     * @param collection elements in this collection will be written in to the file
     */
    public void writeFile(Collection<T> collection) {
        try {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                for (T obj : collection) {
                    obj.serialize(bos);
                }
                try (FileOutputStream fos = new FileOutputStream(opFile)) {
                    fos.write(bos.toByteArray());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("序列化失败");
        }
    }
}
