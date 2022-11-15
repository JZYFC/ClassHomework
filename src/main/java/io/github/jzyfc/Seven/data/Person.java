package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.Displayable;
import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Person implements Displayable, ByteSerializable<Person> {
    protected String name;
    protected String sex;
    protected int age;

    @Override
    public String toString() {
        return "姓名：%s, 性别：%s, 年龄：%d".formatted(name, sex, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
        this.name = null;
        this.sex = null;
        this.age = 0;
    }

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        // name
        SerializeUtils.serializeToBytes(this.name, base);
        // sex
        SerializeUtils.serializeToBytes(this.sex, base);
        // age
        SerializeUtils.serializeToBytes(this.age, base);
        return base;
    }

    @Override
    public Person deserialize(ByteArrayInputStream base) throws IOException {
        // name
        this.name = SerializeUtils.parseStringFromBytes(base);
        // sex
        this.sex = SerializeUtils.parseStringFromBytes(base);
        // age
        this.age = SerializeUtils.parseIntFromBytes(base);
        return this;
    }

}
