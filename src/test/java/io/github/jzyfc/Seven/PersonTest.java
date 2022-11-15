package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.data.Person;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

class PersonTest {

    @Test
    void display() {
        Person person = new Person("张三", "男", 18);
        person.display();
    }

    @Test
    @Order(1)
    void serialize() throws IOException {
        Person person = new Person("张三", "男", 18);
        var byteArrayOutputStream = person.serialize(new ByteArrayOutputStream());
        var outputStream = new FileOutputStream("testOutput/out_person.data");
        byteArrayOutputStream.writeTo(outputStream);
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(new File("testOutput" +
                "/out_person.data").toPath()));
        System.out.println(new Person().deserialize(byteArrayInputStream));
    }
}