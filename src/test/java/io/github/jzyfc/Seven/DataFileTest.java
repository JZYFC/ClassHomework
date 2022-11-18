package io.github.jzyfc.Seven;

import io.github.jzyfc.Seven.data.Displayable;
import io.github.jzyfc.Seven.data.Person;
import io.github.jzyfc.Seven.serialization.DataFile;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

class DataFileTest {

    DataFile<Person> dataFile = new DataFile<>(new File("testOutput/persons.data"), Person::new);

    @Test
    @Order(1)
    void writeFile() {
        var list = List.of(
                new Person("张三", "男", 18),
                new Person("李四", "男", 18),
                new Person("王五", "男", 18)
        );
        dataFile.writeFile(list);
    }

    @Test
    @Order(2)
    void readFile() {
        dataFile.readFile().forEach(Displayable::display);
    }
}