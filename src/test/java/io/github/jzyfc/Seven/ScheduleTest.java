package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.data.Schedule;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

class ScheduleTest {

    @Test
    void display() {
        Schedule schedule = new Schedule("1", "1", "1", "103");
        schedule.display();
    }

    @Test
    @Order(1)
    void serialize() throws IOException {
        Schedule schedule = new Schedule("1", "1", "1", "103");
        var byteArrayOutputStream = schedule.serialize(new ByteArrayOutputStream());
        var outputStream = new FileOutputStream("testOutput/out_schedule.data");
        byteArrayOutputStream.writeTo(outputStream);
    }

    @Test
    @Order(2)
    void deserialize() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(new File("testOutput" +
                "/out_schedule.data").toPath()));
        System.out.println(new Schedule().deserialize(byteArrayInputStream));
    }
}