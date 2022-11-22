package io.github.jzyfc.Seven.ui;

import io.github.jzyfc.Seven.data.*;
import io.github.jzyfc.Seven.serialization.DataFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private final String FILE_BASE_ROOT = "data/";

    private final DataFile<Teacher> teacherDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "teacher.data"), Teacher::new);

    private final DataFile<Student> studentDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "student.data"), Student::new);

    private final DataFile<Course> courseDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "course.data"), Course::new);

    private final DataFile<Schedule> scheduleDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "schedule.data"), Schedule::new);

    private final DataFile<ElectiveCourse> electiveDataFile
            = new DataFile<>(new File(FILE_BASE_ROOT + "electiveCourse.data"), ElectiveCourse::new);

    private ArrayList<Teacher> teacherList;
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<Schedule> scheduleList;
    private ArrayList<ElectiveCourse> electiveList;

    public MainWindow() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(800, 600);

        this.setTitle("管理系统");

        loadFile();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveFile();
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("教师信息", new DataPanel<>(this, "教师", teacherList, Teacher::new));
        tabbedPane.add("学生信息", new DataPanel<>(this, "学生", studentList, Student::new));
        tabbedPane.add("课程信息", new DataPanel<>(this, "课程", courseList, Course::new));
        tabbedPane.add("排课信息", new DataPanel<>(this, "排课", scheduleList, Schedule::new));
        tabbedPane.add("选课信息", new DataPanel<>(this, "选课", electiveList, ElectiveCourse::new));

        this.add(tabbedPane);
        this.setVisible(true);
    }

    private void loadFile() {
//        teacherList = new ArrayList<>(List.of(
//                new Teacher("1", "张三", "男", 18, "教授"),
//                new Teacher("2", "李四", "男", 18, "教授"),
//                new Teacher("3", "王五", "男", 18, "教授")
//        ));
//        studentList = new ArrayList<>(List.of(
//                new Student("0001", "张三", "男", 18, "Software"),
//                new Student("0002", "李四", "男", 18, "Software"),
//                new Student("0003", "王五", "男", 18, "Software")
//        ));
//        courseList = new ArrayList<>(List.of(
//                new Course("1", "编译原理", 96),
//                new Course("2", "编译原", 96),
//                new Course("3", "编译", 96)
//        ));
//        scheduleList = new ArrayList<>(List.of(
//                new Schedule("1", "1", "1", "103"),
//                new Schedule("2", "2", "2", "104"),
//                new Schedule("3", "3", "3", "105")
//
//        ));
//        electiveList = new ArrayList<>(List.of(
//                new ElectiveCourse("1", "1", "1"),
//                new ElectiveCourse("2", "2", "2"),
//                new ElectiveCourse("3", "3", "3")
//        ));
        teacherList = new ArrayList<>(teacherDataFile.readFile());
        studentList = new ArrayList<>(studentDataFile.readFile());
        courseList = new ArrayList<>(courseDataFile.readFile());
        scheduleList = new ArrayList<>(scheduleDataFile.readFile());
        electiveList = new ArrayList<>(electiveDataFile.readFile());
    }

    private void saveFile() {
        System.out.println("Saving files");
        JDialog dialog = new JDialog(this, false);
        JLabel label = new JLabel("保存文件中");
        dialog.add(label);
        dialog.setLocationRelativeTo(this);
        dialog.setSize(label.getMaximumSize());
        dialog.setVisible(true);
        teacherDataFile.writeFile(teacherList);
        studentDataFile.writeFile(studentList);
        courseDataFile.writeFile(courseList);
        scheduleDataFile.writeFile(scheduleList);
        electiveDataFile.writeFile(electiveList);
        dialog.dispose();
        this.dispose();
        System.exit(0);
    }
}
