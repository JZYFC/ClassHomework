package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Person implements Displayable, ByteSerializable<Person>, Modifiable {
    protected String name;
    protected String sex;
    protected int age;

    @Override
    public String toString() {
        return "姓名：%s, 性别：%s, 年龄：%d".formatted(getName(), getSex(), getAge());
    }

    public String getName() {
        return name != null ? name : "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex != null ? sex : "";
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
        SerializeUtils.serializeToBytes(getName(), base);
        // sex
        SerializeUtils.serializeToBytes(getSex(), base);
        // age
        SerializeUtils.serializeToBytes(getAge(), base);
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

    @Override
    public int getDataCount() {
        return 3;
    }

    @Override
    public List<String> getDataHeader() {
        return List.of("姓名", "性别", "年龄");
    }

    @Override
    public List<String> getDataRepresentation() {
        return List.of(getName(), getSex(), String.valueOf(age));
    }

    @Override
    public List<String> toStrings() {
        return getDataRepresentation();
    }

    @Override
    public void fromStrings(List<String> strings) {
        this.name = strings.get(0);
        this.sex = strings.get(1);
        this.age = Integer.parseInt(strings.get(2));
    }
}
