package io.github.jzyfc.Seven.data;

import io.github.jzyfc.Seven.serialization.ByteSerializable;
import io.github.jzyfc.Seven.serialization.SerializeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Course implements Displayable, ByteSerializable<Course>, Modifiable {
    private String courseName;
    private String courseID;
    private int courseHour;

    public Course() {
        this.courseName = null;
        this.courseID = null;
        this.courseHour = 0;
    }

    public Course(String courseName, String courseID, int courseHour) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseHour = courseHour;
    }

    @Override
    public String toString() {
        return "课程名：%s, 课程ID：%s, 课时：%d".formatted(getCourseName(), getCourseID(), getCourseHour());
    }

    public String getCourseName() {
        return courseName != null ? courseName : "";
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID != null ? courseID: "";
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    @Override
    public ByteArrayOutputStream serialize(ByteArrayOutputStream base) throws IOException {
        SerializeUtils.serializeToBytes(getCourseName(), base);
        SerializeUtils.serializeToBytes(getCourseID(), base);
        SerializeUtils.serializeToBytes(getCourseHour(), base);
        return base;
    }

    @Override
    public Course deserialize(ByteArrayInputStream base) throws IOException {
        this.courseName = SerializeUtils.parseStringFromBytes(base);
        this.courseID = SerializeUtils.parseStringFromBytes(base);
        this.courseHour = SerializeUtils.parseIntFromBytes(base);
        return this;
    }

    @Override
    public int getDataCount() {
        return 3;
    }

    @Override
    public List<String> getDataHeader() {
        return List.of("课程名", "课程ID", "课时");
    }

    @Override
    public List<String> getDataRepresentation() {
        return List.of(getCourseName(), getCourseID(), String.valueOf(courseHour));
    }

    @Override
    public List<String> toStrings() {
        return getDataRepresentation();
    }

    @Override
    public void fromStrings(List<String> strings) {
        this.courseName = strings.get(0);
        this.courseID = strings.get(1);
        this.courseHour = Integer.parseInt(strings.get(2));
    }
}
