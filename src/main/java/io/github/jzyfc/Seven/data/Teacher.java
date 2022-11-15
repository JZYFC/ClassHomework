package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Teacher extends Person {
    private String teacherID;
    private String title;

    public Teacher() {
        super();
        this.teacherID = null;
        this.title = null;
    }

    public Teacher(String teacherID, String name, String sex, int age, String title) {
        super(name, sex, age);
        this.teacherID = teacherID;
        this.title = title;
    }

    @Override
    public String toString() {
        return "教师号：%s, %s, 职称：%s".formatted(teacherID, super.toString(), title);
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        super.serialize(base);
        // teacherID
        SerializeUtils.serializeToBytes(this.teacherID, base);
        // title
        SerializeUtils.serializeToBytes(this.title, base);
        return base;
    }

    @Override
    public Teacher deserialize(ByteArrayInputStream base) throws IOException {
        super.deserialize(base);
        // teacherID
        this.teacherID = SerializeUtils.parseStringFromBytes(base);
        // title
        this.title = SerializeUtils.parseStringFromBytes(base);
        return this;
    }
}
