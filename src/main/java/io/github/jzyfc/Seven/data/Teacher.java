package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return "教师号：%s, %s, 职称：%s".formatted(getTeacherID(), super.toString(), getTitle());
    }

    public String getTeacherID() {
        return teacherID != null ? teacherID : "";
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTitle() {
        return title != null ? title : "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        super.serialize(base);
        // teacherID
        SerializeUtils.serializeToBytes(getTeacherID(), base);
        // title
        SerializeUtils.serializeToBytes(getTitle(), base);
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

    @Override
    public int getDataCount() {
        return super.getDataCount() + 2;
    }

    @Override
    public List<String> getDataHeader() {
        List<String> list = new ArrayList<>(List.of("教师号"));
        list.addAll(super.getDataHeader());
        list.add("职称");
        return list;
    }

    @Override
    public List<String> getDataRepresentation() {
        List<String> list = new ArrayList<>(List.of(getTeacherID()));
        list.addAll(super.getDataRepresentation());
        list.add(getTitle());
        return list;
    }

    @Override
    public List<String> toStrings() {
        return getDataRepresentation();
    }

    @Override
    public void fromStrings(List<String> strings) {
        this.teacherID = strings.get(0);
        super.fromStrings(strings.subList(1, 4));
        this.title = strings.get(4);
    }
}
