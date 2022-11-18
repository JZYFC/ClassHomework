package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String studentID;

    private String major;

    @Override
    public String toString() {
        return "学号：%s, %s, 专业：%s".formatted(getStudentID(), super.toString(), getMajor());
    }

    public String getStudentID() {
        return studentID != null ? studentID : "";
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMajor() {
        return major != null ? major : "";
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Student() {
        super();
        this.studentID = null;
        this.major = null;
    }

    public Student(String studentID, String name, String sex, int age, String major) {
        super(name, sex, age);
        this.studentID = studentID;
        this.major = major;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        // super
        super.serialize(base);
        // studentID
        SerializeUtils.serializeToBytes(getStudentID(), base);
        // major
        SerializeUtils.serializeToBytes(getMajor(), base);
        return base;
    }

    @Override
    public Student deserialize(ByteArrayInputStream base) throws IOException {
        super.deserialize(base);
        // studentID
        this.studentID = SerializeUtils.parseStringFromBytes(base);
        // major
        this.major = SerializeUtils.parseStringFromBytes(base);
        return this;
    }

    @Override
    public int getDataCount() {
        return super.getDataCount() + 2;
    }

    @Override
    public List<String> getDataHeader() {
        List<String> list = new ArrayList<>(List.of("学生ID"));
        list.addAll(super.getDataHeader());
        list.add("专业");
        return list;
    }

    @Override
    public List<String> getDataRepresentation() {
        List<String> list = new ArrayList<>(List.of(getStudentID()));
        list.addAll(super.getDataRepresentation());
        list.add(getMajor());
        return list;
    }

    @Override
    public List<String> toStrings() {
        return getDataRepresentation();
    }

    @Override
    public void fromStrings(List<String> strings) {
        this.studentID = strings.get(0);
        super.fromStrings(strings.subList(1, 4));
        this.major = strings.get(4);
    }
}
